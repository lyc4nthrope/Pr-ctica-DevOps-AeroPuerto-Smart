# Fase 1: Investigación y diagnóstico — AeroPuerto Smart

## 1. ¿Qué es DevOps?

DevOps es una práctica que une a los equipos de desarrollo (Dev) y operaciones (Ops) para entregar software de forma más rápida, segura y confiable. No es una herramienta: es una combinación de cultura, automatización y buenas prácticas que reduce los tiempos entre escribir código y ponerlo en producción.

Sus pilares son:

- **Colaboración**: Dev y Ops comparten responsabilidad del ciclo de vida del software.
- **Automatización**: pruebas, construcción y despliegue se ejecutan sin intervención manual.
- **Entrega continua**: los cambios fluyen de forma incremental y verificada hacia producción.
- **Monitoreo**: el sistema en producción se observa para detectar fallos rápidamente.

## 2. Diagnóstico del flujo actual en AeroPuerto Smart

### 2.1 ¿Cómo trabaja hoy el equipo?

El equipo de FlyTrack opera sin un proceso formal de integración ni despliegue. El flujo actual se puede describir así:

1. Un desarrollador escribe o modifica código en su máquina local.
2. Comparte los cambios manualmente (ZIP, correo o copia directa al servidor).
3. Otro desarrollador o el líder técnico integra los cambios sin herramienta de control de versiones consistente.
4. No existen pruebas automáticas: la validación es manual y depende de quien tenga tiempo.
5. El despliegue se realiza manualmente sobre el servidor de producción.
6. Si algo falla, se corrige directamente en producción ("hotfix manual").
7. No hay registro claro de qué versión está activa ni quién hizo cada cambio.

### 2.2 Problemas identificados

| Problema | Impacto |
|----------|---------|
| Sin control de versiones estructurado | Imposible rastrear cambios ni volver atrás |
| Sin pruebas automáticas | Un cambio puede romper funcionalidades existentes sin que nadie lo note |
| Despliegue manual | Propenso a errores humanos y configuraciones inconsistentes |
| Sin separación de entornos | Los cambios no probados van directo a producción |
| Comunicación informal entre roles | Desarrollo, soporte y operaciones no coordinan, se culpan mutuamente |
| Sin empaquetado reproducible | "En mi máquina funciona" es la respuesta habitual ante fallos |

### 2.3 Consecuencias concretas para el aeropuerto

- Las actualizaciones tardan días porque cada paso es manual.
- Cada despliegue genera fallos inesperados al saltar pasos o usar versiones equivocadas.
- Cuando algo falla en producción, nadie sabe con certeza qué cambio lo causó.
- Los pasajeros reciben información incorrecta sobre vuelos y puertas de embarque.

## 3. ¿Por qué DevOps resuelve estos problemas?

| Problema actual | Solución DevOps |
|-----------------|-----------------|
| Sin control de versiones | Git + estrategia de ramas (Git Flow) |
| Sin pruebas automáticas | Suite de pruebas unitarias ejecutadas en CI |
| Despliegue manual y riesgoso | Pipeline CI/CD automatizado (GitHub Actions) |
| Entorno irrepetible | Docker: empaquetado reproducible |
| Sin staging | GHCR como registro de imagen para staging |
| Comunicación informal | Flujo de trabajo visible en GitHub (PRs, commits, pipeline status) |

## 4. Herramientas seleccionadas y justificación

| Herramienta | Función | Por qué esta |
|-------------|---------|--------------|
| **Git + GitHub** | Control de versiones y colaboración | Estándar de la industria, integración nativa con Actions |
| **GitHub Actions** | Pipeline CI/CD | Sin servidor propio, integrado al repositorio, gratuito para proyectos públicos |
| **Maven** | Construcción y gestión de dependencias | Estándar para proyectos Spring Boot |
| **JaCoCo** | Cobertura de pruebas | Plugin nativo de Maven, genera reporte HTML sin servidor externo |
| **Docker** | Empaquetado de la aplicación | Elimina dependencias de entorno, imagen reproducible |
| **GHCR** | Registro de imágenes (staging) | Integrado a GitHub, autenticación con GITHUB_TOKEN sin secretos adicionales |
| **Spring Boot 3.2.5** | Framework de la aplicación | Convención sobre configuración, arranque rápido |

## 5. Conclusión

El flujo actual de AeroPuerto Smart es manual, lento y propenso a errores. DevOps propone reemplazarlo con un proceso automatizado donde cada cambio al código desencadena verificación, empaquetado y despliegue de forma consistente. La propuesta no requiere infraestructura costosa: GitHub Actions + Docker + GHCR cubren el ciclo completo de CI/CD para el alcance de este laboratorio.
