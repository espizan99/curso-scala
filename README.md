# Programación Funcional en Scala

Este repositorio contiene el material utilizado durante el curso de programación funcional en Scala impartido por Habla Computing.

En este documento también se recogen instrucciones relativas a la instalación de software, y otros aspectos necesarios para el correcto seguimiento del curso.

## Material del curso

Las explicaciones durante las sesiones del curso alternarán el uso de transparencias y ejemplos de programación "en vivo". Las transparencias estarán disponibles en los siguientes enlaces al comienzo de cada sesión:

* [Presentación](presentacion.pdf)
* [Tema 0. Introducción a Scala](tema0-scalaintro/IntroduccionAScala.pdf)
* Tema 1. ¿Qué es la programación funcional? HOFs
* Tema 2. TypeClasses
* Tema 3. Las funciones puras. Lenguajes
* Tema 4. El ecosistema funcional de Scala
* [Referencias](REFS.md)

Tanto el código "en vivo" como el historial de la consola de comandos se encontrarán también disponibles en este repositorio al término de cada sesión.

## Réplica y clonación del repositorio

Para facilitar las correcciones a los ejercicios propuestos durante el curso se recomienda hacer un [fork](https://help.github.com/articles/fork-a-repo/#fork-an-example-repository) de este repositorio en la cuenta Github del alumno, y clonar localmente vuestra propia versión del repositorio (instalando previamente [git](https://git-scm.com/)).

En este [documento](InstruccionesGithub.pdf) se explican paso a paso las actividades de configuración de vuestro repositorio, así como las operaciones de git que utilizaréis más comúnmente y el procedimiento para solicitar la corrección de ejercicios. 

## Instalación de software

#### Librerías de Scala y compilador

Prerrequisitos: es necesario que tengáis instalado Java en vuestra máquina. Recomendamos JDK (o JRE) versión 7 u 8.

Este repositorio contiene una copia de `sbt`, la herramienta de builds de Scala más común. Una vez clonado localmente el repositorio, mediante el siguiente comando se descargarán todas las dependencias necesarias para compilar los programas del curso (las librerías [scalaz](https://github.com/scalaz/scalaz/tree/v7.2.0-M5), [cats](https://github.com/non/cats/tree/v0.3.0), etc., y el propio compilador de Scala):  

```bash
$ cd funcourseinscala-singular
$ ./sbt ~update
```

#### Editores

Con respecto al editor, durante las sesiones del curso utilizaremos el editor [Sublime](http://www.sublimetext.com/), pero, por supuesto, podéis utilizar cualquier otro editor (Atom, VI, emacs) o IDE (eclipse, intellij, etc. - véase el paso 3 [aquí](http://www.scala-lang.org/download/)) de vuestra elección.

## Comunicación

#### Correo electrónico

El correo electrónico del coordinador del curso es: [juanmanuel.serrano@hablapps.com](mailto:juanmanuel.serrano@hablapps.com)

#### Chat del curso 

Para resolver dudas y fomentar la colaboración entre los alumnos, se dispone del siguiente chat moderado por el equipo de Habla Computing:

https://gitter.im/hablapps/funcourse-singular

#### Twitter

El hashtag "oficial" del curso es el siguiente: `#funcourseinscala`
