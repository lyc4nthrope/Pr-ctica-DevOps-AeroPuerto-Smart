# AeroPuerto Smart - Mi propuesta DevOps

## Descripcion del proyecto

En este repositorio presento mi propuesta de trabajo para el caso **AeroPuerto Smart**, tomando como base la aplicacion **FlyTrack**. Yo entiendo este laboratorio como un ejercicio donde no solo debo hablar del software, sino tambien de la forma en que el equipo lo desarrolla, lo prueba, lo integra y lo despliega.

La aplicacion FlyTrack permite consultar itinerarios, recibir notificaciones, ver puertas de embarque y reportar problemas con equipaje. El problema del caso no es que la aplicacion no exista, sino que el proceso actual para trabajarla es manual, lento, desordenado y propenso a errores.

## Que me pide realmente esta practica

Yo entiendo que esta practica no me pide solo instalar herramientas. Lo que realmente me pide es:

1. Entender el problema actual.
2. Explicar por que DevOps seria util.
3. Diseñar una forma mas ordenada de trabajar.
4. Implementar una version minima viable de esa mejora.
5. Documentar lo que hice y poder defenderlo.

En otras palabras, yo debo pasar de un proceso manual y desordenado a una propuesta organizada, trazable y automatizable.

## Preguntas del documento que estoy respondiendo

Estas son las preguntas o puntos centrales que yo estoy resolviendo en este `README`:

1. ¿De que trata la practica?
2. ¿Que es DevOps?
3. ¿Por que DevOps es importante en este caso?
4. ¿Que problemas actuales resuelve DevOps en AeroPuerto Smart?
5. ¿Que me pide realmente la practica?
6. ¿Como explico cada fase del laboratorio?
7. ¿Que herramientas menciona la practica y para que sirven?
8. ¿Cual seria una solucion razonable para este laboratorio?
9. ¿Que se va a trabajar?
10. ¿Que deberia entender yo despues de esta practica?
11. ¿Cual seria un flujo ideal para FlyTrack?
12. ¿Cuales podrian ser los entregables del laboratorio?
13. ¿Que cosas no deberia confundir dentro de la practica?
14. ¿Cual es mi resumen corto del laboratorio?
15. ¿Como organizo las ramas del proyecto de acuerdo con DevOps?

## 1. ¿De que trata esta practica?

Para mi, esta practica trata de analizar un caso donde existe una aplicacion util, pero el proceso de desarrollo y despliegue funciona mal. En AeroPuerto Smart hay demoras, errores en despliegue, poco control de versiones, falta de pruebas automatizadas e infraestructura configurada manualmente.

Yo resumiria el problema asi: el software existe, pero la forma de trabajarlo no esta bien organizada.

## 2. ¿Que es DevOps?

Yo explico DevOps como una forma de trabajo que busca unir desarrollo y operaciones para que el software se construya, pruebe, despliegue y mantenga de manera mas rapida, mas segura y mas ordenada.

Para mi, DevOps no es solo una herramienta. Es una combinacion de:

- organizacion del equipo
- automatizacion
- buenas practicas
- control de cambios
- colaboracion entre roles

En palabras simples, yo diria que DevOps sirve para trabajar mejor y hacer que el software sea mas confiable.

## 3. ¿Por que DevOps es importante en este caso?

Yo considero que DevOps es importante en AeroPuerto Smart porque FlyTrack afecta procesos que tienen impacto real en los pasajeros y en la operacion del aeropuerto. Un error aqui no es solo tecnico, tambien puede causar confusion, retrasos, mala informacion y mala imagen para la organizacion.

Por eso yo aplico DevOps en este caso:

- porque ayuda a publicar cambios sin improvisacion
- porque reduce el riesgo de errores en produccion
- porque acelera la entrega de mejoras
- porque obliga a ordenar mejor el trabajo
- porque mejora la coordinacion del equipo

## 4. ¿Que problemas actuales resuelve DevOps en AeroPuerto Smart?

Yo identifico estos problemas principales y asi entiendo que DevOps los ayuda a resolver:

### 4.1. Las actualizaciones tardan demasiado

Si las pruebas y despliegues se hacen manualmente, todo tarda mas. Yo veo que DevOps ayuda aqui mediante automatizacion para reducir tiempos y pasos repetitivos.

### 4.2. Cada despliegue puede provocar errores

Cuando alguien publica cambios manualmente, es facil olvidar algo o usar una configuracion incorrecta. Yo veo que DevOps reduce esto al definir un proceso repetible y controlado.

### 4.3. No hay control adecuado de versiones

Si no se organizan bien ramas, commits y cambios, despues es dificil saber quien hizo algo, cuando lo hizo y como volver atras. Yo entiendo que DevOps impulsa el uso correcto de Git y GitHub para dar trazabilidad.

### 4.4. No hay pruebas automatizadas

Si no se prueban los cambios de manera automatica, cualquier mejora puede romper algo que ya funcionaba. Por eso yo considero clave agregar pruebas dentro del flujo.

### 4.5. La infraestructura se configura manualmente

Si cada entorno se arma a mano, el sistema se vuelve fragil y dificil de repetir. Yo veo que DevOps ayuda a volver los entornos mas predecibles y replicables.

### 4.6. La comunicacion entre equipos es limitada

Yo tambien entiendo DevOps como una cultura de colaboracion. Si desarrollo, soporte y operaciones trabajan separados, el proceso se rompe con facilidad. Por eso documentar, compartir responsabilidades y centralizar el flujo tambien hace parte de la solucion.

## 5. ¿Como explico las fases del laboratorio?

### Fase 1: Investigacion y diagnostico

En esta fase yo debo entender el problema antes de construir nada. Aqui debo explicar que es DevOps, relacionarlo con el caso, describir el flujo actual de trabajo e identificar que practicas podrian mejorar la situacion.

Para mi, esta fase sirve para justificar por que la propuesta tiene sentido.

### Fase 2: Diseño del pipeline DevOps

Aqui yo ya no me limito a describir el problema, sino que propongo una solucion organizada. Yo entiendo el pipeline como una secuencia automatica de pasos que sigue el software desde que alguien hace un cambio hasta que ese cambio queda listo para usarse.

Las etapas minimas que yo incluiria son:

1. compilacion o construccion
2. pruebas automatizadas
3. analisis de calidad
4. despliegue automatizado

Tambien tengo claro que primero conviene publicar en `staging` y despues en `produccion`.

### Fase 3: Implementacion practica minima viable

En esta fase yo llevo la idea a algo funcional, aunque sea sencillo. Para mi, el minimo razonable incluye:

- repositorio con control de versiones
- pruebas unitarias automatizadas
- uso de contenedores con Docker
- una automatizacion basica de despliegue

### Fase 4: Informe y presentacion

Aqui yo debo demostrar que entiendo lo que hice. En el informe explicaria el problema inicial, el flujo actual, las herramientas elegidas, el pipeline propuesto, lo que logre automatizar y las limitaciones que todavia quedan.

## 6. ¿Que herramientas menciona la practica y para que sirven?

Estas son las herramientas que yo entiendo como mas importantes en el contexto del laboratorio:

- `Git`: lo uso para control de versiones.
- `GitHub`: lo uso para alojar el repositorio, colaborar y centralizar el trabajo.
- `GitHub Actions`, `GitLab CI` o `Jenkins`: los veo como opciones para automatizar el pipeline.
- `Docker`: lo uso para empaquetar la aplicacion en un contenedor.
- `Docker Compose`: me sirve si necesito levantar varios servicios juntos.
- `Kubernetes`: lo entiendo como una opcion mas avanzada para orquestacion, pero no la considero obligatoria para una practica minima viable.
- `SonarQube`: lo veo util para analisis de calidad del codigo.

## 7. ¿Cual seria una solucion razonable para este laboratorio?

Yo plantearia una solucion simple pero defendible:

1. Crear el repositorio del proyecto en GitHub.
2. Organizar el trabajo con ramas.
3. Agregar pruebas unitarias basicas.
4. Crear un `Dockerfile`.
5. Usar `docker-compose.yml` si hace falta levantar servicios relacionados.
6. Configurar un workflow de CI/CD.
7. Automatizar instalacion, pruebas, validacion y construccion.
8. Dejar documentado todo el proceso.

Para mi, eso ya demuestra bien la idea central de DevOps sin volver la practica innecesariamente compleja.

## 8. ¿Que se va a trabajar?

Yo explicaria que en esta practica se va a trabajar en el proceso de desarrollo y despliegue del software, no solo en programar una aplicacion.

Yo diria que se va a trabajar en:

- como se guarda el codigo
- como se validan los cambios
- como se prueban automaticamente
- como se empaqueta la aplicacion
- como se publica de forma controlada
- como se documenta el proceso

## 9. ¿Que deberia entender yo despues de esta practica?

Al terminar esta practica, yo deberia entender que:

- DevOps no es solo usar Docker o GitHub
- DevOps busca mejorar la forma de trabajar del equipo
- automatizar reduce errores
- probar antes de desplegar evita problemas
- documentar el proceso tambien es trabajo tecnico
- un buen pipeline ayuda a que el software sea mas estable

## 10. ¿Cual seria mi flujo ideal para FlyTrack?

Mi flujo ideal seria este:

1. Cada desarrollador hace cambios pequenos y claros.
2. Sube esos cambios al repositorio.
3. El sistema ejecuta pruebas automaticamente.
4. Se analiza la calidad del codigo.
5. Si todo esta bien, se construye la aplicacion.
6. Se empaqueta con Docker.
7. Se despliega primero a `staging`.
8. Si todo funciona bien, se aprueba el paso a produccion.

Yo veo este flujo como una mejora clara porque evita improvisacion, reduce errores y hace visible el estado del proyecto.

## 11. ¿Cuales podrian ser los entregables del laboratorio?

Yo organizaria los entregables asi:

- diagnostico del problema y del flujo actual
- diseño del pipeline CI/CD
- implementacion minima viable
- informe final
- presentacion

## 12. ¿Que cosas no debo confundir en esta practica?

Yo tengo claro que:

- DevOps no es solo programar
- DevOps no es solo una herramienta
- usar mas herramientas no significa tener una mejor solucion
- automatizar no significa perder control, sino ganar consistencia

## 13. ¿Cual es mi resumen corto del laboratorio?

Yo resumiria esta practica asi: debo tomar un caso con problemas de organizacion, calidad y despliegue, y convertirlo en una propuesta de trabajo mas ordenada, automatizada y confiable usando principios DevOps.

## 14. Estrategia de ramas que voy a usar

Para este proyecto voy a trabajar con una estructura de ramas inspirada en Git Flow, porque es facil de entender en un entorno academico y encaja bien con una practica DevOps donde necesito separar desarrollo, integracion, liberaciones y correcciones urgentes.

Ademas, no me sirve que estas ramas existan solo en mi equipo local. Tambien necesito que esten en GitHub, porque ahi es donde puedo centralizar el trabajo del grupo, revisar cambios, abrir pull requests y dejar trazabilidad real del proceso. Si las ramas no existen en GitHub, el flujo colaborativo queda incompleto.

### Ramas principales

- `main`: la uso para conservar la version estable del proyecto. Para mi, esta es la rama que representa lo que ya esta aprobado, organizado y listo para entregar o publicar, por eso no la uso como rama de trabajo diario.
- `develop`: la uso para integrar los cambios que ya pasaron por revision y pruebas basicas.

### Ramas de trabajo individual

- `feature/camilo`: la uso para los cambios individuales de Camilo.
- `feature/jeyson`: la uso para los cambios individuales de Jeyson.
- `feature/cristhian`: la uso para los cambios individuales de Cristhian.

### Ramas de soporte al flujo DevOps

- `release/preproduccion`: la uso para preparar una entrega antes de pasarla a `main`.
- `hotfix/produccion`: la uso para corregir errores criticos detectados en una version ya liberada.

## 15. ¿Por que cree estas ramas?

Yo decidi crear estas ramas porque cada una cumple una funcion concreta dentro del flujo DevOps:

- `main` me sirve para proteger la version estable y evitar mezclar trabajo incompleto con la entrega final
- `develop` me sirve como punto de integracion continua antes de publicar cambios
- las ramas `feature/*` me permiten que cada integrante trabaje por separado, con mejor trazabilidad y menos conflictos
- `release/preproduccion` me permite validar una entrega antes de considerarla lista para produccion
- `hotfix/produccion` me permite atender fallos urgentes sin alterar el flujo normal de desarrollo

En mi caso, la rama `main` no desaparece por tener varias ramas. Al contrario, se vuelve mas importante, porque es la que uso para guardar la version mas confiable del proyecto. Yo la dejo como referencia de estabilidad, mientras que el trabajo del dia a dia ocurre en las otras ramas.

Tambien decidi subirlas a GitHub porque asi el flujo no depende de un solo computador. De esa manera:

- cada integrante puede trabajar sobre su rama desde el remoto
- los cambios quedan visibles para todo el equipo
- puedo usar pull requests como parte del control de calidad
- la integracion en `develop` queda mejor organizada
- el historial del proyecto queda documentado de forma centralizada

## 16. ¿Como entiendo el flujo de trabajo con estas ramas?

Yo planteo el flujo asi:

1. Cada integrante trabaja en su propia rama `feature/*`.
2. Cuando termina una tarea, integra sus cambios en `develop`.
3. En `develop` se ejecutan validaciones, pruebas y revision general.
4. Cuando se va a preparar una entrega, se pasa a `release/preproduccion`.
5. Si todo queda correcto, la liberacion pasa a `main`.
6. Si aparece un error critico en una version liberada, se corrige desde `hotfix/produccion`.

Dicho mas simple, `main` es la rama donde dejo lo que ya considero terminado y estable. Las demas ramas existen para ayudarme a llegar a ese punto sin desordenar la version principal.

## 17. Relacion con CI/CD

Esta estructura de ramas tiene sentido para DevOps porque facilita automatizar acciones por entorno o por tipo de cambio. Por ejemplo:

- en `feature/*` puedo ejecutar validaciones rapidas
- en `develop` puedo ejecutar integracion continua
- en `release/*` puedo preparar pruebas de preproduccion
- en `main` puedo dejar listo el despliegue de la version estable
- en `hotfix/*` puedo acelerar correcciones urgentes

## Integrantes y ramas asignadas

- Camilo: `feature/camilo`
- Jeyson: `feature/jeyson`
- Cristhian: `feature/cristhian`

## Conclusion

En esta practica no solo quiero mostrar herramientas, sino demostrar que entiendo por que es importante ordenar el trabajo. Por eso documente tanto las respuestas del documento como la estrategia de ramas, todo en primera persona y alineado con DevOps.

## Fuente de contexto

Yo construyo esta explicacion a partir del documento de la practica **AeroPuerto Smart** de la Universidad del Quindio para responder sus preguntas principales con una propuesta propia.
