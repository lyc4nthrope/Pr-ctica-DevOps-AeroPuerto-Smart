# Fase 2: Diseño del pipeline CI/CD — FlyTrack

## 1. ¿Qué es un pipeline CI/CD?

Un pipeline es una secuencia de pasos automáticos que se ejecutan cada vez que hay un cambio en el código. Garantiza que el software siempre pase por las mismas etapas de validación antes de llegar a producción.

- **CI (Integración Continua)**: cada cambio se integra, compila y prueba automáticamente.
- **CD (Entrega Continua)**: si las pruebas pasan, la aplicación se empaqueta y publica sin intervención manual.

## 2. Flujo del pipeline propuesto

```mermaid
flowchart TD
    A([Push / Pull Request]) --> B

    subgraph CI["Integración Continua"]
        B[test\nEjecutar pruebas con mvn verify\nGenerar reporte JaCoCo] --> C[build\nEmpaquetar JAR con mvn package]
    end

    subgraph CD["Entrega Continua — solo en push a develop/main"]
        C --> D[docker-publish\nBuild imagen Docker\nPublicar en GHCR como staging]
    end

    B -->|Falla| E([Pipeline bloqueado\nNo se construye ni despliega])
    D --> F([Imagen disponible en ghcr.io\nflytrack:latest + flytrack:SHA])
```

## 3. Descripción de cada etapa

### Etapa 1 — test

**Disparador**: cualquier push o pull request hacia `develop` o `main`.

**Pasos**:
1. Checkout del código fuente.
2. Configurar Java 17 (Temurin) con caché de dependencias Maven.
3. Ejecutar `mvn verify`: compila, corre las 32 pruebas y genera reporte JaCoCo.
4. Publicar reporte Surefire (resultados de pruebas) como artefacto.
5. Publicar reporte JaCoCo (cobertura de código) como artefacto.

**Criterio de éxito**: todas las pruebas pasan. Si alguna falla, el pipeline se detiene aquí.

**Cobertura actual**: 87% de instrucciones, 100% de métodos públicos.

### Etapa 2 — build

**Disparador**: éxito de `test` (`needs: test`).

**Pasos**:
1. Checkout del código fuente.
2. Configurar Java 17 con caché Maven.
3. Ejecutar `mvn package -DskipTests -B` para generar el JAR ejecutable.
4. Publicar el JAR como artefacto descargable.

**Criterio de éxito**: JAR generado correctamente por Spring Boot Maven Plugin.

### Etapa 3 — docker-publish

**Disparador**: éxito de `build` + `github.event_name == 'push'` (no corre en PRs).

**Pasos**:
1. Checkout del código fuente.
2. Login en GitHub Container Registry con `GITHUB_TOKEN` (sin secretos adicionales).
3. Build de imagen Docker multi-stage desde `flytrack/Dockerfile`.
4. Push de dos tags: `latest` y el SHA del commit.

**Criterio de éxito**: imagen publicada en `ghcr.io/lyc4nthrope/flytrack`.

**Por qué no corre en PRs**: publicar una imagen desde una rama no integrada puede generar versiones incorrectas en el registro. Solo el código que llegó a `develop` o `main` (validado por revisión) merece existir como imagen de staging.

## 4. Diagrama de dependencias entre jobs

```
push/PR
   │
   ▼
[test] ──── falla ──▶ bloqueado
   │
   ▼ pasa
[build]
   │
   ▼ pasa + es push (no PR)
[docker-publish]
   │
   ▼
ghcr.io/lyc4nthrope/flytrack:latest
ghcr.io/lyc4nthrope/flytrack:<SHA>
```

## 5. ¿Dónde está staging?

En este laboratorio, **staging es el GitHub Container Registry (GHCR)**. Cada imagen publicada representa una versión validada y lista para despliegue. Cualquier entorno (VM, servidor, máquina local) puede descargar esa imagen con:

```bash
docker pull ghcr.io/lyc4nthrope/flytrack:latest
docker run -p 8080:8080 ghcr.io/lyc4nthrope/flytrack:latest
```

Esto garantiza que el entorno de ejecución es idéntico al que pasó por el pipeline, eliminando el problema de "en mi máquina funciona".

## 6. Comparación: flujo anterior vs flujo propuesto

| Aspecto | Antes | Con el pipeline |
|---------|-------|-----------------|
| Pruebas | Manuales o inexistentes | 32 pruebas automáticas en cada push |
| Calidad | Sin medición | JaCoCo reporta cobertura por cada build |
| Empaquetado | Manual, variable | JAR reproducible por Maven |
| Imagen Docker | Construida a mano localmente | Construida y publicada por Actions |
| Despliegue | SSH + pasos manuales | `docker pull` + `docker run` desde GHCR |
| Trazabilidad | Ninguna | Cada imagen lleva el SHA del commit |

## 7. Herramientas del pipeline y su rol

| Herramienta | Rol en el pipeline |
|-------------|-------------------|
| GitHub Actions | Orquestador del pipeline (motor CI/CD) |
| `actions/setup-java@v4` | Configura JDK 17 con caché de Maven |
| `mvn verify` | Compila + prueba + genera reporte de cobertura |
| `mvn package` | Genera JAR ejecutable (Spring Boot fat JAR) |
| `actions/upload-artifact@v4` | Publica reportes y JAR para descarga |
| `docker/login-action@v3` | Autenticación en GHCR con GITHUB_TOKEN |
| `docker/build-push-action@v5` | Build multi-stage + push al registro |
| GHCR (`ghcr.io`) | Registro de imágenes como entorno staging |

## 8. Evaluación de herramientas sugeridas por el documento

El documento de la práctica menciona explícitamente: Git, GitHub Actions/GitLab CI/Jenkins, Docker, Kubernetes y SonarQube. A continuación se evalúa cada una:

### Git + GitHub
**Rol**: control de versiones y colaboración.
**Decisión**: implementado con estrategia Git Flow (ramas `main`, `develop`, `feature/*`, `release/*`, `hotfix/*`).
**Por qué**: estándar de la industria, integración nativa con GitHub Actions, trazabilidad completa de cambios.

### GitHub Actions
**Rol**: motor CI/CD.
**Decisión**: implementado. Pipeline de tres jobs: `test → build → docker-publish`.
**Por qué sobre GitLab CI / Jenkins**: no requiere servidor propio, está integrado al repositorio y es gratuito para proyectos públicos. Jenkins requeriría mantener un servidor; GitLab CI requeriría migrar el repositorio a GitLab.

### Docker
**Rol**: empaquetado reproducible de la aplicación.
**Decisión**: implementado con Dockerfile multi-stage y docker-compose.yml.
**Por qué**: elimina el problema de entornos variables entre desarrolladores. La imagen producida es idéntica en cualquier máquina.

### SonarQube
**Rol**: análisis estático de calidad del código (bugs potenciales, code smells, duplicación, deuda técnica).
**Decisión**: **no implementado en el pipeline**. Se usa JaCoCo como alternativa para métricas de cobertura.
**Por qué no**: SonarQube requiere un servidor propio (SonarQube Community) o una cuenta en SonarCloud. Para un laboratorio estudiantil sin infraestructura de servidor, esa dependencia introduce complejidad operacional que está fuera del alcance mínimo viable.
**Diferencia con JaCoCo**: JaCoCo mide *cuántas líneas ejecutaron las pruebas* (cobertura). SonarQube analiza *la calidad del código fuente* independientemente de las pruebas. Son complementarios, no equivalentes. En un pipeline de producción se usarían ambos.
**Cómo se integraría**: añadiendo `sonar:sonar` como goal de Maven en el job `test`, con `SONAR_TOKEN` como secreto de repositorio y apuntando a `sonar.host.url`.

### Kubernetes
**Rol**: orquestación de contenedores (escalado, self-healing, rolling updates, gestión de múltiples réplicas).
**Decisión**: **no implementado, fuera del alcance de este laboratorio**.
**Por qué no**: Kubernetes resuelve problemas de escala y disponibilidad en producción (múltiples instancias, balanceo de carga, recuperación automática de fallos). FlyTrack es una aplicación de laboratorio con un solo servicio sin requisitos de alta disponibilidad. Introducir Kubernetes agregaría complejidad operacional (cluster, namespaces, deployments, services, ingress) que no aporta valor demostrable en este contexto.
**Cuándo aplicaría**: si FlyTrack tuviera múltiples microservicios que necesitan escalar independientemente, o si el aeropuerto requiriera cero downtime durante despliegues. En ese caso, el `docker-publish` del pipeline sería reemplazado por un `kubectl rollout` o un ArgoCD sync.
