package org.hablapps.fpinscala
package typeclasses

object UnsafePrograms{

  /** 
   * La implementación de las expresiones aritméticas realizada anteriormente
   * no impide que se puedan crear expresiones mal formadas, que den lugar en 
   * tiempo de ejecución a una excepción. Esto pasa en todas las versiones: por
   * ejemplo, en la versión con ADTs:
   */
  object WithADTs{
    import ADTExtensions.DataExtensions._

    val estoSiCompila: Exp = Add(Lit(3), Bool(true))

  }

  // Y en la versión con type classes

  object WithTypeClasses {
    import TypeclassExtensions.DataExtensions._

    // Con esta solución se pueden crear expresiones erroneas
    // que fallarán en runtime
    def estoSiCompila[E](implicit E: IntBoolExp[E]): E =
      E.add(E.lit(3), E.bool(true))

  }

}
