# AeroPuerto Smart - Guía del laboratorio DevOps

## 1. ¿De qué trata esta práctica?

Esta práctica parte de un caso simulado llamado **AeroPuerto Smart**, un aeropuerto regional que quiere modernizar sus procesos tecnológicos.

La aplicación principal del caso se llama **FlyTrack**. Esta aplicación web permite que los pasajeros puedan:

- Consultar itinerarios de vuelo.
- Recibir notificaciones sobre cambios.
- Ver la puerta de embarque.
- Reportar problemas con el equipaje.

El problema no es que la aplicación no exista, sino que **el proceso para desarrollarla, probarla y publicarla funciona mal**.

Actualmente, según el documento de la práctica:

- Las actualizaciones tardan días en llegar a producción.
- Los despliegues generan fallos inesperados.
- No hay buen control de versiones.
- No hay pruebas automatizadas.
- La infraestructura se configura a mano.
- La comunicación entre desarrollo, soporte y operaciones es deficiente.

En palabras simples: **el software existe, pero la forma de trabajarlo está desordenada, es lenta y genera errores**.

Por eso aparece el tema central de la práctica: **DevOps**.

---

## 2. ¿Qué es DevOps?

**DevOps** es una forma de trabajar que busca unir mejor a las personas que desarrollan el software (**Dev**, de development) con las personas que lo despliegan, mantienen y operan (**Ops**, de operations).

No es solo una herramienta ni un programa.

Es una combinación de:

- Forma de trabajo.
- Organización del equipo.
- Automatización.
- Buenas prácticas.
- Uso de herramientas que reducen errores humanos.

La idea principal de DevOps es esta:

> Que el software se pueda construir, probar, desplegar y mantener de manera más rápida, más segura y más ordenada.

### Explicado sin tecnicismo

Sin DevOps, muchas veces un proyecto funciona así:

- Un desarrollador cambia algo.
- Otro lo mezcla con más cambios.
- Nadie prueba bien.
- Alguien sube el sistema manualmente.
- Algo falla.
- Nadie sabe exactamente qué cambio dañó el sistema.

Con DevOps se busca algo mucho más controlado:

- Cada cambio queda registrado.
- El código se prueba automáticamente.
- La aplicación se empaqueta de forma consistente.
- El despliegue sigue pasos definidos.
- El equipo puede detectar errores antes de que lleguen al usuario.

En resumen:

**DevOps sirve para trabajar mejor y para que el software sea más confiable.**

---

## 3. ¿Por qué DevOps es importante en este caso?

En AeroPuerto Smart, la aplicación FlyTrack afecta procesos importantes para pasajeros y operación aérea.

Eso significa que un fallo no es “solo un error técnico”. También puede causar:

- Confusión en pasajeros.
- Mala atención al cliente.
- Retrasos en operación.
- Información incorrecta sobre vuelos.
- Mala imagen para el aeropuerto.

Por eso DevOps es importante aquí:

- Porque ayuda a publicar cambios sin improvisación.
- Porque reduce el riesgo de errores en producción.
- Porque acelera la entrega de mejoras.
- Porque obliga a documentar y ordenar mejor el trabajo.
- Porque mejora la coordinación entre personas técnicas y no técnicas.

Si lo ponemos en una frase:

**DevOps es importante porque permite que FlyTrack evolucione sin volverse inestable ni caótica.**

---

## 4. ¿Qué problemas actuales resuelve DevOps en AeroPuerto Smart?

### 4.1. Las actualizaciones tardan demasiado

Hoy las actualizaciones toman días.

DevOps ayuda a resolver esto mediante automatización. Si el proceso de probar y desplegar deja de hacerse manualmente, los cambios pueden salir mucho más rápido.

### 4.2. Cada despliegue provoca errores

Cuando un despliegue se hace manualmente, es común olvidar pasos, usar versiones incorrectas o configurar algo mal.

DevOps reduce eso porque define un proceso repetible y controlado.

### 4.3. No hay control adecuado de versiones

Si el equipo no usa bien un repositorio o no organiza ramas, commits y cambios, es difícil saber:

- Quién cambió qué.
- Cuándo se cambió.
- Qué versión está en producción.
- Cómo volver atrás si algo sale mal.

DevOps impulsa el uso correcto de herramientas como Git.

### 4.4. No hay pruebas automatizadas

Sin pruebas automáticas, cada cambio nuevo puede romper algo que ya funcionaba.

DevOps propone que el sistema se pruebe solo cada vez que haya cambios importantes.

### 4.5. La infraestructura se configura manualmente

Eso vuelve el sistema frágil.

Si una persona instala algo “a su manera”, luego otra persona puede no lograr repetir el entorno igual.

DevOps intenta que los entornos sean más predecibles y replicables.

### 4.6. La comunicación entre equipos es limitada

Muchas veces desarrollo culpa a operaciones, operaciones culpa a soporte y nadie trabaja como un solo equipo.

DevOps también es cultura de colaboración:

- Compartir responsabilidad.
- Hacer visible el proceso.
- Documentar.
- Coordinar mejor.

---

## 5. Entonces, ¿qué te está pidiendo realmente esta práctica?

La práctica no te está pidiendo solo “instalar herramientas”.

Lo que realmente te pide es:

1. Entender el problema actual.
2. Explicar por qué DevOps sería útil.
3. Diseñar una forma más ordenada de trabajar.
4. Implementar una versión básica de esa mejora.
5. Documentar lo que hiciste y defenderlo en una presentación.

En otras palabras:

**Debes pasar de un proceso desordenado y manual a una propuesta mínima, organizada y automatizada.**

---

## 6. Explicación de cada fase del laboratorio

## Fase 1: Investigación y diagnóstico

Esta fase busca que entiendas el problema antes de construir nada.

### ¿Qué debes hacer aquí?

- Explicar qué es DevOps.
- Relacionar DevOps con el caso de AeroPuerto Smart.
- Describir cómo sería el flujo actual de trabajo, aunque sea simulado.
- Identificar qué prácticas y herramientas podrían mejorar la situación.

### ¿Qué significa “diagnóstico del flujo actual”?

Significa describir cómo se imagina que hoy trabaja el equipo.

Por ejemplo, un diagnóstico simple y coherente podría decir:

1. Un desarrollador hace cambios en su equipo.
2. Comparte archivos o sube cambios sin una estrategia clara.
3. No hay pruebas automáticas.
4. El despliegue se hace manualmente.
5. Si algo falla, se corrige en el momento.
6. No hay documentación suficiente del proceso.

### ¿Para qué sirve esta fase?

Sirve para justificar por qué necesitas DevOps.

Si no explicas bien el problema actual, el resto del laboratorio se ve como una lista de herramientas sin sentido.

---

## Fase 2: Diseño del pipeline DevOps

Aquí ya no solo explicas el problema, sino que propones una solución organizada.

### ¿Qué es un pipeline?

Un **pipeline** es una secuencia de pasos automáticos que sigue el software desde que alguien hace un cambio hasta que ese cambio queda listo para usarse.

Piensa en una línea de producción:

- Entra código nuevo.
- Se revisa.
- Se prueba.
- Se valida.
- Se publica.

Eso es un pipeline, pero aplicado al software.

### ¿Qué te piden diseñar?

Un pipeline básico de **CI/CD** para FlyTrack.

### ¿Qué significa CI/CD?

**CI** significa *Integración Continua*.

Quiere decir que cada vez que alguien agrega cambios al proyecto, esos cambios se integran de manera ordenada y se validan automáticamente.

**CD** significa *Entrega Continua* o *Despliegue Continuo*.

Quiere decir que, después de validar el software, este puede quedar listo para publicarse o incluso publicarse automáticamente.

### ¿Qué etapas debería tener tu pipeline?

El documento pide incluir al menos estas:

1. **Compilación o construcción**
   Aquí se prepara la aplicación para ejecutarse.

2. **Pruebas automatizadas**
   Se valida que lo más importante del sistema siga funcionando.

3. **Análisis de calidad**
   Se revisa el código para detectar malas prácticas, errores comunes o problemas de mantenimiento.

4. **Despliegue automático**
   Si todo sale bien, la aplicación se publica en un entorno controlado.

### ¿Qué es staging y qué es producción?

- **Staging**: es un entorno de prueba muy parecido al real.
- **Producción**: es el entorno real que usaría el usuario final.

Lo ideal es que primero se publique en staging y, si todo está bien, luego en producción.

### ¿Para qué sirve esta fase?

Sirve para mostrar que entiendes cómo convertir una idea de DevOps en un flujo real de trabajo.

---

## Fase 3: Implementación práctica mínima viable

Esta es la parte donde llevas la idea a algo funcional, aunque sea sencillo.

La práctica pide una **implementación mínima viable**, no una plataforma empresarial compleja.

### ¿Qué deberías construir como mínimo?

#### 1. Repositorio con control de versiones

Debes tener el proyecto en un repositorio, por ejemplo con Git y GitHub.

¿Para qué sirve?

- Guardar historial de cambios.
- Saber quién hizo cada cambio.
- Recuperar versiones anteriores.
- Trabajar de manera más ordenada.

#### 2. Pruebas unitarias automatizadas

Debes agregar pruebas automáticas para partes importantes del proyecto.

¿Para qué sirven?

- Detectar errores temprano.
- Evitar que una mejora dañe otra parte.
- Dar confianza antes de desplegar.

#### 3. Uso de contenedores

La práctica menciona contenedores, por ejemplo con Docker.

¿Para qué sirven?

- Empaquetar la aplicación con lo necesario para ejecutarse.
- Evitar el típico problema de “en mi computador sí funciona”.
- Facilitar despliegues más consistentes.

#### 4. Despliegue automatizado

Debes automatizar la publicación a un entorno controlado.

El documento sugiere ejemplos como:

- GitHub Actions.
- Docker Compose.
- Una máquina virtual.
- Un servicio en la nube.

No necesitas montar una infraestructura gigantesca.

Lo importante es demostrar que:

- El proceso ya no es totalmente manual.
- Hay una secuencia clara.
- El sistema se puede construir y desplegar con menos intervención humana.

---

## Fase 4: Informe y presentación

Aquí debes demostrar que entiendes lo que hiciste.

### El informe técnico debería explicar:

- El problema inicial.
- El diagnóstico del flujo actual.
- Por qué elegiste ciertas herramientas.
- Cómo diseñaste el pipeline.
- Qué lograste automatizar.
- Qué limitaciones quedaron.
- Qué aprendiste.

### La presentación debería mostrar:

- Qué es DevOps en este caso.
- Qué problemas tenía AeroPuerto Smart.
- Cómo funciona tu pipeline.
- Qué mejora frente al proceso anterior.
- Qué aprendió el equipo con esta práctica.

---

## 7. ¿Qué herramientas menciona la práctica y para qué sirven?

La guía menciona varias herramientas. No significa que debas usar todas al mismo tiempo, pero sí debes entender su función.

### Git

Sirve para llevar control de versiones del código.

Te ayuda a registrar cambios, volver atrás y trabajar de forma ordenada.

### GitHub

Sirve para alojar el repositorio y colaborar en línea.

Además puede integrarse con automatizaciones.

### GitHub Actions / GitLab CI / Jenkins

Sirven para automatizar el pipeline.

Por ejemplo:

- Ejecutar pruebas.
- Revisar calidad.
- Construir la aplicación.
- Desplegarla.

### Docker

Sirve para empaquetar la aplicación en un contenedor.

Eso facilita que el mismo software funcione de forma similar en distintos entornos.

### Docker Compose

Sirve para levantar varios servicios relacionados de manera coordinada.

Por ejemplo:

- Aplicación web.
- Base de datos.
- Servicio auxiliar.

### Kubernetes

Es una herramienta más avanzada para orquestar contenedores.

Puede mencionarse como referencia, pero para una práctica mínima viable normalmente **no es obligatorio** usarlo si el alcance no lo necesita.

### SonarQube

Sirve para analizar calidad de código.

Puede ayudar a detectar:

- Código duplicado.
- Errores frecuentes.
- Problemas de mantenibilidad.

---

## 8. ¿Qué sería una solución razonable para este laboratorio?

Una solución simple, coherente y defendible podría ser esta:

1. Crear el repositorio de FlyTrack en GitHub.
2. Organizar el trabajo con ramas.
3. Agregar pruebas unitarias básicas.
4. Crear un `Dockerfile` para empaquetar la aplicación.
5. Crear un `docker-compose.yml` si necesitas levantar servicios juntos.
6. Configurar un workflow en GitHub Actions.
7. Hacer que ese workflow:
   - Instale dependencias.
   - Ejecute pruebas.
   - Verifique calidad básica.
   - Construya la imagen Docker.
   - Despliegue a staging si todo pasa.
8. Documentar todo en el informe y mostrarlo en la exposición.

Esto ya demuestra la idea central de DevOps sin volver el laboratorio innecesariamente complejo.

---

## 9. ¿Qué deberías explicar cuando te pregunten “qué se va a trabajar”?

Puedes explicarlo así:

### Se va a trabajar en el proceso de desarrollo y despliegue del software

No solo en programar una aplicación.

Se va a trabajar en:

- Cómo se guarda el código.
- Cómo se validan los cambios.
- Cómo se prueban automáticamente.
- Cómo se empaqueta la aplicación.
- Cómo se publica de forma controlada.
- Cómo se documenta el proceso.

### ¿Por qué se va a trabajar eso?

Porque el problema del caso no es solo funcional.

El problema principal es que el equipo trabaja de forma manual, lenta y riesgosa.

### ¿Para qué sirve trabajar eso?

Sirve para lograr:

- Entregas más rápidas.
- Menos errores.
- Más orden.
- Mejor trazabilidad.
- Mejor comunicación entre roles.

---

## 10. ¿Qué deberías entender tú, como estudiante, después de esta práctica?

Al terminar esta práctica deberías poder entender que:

- DevOps no es solo “usar Docker” o “usar GitHub”.
- DevOps busca mejorar la forma de trabajar del equipo.
- Automatizar no es un lujo, sino una manera de reducir errores.
- Probar antes de desplegar ahorra problemas.
- Documentar el proceso es parte del trabajo técnico.
- Un buen pipeline ayuda a que el software sea más estable.

---

## 11. Propuesta de flujo ideal para FlyTrack

Este podría ser el flujo que quieres proponer como mejora:

1. El desarrollador hace cambios pequeños y claros.
2. Sube esos cambios al repositorio.
3. El sistema ejecuta pruebas automáticamente.
4. Se analiza la calidad del código.
5. Si todo está bien, se construye la aplicación.
6. Se empaqueta con Docker.
7. Se despliega primero a staging.
8. Si staging funciona bien, se aprueba el paso a producción.

### ¿Qué mejora este flujo?

- Evita improvisación.
- Reduce errores humanos.
- Hace visible el estado del proyecto.
- Permite detectar fallos antes de afectar al usuario final.

---

## 12. Posibles entregables del laboratorio

Si quieres organizar bien el trabajo, los entregables podrían quedar así:

### Entregable 1: Diagnóstico

Documento o sección donde expliques:

- Qué es DevOps.
- Qué problemas tiene AeroPuerto Smart.
- Cómo es el flujo actual.
- Qué se debe mejorar.

### Entregable 2: Diseño del pipeline

Diagrama o explicación por pasos del pipeline CI/CD.

### Entregable 3: Implementación mínima

Repositorio con:

- Control de versiones.
- Pruebas automatizadas.
- Docker.
- Workflow de automatización.

### Entregable 4: Informe final

Documento técnico explicando decisiones, resultados y aprendizajes.

### Entregable 5: Presentación

Explicación oral del problema, la solución y el funcionamiento del pipeline.

---

## 13. Qué no deberías confundir en esta práctica

Hay varias ideas que conviene separar:

### DevOps no es solo programar

También incluye despliegue, pruebas, automatización, documentación y colaboración.

### DevOps no es solo una herramienta

Las herramientas ayudan, pero lo importante es el proceso que construyes.

### Más herramientas no significa mejor solución

Una solución simple, bien explicada y funcional vale más que una solución llena de herramientas mal entendidas.

### Automatizar no significa perder control

Al contrario: automatizar bien significa tener más consistencia y menos improvisación.

---

## 14. Resumen corto del laboratorio

Este laboratorio busca que tomes un caso de software con problemas de organización, despliegue y calidad, y propongas una manera moderna de trabajarlo usando principios DevOps.

Tu tarea principal es:

- Entender el problema.
- Explicar DevOps de forma aplicada al caso.
- Diseñar un pipeline de CI/CD.
- Implementar una versión mínima con automatización.
- Documentar y presentar el resultado.

Si lo dijéramos de la manera más simple posible:

**El laboratorio trata de aprender a desarrollar y publicar software de forma más ordenada, confiable y automática.**

---

## 15. Fuente de contexto utilizada

Esta guía fue construida a partir del documento:

**Práctica DevOps: AeroPuerto Smart**
Universidad del Quindio
Facultad de Ingeniería
Programa de Ingeniería de Sistemas y Computación
Ingeniería de Software III

