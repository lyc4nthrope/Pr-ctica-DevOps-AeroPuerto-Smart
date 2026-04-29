# Fase 4: Guía de presentación en clase

**Práctica DevOps: AeroPuerto Smart**
**Expositor**: Cristhian Osorio
**Tiempo estimado**: 15–20 minutos + preguntas

---

## Estructura de la exposición

### Slide 1 — Contexto: ¿cuál era el problema?

**Qué decir:**
AeroPuerto Smart tenía una aplicación funcional (FlyTrack) pero un proceso de desarrollo y despliegue completamente manual y desordenado. Las actualizaciones tardaban días, cada despliegue rompía algo, no había pruebas automáticas y nadie sabía exactamente qué versión estaba en producción.

**Punto clave para el profesor:** el problema no era el código, era el *proceso*.

---

### Slide 2 — ¿Qué es DevOps?

**Qué decir:**
DevOps no es una herramienta ni un cargo. Es una práctica que une a desarrollo (Dev) y operaciones (Ops) para entregar software de forma más rápida, más segura y con menos errores. Sus pilares son: colaboración, automatización, entrega continua y monitoreo.

**Analogía útil:** es como pasar de una cocina donde cada cocinero trabaja solo y entrega un plato diferente, a una línea de producción donde cada paso está definido, verificado y documentado.

---

### Slide 3 — Diagnóstico del flujo actual (simulado)

**Qué decir:**
Antes de DevOps, el equipo de FlyTrack trabajaba así:

1. El desarrollador modifica código en local.
2. Comparte cambios manualmente (sin Git consistente).
3. Integración informal.
4. Prueba manual si hay tiempo.
5. Despliegue directo a producción.
6. Correcciones reactivas cuando algo falla.

**Resultado:** ciclos de días, errores en producción, nula trazabilidad.

---

### Slide 4 — Solución: el pipeline CI/CD implementado

**Qué decir:**
Se diseñó e implementó un pipeline de tres etapas en GitHub Actions:

```
[test] → [build] → [docker-publish]
```

- **test**: ejecuta las 32 pruebas automáticas y genera reporte de cobertura JaCoCo.
- **build**: empaqueta la aplicación en un JAR ejecutable.
- **docker-publish**: construye la imagen Docker y la publica en GitHub Container Registry como staging.

Si cualquier prueba falla, el pipeline se detiene. Nada llega a staging sin pasar por las pruebas.

---

### Slide 5 — Demo en vivo (mostrar en pantalla)

**Orden sugerido:**

1. Abrir el repositorio en GitHub y mostrar la pestaña **Actions**.
2. Mostrar la estructura de jobs: `test → build → docker-publish`.
3. Mostrar los artefactos publicados: reporte Surefire, reporte JaCoCo, JAR.
4. Mostrar la imagen en **GitHub Container Registry** (`ghcr.io/lyc4nthrope/flytrack`).
5. En terminal local, ejecutar:
   ```bash
   docker compose up -d
   curl http://localhost:8080/api/vuelos/1
   curl http://localhost:8080/api/puertas/AV101
   curl -X POST http://localhost:8080/api/equipaje/reporte \
     -H "Content-Type: application/json" \
     -d '{"pasajero":"Juan","vuelo":"AV101","descripcion":"Maleta perdida"}'
   docker compose down
   ```
6. Mostrar el reporte JaCoCo (abrir `target/site/jacoco/index.html` en el navegador).

---

### Slide 6 — Estrategia de ramas (Git Flow)

**Qué decir:**
El repositorio usa Git Flow para que el trabajo de cada integrante sea trazable y no interfiera con los demás:

```
main              ← producción estable
develop           ← integración del equipo
feature/cristhian ← mi trabajo
feature/camilo    ← trabajo de Camilo
feature/jeyson    ← trabajo de Jeyson
release/*         ← preparación de versiones
hotfix/*          ← correcciones urgentes
```

Cada feature se integra a `develop` con `merge --no-ff` para que el historial muestre exactamente qué se integró y cuándo.

---

### Slide 7 — Herramientas utilizadas y evaluadas

**Implementadas:**

| Herramienta | Para qué |
|-------------|----------|
| Git + GitHub | Control de versiones y colaboración |
| GitHub Actions | Motor del pipeline CI/CD |
| JUnit 5 + MockMvc | Pruebas unitarias e integración web |
| JaCoCo | Cobertura de código (87% instrucciones) |
| Docker multi-stage | Empaquetado reproducible |
| GHCR | Registro de imágenes (staging) |

**Evaluadas y descartadas para este alcance:**

| Herramienta | Por qué no se implementó |
|-------------|--------------------------|
| SonarQube | Requiere servidor o cuenta SonarCloud; JaCoCo cubre el concepto mínimo de calidad |
| Kubernetes | Resuelve escala y alta disponibilidad; FlyTrack es un servicio único sin esos requisitos |

---

### Slide 8 — Ventajas de DevOps: antes vs después

| Aspecto | Antes | Después |
|---------|-------|---------|
| Pruebas | 0, manuales | 32 automáticas en cada push |
| Calidad | Sin medición | 87% cobertura por JaCoCo |
| Despliegue | Manual a producción | Automático a staging via GHCR |
| Empaquetado | Variable entre máquinas | Imagen Docker reproducible |
| Trazabilidad | Ninguna | Cada imagen lleva el SHA del commit |
| Control de versiones | Informal | Git Flow con ramas por integrante |

---

### Slide 9 — Lecciones aprendidas

1. **DevOps es proceso, no herramientas.** Instalar Docker no es hacer DevOps. Lo importante es definir qué pasa desde que se escribe una línea de código hasta que llega al usuario.

2. **La arquitectura afecta la testeabilidad.** Usar constructor injection y `Optional<T>` hizo que las pruebas fueran más simples y predecibles.

3. **Docker multi-stage no es opcional en producción.** Separar la etapa de build de la de runtime hace las imágenes más pequeñas, más seguras y más rápidas de desplegar.

4. **El pipeline como red de seguridad.** Cada push que rompe una prueba es detectado antes de llegar a staging. Eso vale más que cualquier revisión manual.

5. **Git Flow da visibilidad al equipo.** Cuando el historial muestra `merge feature/cristhian → develop`, cualquier integrante puede entender qué se integró, cuándo y por quién, sin preguntar.

---

## Preguntas frecuentes del profesor

**¿Por qué no usaron SonarQube si el documento lo menciona?**
> SonarQube requiere un servidor o cuenta externa con credenciales. Para el alcance mínimo viable del laboratorio, JaCoCo genera métricas de cobertura suficientes para demostrar el concepto de calidad en el pipeline. La diferencia es que JaCoCo mide cobertura (qué ejecutaron las pruebas) y SonarQube hace análisis estático (calidad del código independientemente de las pruebas). Ambas son complementarias; aquí implementamos la que no requiere infraestructura adicional.

**¿Por qué no usaron Kubernetes?**
> Kubernetes resuelve orquestación de múltiples contenedores con escala y alta disponibilidad. FlyTrack es un servicio único sin requisitos de escalado. Usar Kubernetes agregaría complejidad operacional (cluster, namespaces, manifiestos) sin aportar valor demostrable en este contexto. El concepto de despliegue controlado está cubierto con Docker + GHCR.

**¿Qué pasa si una prueba falla en el pipeline?**
> El job `test` falla y los jobs `build` y `docker-publish` no se ejecutan. Nada llega a staging. El desarrollador recibe notificación en GitHub y debe corregir antes de que el código pueda avanzar en el pipeline.

**¿Cómo garantizan que la imagen en staging es la misma que pasó las pruebas?**
> Cada imagen se publica con dos tags: `latest` y el SHA del commit exacto (`ghcr.io/lyc4nthrope/flytrack:<SHA>`). El SHA es inmutable, por lo que siempre es posible determinar qué versión del código corresponde a qué imagen.

**¿Por qué no hay base de datos?**
> El laboratorio es de DevOps, no de persistencia de datos. Agregar una base de datos habría requerido configurar docker-compose con múltiples servicios, variables de entorno, volúmenes y migraciones, todo lo cual distrae del objetivo principal: demostrar el ciclo CI/CD.
