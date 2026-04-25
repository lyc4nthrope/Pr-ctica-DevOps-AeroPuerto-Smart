# AeroPuerto Smart - Mi propuesta DevOps

## Descripcion del proyecto

En este repositorio presento mi propuesta de trabajo para el caso **AeroPuerto Smart**, tomando como base la aplicacion **FlyTrack**. Mi enfoque no se centra solo en el software, sino en mejorar la forma en que el equipo desarrolla, prueba, integra y despliega los cambios.

Desde mi punto de vista, el problema principal del caso no es que falte una aplicacion, sino que el proceso actual es manual, desordenado y riesgoso. Por eso planteo una estrategia basada en practicas DevOps para tener mas control, mas trazabilidad y menos errores durante la entrega.

## Que busco resolver

Con esta propuesta busco mejorar estos puntos:

- Reducir errores en los despliegues.
- Tener mejor control de versiones.
- Separar el trabajo de cada integrante sin perder integracion.
- Facilitar pruebas e integracion continua.
- Dejar una base clara para automatizar CI/CD.

## Por que aplico DevOps en este caso

Yo aplico DevOps en este laboratorio porque necesito que el flujo de trabajo sea repetible, claro y facil de mantener. En un caso como AeroPuerto Smart, donde la informacion de vuelos y equipaje impacta la operacion y la experiencia del pasajero, no conviene trabajar con cambios desorganizados o subidos directamente a produccion.

DevOps me permite organizar mejor el ciclo completo:

- desarrollo
- integracion
- validacion
- despliegue
- correccion de incidentes

## Estrategia de ramas que voy a usar

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

## Por que cree estas ramas

Decidi crear estas ramas porque cada una cumple una funcion concreta dentro del flujo DevOps:

- `main` me sirve para proteger la version estable y evitar mezclar trabajo incompleto con la entrega final.
- `develop` me sirve como punto de integracion continua antes de publicar cambios.
- Las ramas `feature/*` me permiten que cada integrante trabaje por separado, con mejor trazabilidad y menos conflictos.
- `release/preproduccion` me permite validar una entrega antes de considerarla lista para produccion.
- `hotfix/produccion` me permite atender fallos urgentes sin alterar el flujo normal de desarrollo.

En mi caso, la rama `main` no desaparece por tener varias ramas. Al contrario, se vuelve mas importante, porque es la que uso para guardar la version mas confiable del proyecto. Yo la dejo como referencia de estabilidad, mientras que el trabajo del dia a dia ocurre en las otras ramas.

Tambien decidi subirlas a GitHub porque asi el flujo no depende de un solo computador. De esa manera:

- cada integrante puede trabajar sobre su rama desde el remoto
- los cambios quedan visibles para todo el equipo
- puedo usar pull requests como parte del control de calidad
- la integracion en `develop` queda mejor organizada
- el historial del proyecto queda documentado de forma centralizada

## Como entiendo el flujo de trabajo

Yo planteo el flujo asi:

1. Cada integrante trabaja en su propia rama `feature/*`.
2. Cuando termina una tarea, integra sus cambios en `develop`.
3. En `develop` se ejecutan validaciones, pruebas y revision general.
4. Cuando se va a preparar una entrega, se pasa a `release/preproduccion`.
5. Si todo queda correcto, la liberacion pasa a `main`.
6. Si aparece un error critico en una version liberada, se corrige desde `hotfix/produccion`.

En otras palabras, yo no cree estas ramas solo por orden. Las cree para que el proyecto tenga una estructura de trabajo mas profesional, mas controlada y mas alineada con DevOps tanto en local como en GitHub.

Dicho mas simple, `main` es la rama donde dejo lo que ya considero terminado y estable. Las demas ramas existen para ayudarme a llegar a ese punto sin desordenar la version principal.

## Relacion con CI/CD

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

En esta practica no solo quiero mostrar herramientas, sino demostrar que entiendo por que es importante ordenar el trabajo. Por eso documente una estrategia de ramas simple, clara y alineada con DevOps, donde cada integrante tiene su espacio de trabajo y al mismo tiempo existe un camino controlado para integrar, validar y liberar cambios.
