package org.hablapps.scalaintro.genericidad

object Ejercicios extends App {

  case class Bicicleta(marcha: Int, cadencia: Int, velocidad: Int)

  val bicicletas: List[Bicicleta] = List(
    Bicicleta(1,  0,  5),
    Bicicleta(2, 10,  8),
    Bicicleta(4, 25, 30),
    Bicicleta(6,  1, 45))

  // Consulta la API de `List` para acceder a la documentación de los métodos
  // que usaremos en los siguientes ejercicios:
  // http://www.scala-lang.org/api/current/#scala.collection.immutable.List

  object Ejercicio1 {
    // Filtra todas aquellas bicicletas que se encuentren en una marcha impar. A
    // tener en cuenta:
    // - El operador "módulo" se representa mediante `%`, por ejemplo `x % y`
    val res: List[Bicicleta] = bicicletas.filter(_.marcha % 2 != 0)
  }

  println("El resultado del ejercicio 1 es: " + Ejercicio1.res)

  object Ejercicio2 {
    // Busca la bicicleta que circule a mayor velocidad. Pista: utiliza el
    // método `reduce` para llevar a cabo esta implementación. A tener en cuenta:
    // - La estructura de control de flujo "if" se despliega con esta sintáxis:
    // `if (b) expr1 else expr2`
    val res: Bicicleta = bicicletas reduce {(b1, b2) =>
      if (b1.velocidad > b2.velocidad)
        b1
      else
        b2
    }
  }

  println("El resultado del ejercicio 2 es: " + Ejercicio2.res)

  object Ejercicio3 {
    // Suma las cadencias de todas las bicicletas. ¿A qué estructura de control
    // de flujo te recuerda `foldLeft`?
    val res: Int = bicicletas.foldLeft(0)((acc, b) => acc + b.cadencia)
  }

  println("El resultado del ejercicio 3 es: " + Ejercicio3.res)
}
