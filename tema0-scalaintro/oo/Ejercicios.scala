package org.hablapps.scalaintro.oo

object Ejercicios extends App {

  object Ejercicio1 {
    // Extiende el trait Fruta, añadiendo un método 'precio' sin implementación
    // asociada, que no recibe ningún argumento y que devuelve un Double como
    // salida. Toma el método `color` como referencia.
    trait Fruta {
      def color: String
      def precio: Double
    }
  }

  import Ejercicio1._

  object Ejercicio2 {
    // A continuación aparece el Companion Object para el trait `Fruta` que
    // creamos en el primer ejercicio. Implementa el método `crear`, que crea
    // una `Fruta`, valiéndose del `_color` recibido como argumento y fijando el
    // precio a 3.0. Recuerda que un trait no tiene constructores, por lo que
    // tendrás que utilizar la sintaxis de clases anónimas para poder crear una
    // instancia. `new Fruta { ... }`
    object Fruta {
      def crear(_color: String): Fruta = new Fruta {
        val color :String = _color
        val precio: Double = 3.0
        /** otra implementacion
        override val color: String = _color
        override def precio: Double = 3.0
        * 
        */
      }
    }
  }

  import Ejercicio2._

  object Ejercicio3 {
    // Extiende la clase `Platano`, para que herede de `Fruta`, fijando el
    // método `color` a "amarillo" y recibiendo el precio en el constructor.

    class Platano(val precio: Double) extends Fruta{
      val color: String = "amarillo"
    }
    /** otra impl
    class Platano(val precio: Double) extends Fruta {
      def color: String = "amarillo"
    }
    * 
    */
  }

//Con el '_' se importa todo lo que haya en este objeto
  import Ejercicio3._

  object Ejercicio4 {
    // Crea una fruta de cualquier color (utilizando `Fruta.crear`) y un plátano
    // de cualquier precio (utilizando el constructor de la clase `Platano`).
    val fruta: Fruta = Fruta.crear("verde")
    val platano: Platano = new Platano(3.0)

    /* otra imple
    val fruta: Fruta = Fruta.crear("morado")
    val platano: Platano = new Platano(12.23)
    * 
    */
  }

  import Ejercicio4._

  // Ejecuta `org.hablapps.scalaintro.oo.Ejercicios` y asegúrate de que el precio de
  // la `fruta` (3.0) y el color del `platano` ("amarillo") sean correctos.
  println("El precio de la fruta es: " + fruta.precio)
  println("El color de la fruta es: " + fruta.color)
  println("El precio del plátano es: "  + platano.precio)
  println("El color del plátano es: "  + platano.color)
}
