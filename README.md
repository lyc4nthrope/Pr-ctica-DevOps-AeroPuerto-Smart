# FlyTrack — Práctica DevOps: AeroPuerto Smart

**Universidad del Quindío · Ingeniería de Software III**

Práctica de laboratorio DevOps sobre el caso AeroPuerto Smart. El objetivo no es construir una aplicación compleja sino demostrar un ciclo completo de DevOps: control de versiones, pruebas automatizadas, análisis de calidad, empaquetado con contenedores y despliegue automático a staging.

---

## Estado del pipeline

| Job | Estado |
|-----|--------|
| Pruebas (32) + JaCoCo | ✅ Pasando |
| Build JAR | ✅ Pasando |
| Imagen Docker en GHCR | ✅ Publicada |

**Imagen en staging:**
```bash
docker pull ghcr.io/lyc4nthrope/flytrack:latest
docker run -p 8080:8080 ghcr.io/lyc4nthrope/flytrack:latest
```

---

## ¿Qué es FlyTrack?

FlyTrack es la API REST de AeroPuerto Smart. Permite a los pasajeros consultar vuelos, conocer la puerta de embarque y reportar inconvenientes con su equipaje.

### Endpoints disponibles

| Método | Ruta | Descripción | Respuesta |
|--------|------|-------------|-----------|
| `GET` | `/api/vuelos/{id}` | Consultar información de un vuelo por ID | `200 OK` / `404 Not Found` |
| `GET` | `/api/puertas/{vuelo}` | Consultar puerta de embarque por número de vuelo | `200 OK` / `404 Not Found` |
| `POST` | `/api/equipaje/reporte` | Crear reporte de inconveniente con equipaje | `201 Created` / `400 Bad Request` |

### Ejemplos de uso

```bash
# Consultar vuelo
curl http://localhost:8080/api/vuelos/1

# Respuesta
{
  "id": 1,
  "numero": "AV101",
  "origen": "Bogotá",
  "destino": "Medellín",
  "hora": "08:00",
  "estado": "A tiempo"
}
```

```bash
# Consultar puerta de embarque
curl http://localhost:8080/api/puertas/AV101

# Respuesta
{
  "puerta": "A12",
  "vuelo": "AV101",
  "terminal": "Terminal Norte"
}
```

```bash
# Reportar equipaje
curl -X POST http://localhost:8080/api/equipaje/reporte \
  -H "Content-Type: application/json" \
  -d '{"pasajero":"Juan Pérez","vuelo":"AV101","descripcion":"Maleta perdida"}'

# Respuesta
{
  "id": 1,
  "pasajero": "Juan Pérez",
  "vuelo": "AV101",
  "descripcion": "Maleta perdida"
}
```

---

## Arquitectura de la aplicación

```
src/main/java/co/edu/uniquindio/flytrack/
├── controller/
│   ├── VuelosController.java      ← recibe peticiones HTTP, delega al service
│   ├── PuertasController.java
│   └── EquipajeController.java
├── service/
│   ├── VuelosService.java         ← lógica de negocio, datos en memoria
│   ├── PuertasService.java
│   └── EquipajeService.java
└── model/
    ├── Vuelo.java                 ← POJO sin anotaciones de framework
    ├── Puerta.java
    └── ReporteEquipaje.java
```

**Patrón**: Controller → Service → Model. Sin base de datos — los datos viven en memoria con `List<T>`. El foco del laboratorio es DevOps, no persistencia.

**Decisiones técnicas:**

| Decisión | Por qué |
|----------|---------|
| Constructor injection (no `@Autowired` en campos) | Patrón recomendado por Spring; facilita las pruebas y hace las dependencias explícitas |
| `Optional<T>` en búsquedas | Elimina `NullPointerException`; el controlador está obligado a manejar el caso 404 |
| `List.copyOf()` en retornos de servicio | Devuelve colección inmutable; el cliente no puede mutar el estado interno del servicio |
| `AtomicLong` para generación de IDs | Thread-safe sin sincronización explícita |
| Sin Lombok | Sin dependencia extra; el código es explícito y legible |

---

## Pipeline CI/CD

El pipeline corre automáticamente en GitHub Actions en cada push o pull request a `develop` y `main`.

```
Push / Pull Request
       │
       ▼
  ┌─────────────────────────────────────────┐
  │          Integración Continua           │
  │                                         │
  │  [test]  mvn verify                     │
  │  · 32 pruebas JUnit 5                   │
  │  · Reporte Surefire (resultados)        │
  │  · Reporte JaCoCo (cobertura 87%)       │
  │         │                               │
  │         ▼ pasa                          │
  │  [build] mvn package -DskipTests        │
  │  · JAR ejecutable (Spring Boot)         │
  └─────────────────────────────────────────┘
       │ pasa + es push (no PR)
       ▼
  ┌─────────────────────────────────────────┐
  │           Entrega Continua              │
  │                                         │
  │  [docker-publish]                       │
  │  · Build imagen Docker multi-stage      │
  │  · Push a ghcr.io/lyc4nthrope/flytrack  │
  │    → :latest                            │
  │    → :<SHA del commit>                  │
  └─────────────────────────────────────────┘
```

Si cualquier prueba falla en `[test]`, el pipeline se detiene. Nada llega a staging sin pasar las pruebas.

### Artefactos publicados por el pipeline

| Artefacto | Contenido |
|-----------|-----------|
| `surefire-reports` | Resultados XML/HTML de las 32 pruebas |
| `jacoco-report` | Reporte HTML de cobertura de código |
| `flytrack-jar` | JAR ejecutable listo para despliegue |

---

## Herramientas utilizadas

### Git
**Qué es:** sistema de control de versiones distribuido.
**Para qué se usó:** registrar cada cambio del proyecto con autor, fecha y descripción. Permite ver el historial completo, volver a cualquier versión anterior y trabajar en paralelo sin conflictos.

### GitHub
**Qué es:** plataforma de alojamiento de repositorios Git con herramientas de colaboración.
**Para qué se usó:** alojar el repositorio, revisar cambios antes de integrarlos (pull requests) y como punto de integración para el pipeline CI/CD.

### Git Flow (estrategia de ramas)
**Qué es:** convención de organización de ramas en Git.
**Para qué se usó:** separar el trabajo de cada integrante del equipo y definir un flujo claro desde desarrollo hasta producción.

```
main                  ← código estable en producción
develop               ← integración continua del equipo
feature/cristhian     ← trabajo de Cristhian Osorio
feature/camilo        ← trabajo de Camilo
feature/jeyson        ← trabajo de Jeyson
release/preproduccion ← preparación de releases
hotfix/produccion     ← correcciones urgentes en producción
```

Cada feature se integra a `develop` con `merge --no-ff` para preservar la trazabilidad en el historial.

### Java 17 (Temurin)
**Qué es:** versión LTS de Java con soporte extendido, distribución Eclipse Temurin (OpenJDK).
**Para qué se usó:** lenguaje de implementación de FlyTrack. LTS garantiza soporte y compatibilidad a largo plazo.

### Spring Boot 3.2.5
**Qué es:** framework de Java que simplifica la creación de aplicaciones web con configuración mínima.
**Para qué se usó:** construir la API REST de FlyTrack. Provee el servidor web embebido (Tomcat), mapeo de rutas HTTP, serialización JSON automática y el contexto de inyección de dependencias.

### Maven 3.9
**Qué es:** herramienta de construcción y gestión de dependencias para proyectos Java.
**Para qué se usó:** compilar el código, ejecutar las pruebas, generar el JAR ejecutable y coordinar los plugins (JaCoCo, Spring Boot). Define el ciclo de vida del proyecto (compile → test → package → verify).

### JUnit 5
**Qué es:** framework de pruebas unitarias para Java.
**Para qué se usó:** escribir y ejecutar las 32 pruebas automatizadas del proyecto. Permite definir casos de prueba con `@Test`, organizar con `@DisplayName` y verificar resultados con `assertThat`.

### Mockito + MockMvc
**Qué es:** Mockito es una librería para crear objetos simulados (mocks) en pruebas. MockMvc es la utilidad de Spring para probar controladores HTTP sin levantar un servidor real.
**Para qué se usó:** en las pruebas de controladores (`@WebMvcTest`), Mockito simula el comportamiento de los servicios y MockMvc simula las peticiones HTTP, haciendo las pruebas más rápidas y aisladas.

### JaCoCo
**Qué es:** plugin de Maven que mide la cobertura de código durante la ejecución de pruebas.
**Para qué se usó:** generar un reporte que muestra qué porcentaje del código ejecutaron las pruebas. Resultado: **87% de instrucciones, 100% de métodos públicos**. El reporte se publica como artefacto en cada ejecución del pipeline.

### Docker
**Qué es:** plataforma de contenedores que empaqueta una aplicación con todo lo necesario para ejecutarla.
**Para qué se usó:** empaquetar FlyTrack en una imagen reproducible. Resuelve el problema de "en mi máquina funciona" — la imagen corre igual en cualquier entorno.

**Dockerfile multi-stage:**
```dockerfile
# Stage 1: compilar (Maven + JDK — imagen pesada, solo para build)
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: ejecutar (solo JRE — imagen liviana, sin código fuente)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

El multi-stage separa construcción de ejecución: la imagen final no contiene Maven, el JDK ni el código fuente — solo el JRE y el JAR. Esto hace la imagen más pequeña y segura.

### Docker Compose
**Qué es:** herramienta para definir y levantar servicios Docker con un solo comando.
**Para qué se usó:** simplificar la ejecución local de FlyTrack. Con `docker compose up -d` se construye la imagen y levanta el contenedor sin comandos adicionales.

### GitHub Actions
**Qué es:** motor de automatización integrado a GitHub que ejecuta workflows definidos en YAML.
**Para qué se usó:** orquestar el pipeline CI/CD completo. Cada push dispara automáticamente los jobs de prueba, construcción y publicación de la imagen Docker. Elimina el proceso manual de validar y desplegar.

### GitHub Container Registry (GHCR)
**Qué es:** registro de imágenes Docker integrado a GitHub (`ghcr.io`).
**Para qué se usó:** publicar la imagen de FlyTrack como entorno de staging. Cada imagen lleva dos tags: `latest` (versión más reciente) y el SHA del commit (trazabilidad exacta). La autenticación usa `GITHUB_TOKEN` — sin secretos adicionales.

### SonarQube (evaluado, no implementado)
**Qué es:** plataforma de análisis estático de código que detecta bugs potenciales, code smells, código duplicado y deuda técnica.
**Por qué no se implementó:** requiere un servidor propio o cuenta en SonarCloud. Para el alcance mínimo viable del laboratorio, JaCoCo cubre el concepto de calidad en el pipeline. En un proyecto de producción real, SonarQube y JaCoCo se usarían juntos.

### Kubernetes (evaluado, no implementado)
**Qué es:** orquestador de contenedores para gestionar múltiples instancias, escala automática y alta disponibilidad.
**Por qué no se implementó:** FlyTrack es un servicio único sin requisitos de escala ni alta disponibilidad. Kubernetes agrega complejidad operacional (cluster, namespaces, manifiestos) que no aporta valor demostrable en este laboratorio. El concepto de despliegue controlado está cubierto con Docker + GHCR.

---

## Pruebas

### Ejecutar todas las pruebas

```bash
cd flytrack
mvn verify
```

Genera los reportes en:
- `target/surefire-reports/` — resultados de pruebas
- `target/site/jacoco/index.html` — cobertura de código (abrir en navegador)

### Resumen de pruebas

| Clase | Tipo | Casos | Cobertura |
|-------|------|-------|-----------|
| `VuelosServiceTest` | Unitaria | 6 | Búsqueda por ID, campos, IDs únicos |
| `PuertasServiceTest` | Unitaria | 6 | Búsqueda por vuelo, case-insensitive |
| `EquipajeServiceTest` | Unitaria | 6 | IDs incrementales, persistencia en lista |
| `VuelosControllerTest` | Integración web | 4 | HTTP 200/404, JSON correcto |
| `PuertasControllerTest` | Integración web | 4 | HTTP 200/404, JSON correcto |
| `EquipajeControllerTest` | Integración web | 6 | HTTP 201/400, validaciones |
| **Total** | | **32** | **87% instrucciones · 100% métodos** |

---

## Cómo ejecutar el proyecto

### Con Maven (local)

```bash
cd flytrack
mvn spring-boot:run
```

### Con Docker Compose

```bash
cd flytrack
docker compose up -d
```

### Desde el registro de staging (GHCR)

```bash
docker pull ghcr.io/lyc4nthrope/flytrack:latest
docker run -p 8080:8080 ghcr.io/lyc4nthrope/flytrack:latest
```

En todos los casos la aplicación queda disponible en `http://localhost:8080`.

---

## Documentación del laboratorio

| Documento | Fase | Contenido |
|-----------|------|-----------|
| [`docs/diagnostico.md`](docs/diagnostico.md) | Fase 1 | ¿Qué es DevOps?, diagnóstico del flujo actual, herramientas identificadas |
| [`docs/diseno-pipeline.md`](docs/diseno-pipeline.md) | Fase 2 | Diseño del pipeline CI/CD con diagrama, evaluación de SonarQube y Kubernetes |
| [`docs/informe.md`](docs/informe.md) | Fase 4 | Informe técnico: decisiones tomadas, limitaciones, aprendizajes |

---

## Estrategia de ramas

Este proyecto usa **Git Flow**. El trabajo de cada integrante se desarrolla en su rama `feature/*` y se integra a `develop` mediante `merge --no-ff` para preservar la trazabilidad.

```
main ←── release/preproduccion ←── develop ←── feature/cristhian
                                          ←── feature/camilo
                                          ←── feature/jeyson
         hotfix/produccion ──────────────────────────────────→ main
```

---

## Equipo

| Integrante | Rama |
|------------|------|
| Cristhian Osorio | `feature/cristhian` |
| Camilo | `feature/camilo` |
| Jeyson | `feature/jeyson` |

**Universidad del Quindío · Facultad de Ingeniería · Ingeniería de Software III**
