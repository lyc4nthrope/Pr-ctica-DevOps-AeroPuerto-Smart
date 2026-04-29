# Fase 4: Informe técnico — Práctica DevOps AeroPuerto Smart

**Universidad del Quindío — Ingeniería de Software III**
**Integrante**: Cristhian Osorio
**Rama de trabajo**: `feature/cristhian`
**Fecha**: Abril 2026

---

## 1. Problema inicial

AeroPuerto Smart gestiona su sistema FlyTrack con un proceso de desarrollo y despliegue completamente manual:

- Sin control de versiones estructurado.
- Sin pruebas automáticas.
- Despliegues directos a producción sin validación previa.
- Entorno de ejecución variable entre desarrolladores.

El resultado es un sistema frágil, lento de actualizar y con alta probabilidad de fallo en cada cambio.

## 2. Diagnóstico del flujo actual

El flujo identificado antes de la intervención DevOps:

1. Desarrollador modifica código en local.
2. Comparte cambios manualmente (sin Git).
3. Integración informal entre compañeros.
4. Prueba manual antes del despliegue (si hay tiempo).
5. Despliegue manual sobre el servidor de producción.
6. Correcciones reactivas en producción cuando algo falla.

Esto genera ciclos de entrega de días, errores frecuentes en producción y nula trazabilidad de cambios.

## 3. Solución propuesta e implementada

Se implementó una solución mínima viable de DevOps aplicando los siguientes pilares:

### 3.1 Control de versiones con estrategia de ramas (Git Flow)

Se organizó el repositorio con la siguiente estructura:

```
main              ← producción estable
develop           ← integración continua del equipo
feature/cristhian ← trabajo de este integrante
feature/camilo    ← trabajo del segundo integrante
feature/jeyson    ← trabajo del tercer integrante
release/preproduccion ← preparación de releases
hotfix/produccion     ← correcciones urgentes en producción
```

Cada feature se integra a `develop` mediante merge `--no-ff` para preservar la trazabilidad del historial.

### 3.2 Aplicación FlyTrack con arquitectura limpia

Se construyó la aplicación en Java 17 con Spring Boot 3.2.5. La arquitectura sigue el patrón Controller → Service → Model:

- **3 controladores REST**: VuelosController, PuertasController, EquipajeController
- **3 servicios**: VuelosService, PuertasService, EquipajeService
- **3 modelos POJO**: Vuelo, Puerta, ReporteEquipaje

Decisiones técnicas relevantes:

| Decisión | Justificación |
|----------|--------------|
| Sin JPA ni base de datos | El laboratorio es de DevOps, no de persistencia. Datos en memoria con `List<T>` |
| Constructor injection | Patrón recomendado por Spring; facilita pruebas y evita dependencias circulares |
| `Optional<T>` en búsquedas | Elimina NullPointerException; fuerza al controlador a manejar el caso 404 |
| `List.copyOf()` en retornos | Devuelve colección inmutable; el cliente no puede mutar el estado interno del servicio |
| `AtomicLong` para IDs | Thread-safe sin necesidad de sincronización explícita |
| Sin Lombok | Sin dependencia adicional; el código es explícito y legible |

### 3.3 Suite de pruebas automatizadas (32 pruebas)

| Clase de prueba | Tipo | Pruebas | Estrategia |
|-----------------|------|---------|------------|
| VuelosServiceTest | Unitaria | 6 | JUnit 5 puro, sin Spring |
| PuertasServiceTest | Unitaria | 6 | JUnit 5 puro, sin Spring |
| EquipajeServiceTest | Unitaria | 6 | JUnit 5 puro, sin Spring |
| VuelosControllerTest | Integración web | 4 | @WebMvcTest + @MockBean |
| PuertasControllerTest | Integración web | 4 | @WebMvcTest + @MockBean |
| EquipajeControllerTest | Integración web | 6 | @WebMvcTest + @MockBean |

**Cobertura final con JaCoCo**: 87% de instrucciones, 100% de métodos públicos.

La elección de `@WebMvcTest` sobre `@SpringBootTest` es deliberada: carga solo la capa web, hace las pruebas más rápidas y más aisladas.

### 3.4 Empaquetado con Docker (multi-stage)

```dockerfile
# Stage 1: build con Maven + JDK
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B      # capa de caché de dependencias
COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: runtime con solo JRE
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Decisión clave**: usar `maven:3.9-eclipse-temurin-17` como builder (incluye Maven y JDK) y `eclipse-temurin:17-jre` como runtime (solo JRE, imagen más liviana, sin código fuente).

El error original fue usar `eclipse-temurin:17-jdk` como builder — esa imagen no incluye Maven, por lo que el build fallaba con `mvn: not found`.

### 3.5 Pipeline CI/CD con GitHub Actions

Pipeline de tres jobs con dependencias en cadena:

```
[test] → [build] → [docker-publish]
```

| Job | Herramienta | Artefacto generado |
|-----|-------------|-------------------|
| test | `mvn verify` | Reporte Surefire + Reporte JaCoCo |
| build | `mvn package -DskipTests` | JAR ejecutable |
| docker-publish | `docker/build-push-action@v5` | Imagen en ghcr.io (staging) |

`docker-publish` solo corre en `push` (no en PRs) porque publicar imágenes desde ramas no integradas contamina el registro de staging.

## 4. Verificación SPEC-27

La imagen Docker fue verificada localmente:

```bash
docker build -t flytrack:local .          # BUILD SUCCESS
docker compose up -d                      # Container flytrack-app Started
curl http://localhost:8080/api/vuelos/1   # HTTP 200
curl http://localhost:8080/api/puertas/AV101  # HTTP 200
curl -X POST http://localhost:8080/api/equipaje/reporte \
  -H "Content-Type: application/json" \
  -d '{"pasajero":"Juan","vuelo":"AV101","descripcion":"Maleta perdida"}' # HTTP 201
```

Los tres endpoints responden correctamente dentro del contenedor.

## 5. Limitaciones y decisiones conscientes

### 5.1 Limitaciones técnicas

| Limitación | Razón |
|------------|-------|
| No hay base de datos | El alcance del laboratorio es DevOps, no persistencia. Los datos son en memoria y se pierden al reiniciar el contenedor |
| No hay autenticación | Fuera del alcance; requeriría Spring Security y gestión de tokens |
| No hay CD hacia una VM real | Se simula staging con GHCR; un despliegue real requeriría acceso a servidor con IP pública |

### 5.2 Herramientas evaluadas y no implementadas

**SonarQube**

El documento de la práctica lo menciona explícitamente como herramienta sugerida para análisis de calidad. Se evaluó y se decidió no implementarlo por las siguientes razones:

- Requiere un servidor SonarQube propio o una cuenta en SonarCloud con permisos de organización.
- La integración con GitHub Actions requiere configurar `SONAR_TOKEN` y `SONAR_HOST_URL` como secretos de repositorio.
- Para el alcance mínimo viable del laboratorio, JaCoCo provee métricas de cobertura suficientes para demostrar el concepto de "análisis de calidad en el pipeline".

La diferencia conceptual es importante: JaCoCo mide **cobertura** (qué porcentaje del código ejecutaron las pruebas), mientras que SonarQube realiza **análisis estático** (detecta bugs potenciales, code smells, código duplicado y deuda técnica sin ejecutar el código). En un proyecto real de producción, ambas herramientas se usarían juntas. En este laboratorio, JaCoCo con 87% de cobertura de instrucciones y 100% de métodos públicos demuestra el principio.

**Kubernetes**

También mencionado en el documento como herramienta sugerida para orquestación. Se evaluó y se descartó porque:

- Kubernetes resuelve problemas de escala, alta disponibilidad y gestión de múltiples réplicas.
- FlyTrack es un servicio único sin requisitos de escalado horizontal ni zero-downtime deployment.
- La complejidad operacional (cluster, namespaces, manifiestos YAML, ingress controllers) no aporta valor demostrable para el alcance del laboratorio.
- El equivalente funcional implementado —imagen Docker publicada en GHCR + docker-compose para ejecución— cubre el mismo concepto de despliegue reproducible y controlado.

## 6. Qué mejoró frente al proceso anterior

| Aspecto | Antes | Después |
|---------|-------|---------|
| Pruebas | 0 (manuales) | 32 automatizadas en cada push |
| Calidad | Sin medición | 87% cobertura medida por JaCoCo |
| Despliegue | Manual a producción | Automatizado a staging (GHCR) |
| Empaquetado | "en mi máquina funciona" | Imagen Docker reproducible |
| Trazabilidad | Ninguna | Cada imagen lleva el SHA del commit |
| Control de versiones | Informal | Git Flow con ramas por integrante |

## 7. Aprendizajes

- DevOps no es solo instalar herramientas. Es definir un proceso claro y hacerlo automatizable.
- La arquitectura del código afecta directamente la calidad de las pruebas. Constructor injection y Optional<T> hicieron las pruebas más simples y predecibles.
- Los Dockerfile multi-stage son la práctica correcta: separan responsabilidades de construcción y ejecución, generando imágenes más livianas y seguras.
- GitHub Actions con `GITHUB_TOKEN` permite publicar en GHCR sin configurar secretos adicionales, lo que simplifica mucho el pipeline inicial.
- La estrategia de ramas no es un formalismo vacío: al trabajar en `feature/cristhian` y hacer merge `--no-ff` a `develop`, el historial muestra exactamente qué se integró y cuándo.
