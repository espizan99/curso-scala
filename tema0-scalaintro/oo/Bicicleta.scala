package org.hablapps.scalaintro.oo

// 1. Creación de una bicicleta MUTABLE, con cadencia, marcha y velocidad. Los
// campos van definidos en el bloque, utilizando los argumentos del constructor. MAL, no se suele usar
class Bicicleta( _cadencia: Int, _marcha: Int, _velocidad: Int) {

	var cadencia: Int = _cadencia
	var marcha: Int = _marcha
	var velocidad: Int = _velocidad


}
// campos van definidos en el bloque, utilizando los argumentos del constructor.
// class Bicicleta(_cadencia: Int, _velocidad: Int, _marcha: Int) {
//   var cadencia: Int = _cadencia
//   var velocidad: Int = _velocidad
//   var marcha: Int = _marcha
// }

// 2. Versión INMUTABLE de la bicicleta anterior. Utilizamos `val` (valor) en
// lugar de `var` (variable). Scala fomenta la inmutabilidad, por lo que el uso
// de `var`s está desaconsejado. Como consecuencia, desaparecen los setters.
class BicicletaImmutable( _cadencia: Int, _marcha: Int, _velocidad: Int) {

	val cadencia: Int = _cadencia
	val marcha: Int = _marcha
	val velocidad: Int = _velocidad


}
// class Bicicleta(_cadencia: Int, _velocidad: Int, _marcha: Int) {
//   val cadencia: Int = _cadencia
//   val velocidad: Int = _velocidad
//   val marcha: Int = _marcha
// }

// 3. Los constructores y los campos se pueden unificar. Si algún argumento
// prescinde del modificador `val` no se creará un campo para él en la clase. En
// tal caso, será considerado como un argumento de entrada del constructor sin
// más.
class BicicletaImproved( val cadencia: Int, val marcha: Int, val velocidad: Int)

// 4. Podemos declarar constructores adicionales mediante `def this`: MAL, no se suele usar
class BicicletaManyConstructors( val cadencia: Int, val marcha: Int, val velocidad: Int) {

	def this(_cadencia: Int, _marcha: Int) = //Las llaves se pueden quitar si solo hay un statement
		this(_cadencia, _marcha, 200)//NO hay return. Scala devuelve la ultima expresion
}

// 5. Los métodos se añaden utilizando `def`. Podemos, por ejemplo, permitir que
// nuestra bicicleta pueda frenar la velocidad dado un decremento.
class BicicletaMethod(val cadencia: Int, val marcha: Int, val velocidad: Int) {

	def frenar(decremento: Int): BicicletaMethod = new BicicletaMethod(cadencia, marcha, velocidad-decremento)
// class Bicicleta(val cadencia: Int, val velocidad: Int, val marcha: Int)

// 4. Podemos declarar constructores adicionales mediante `def this`:
// class Bicicleta(val cadencia: Int, val velocidad: Int, val marcha: Int) {
//   def this(cadencia: Int, velocidad: Int) =
//     this(cadencia, velocidad, 100)
// }

// 5. Los métodos se añaden utilizando `def`. Podemos, por ejemplo, permitir que
// nuestra bicicleta pueda frenar la velocidad dado un decremento.
case class Bicicleta(val cadencia: Int, val velocidad: Int, val marcha: Int) {
  def this(cadencia: Int, velocidad: Int) =
    this(cadencia, velocidad, 100)

  def frenar(decremento: Int): Bicicleta =
    new Bicicleta(cadencia, velocidad-decremento, marcha)
}

// 6. Es posible tener clases con una única instancia, lo que se conoce como
// Singleton Objects. Este componente nos permite reemplazar los miembros de
// clase propios de Java. Crearemos una `FabricaDeBicicletas` con valores
// iniciales y un método de creación de bicicletas `crear`.
object FabricaDeBicicletas {

	def crear(_cadencia: Int): BicicletaMethod = new BicicletaMethod(_cadencia, 200, 300)
}

// 7. Los Companion Objects son singleton objects (o simplemente objects) que
// acompañan a un tipo de datos, por ejemplo a la clase bicicleta.
//Solo se pueden definir en el mismo fichero y deben tener el mismo nombre de la clase
object BicicletaMethod {

	def crear(_cadencia: Int): BicicletaMethod = new BicicletaMethod(_cadencia, 200, 300)	

// object FactoriaDeBicicletas {
//   val velocidadPorDefecto: Int = 25

//   def crear(cadencia: Int): Bicicleta =
//     new Bicicleta(cadencia, velocidadPorDefecto, 200)
// }

// 7. Los Companion Objects son singleton objects (o simplemente objects) que
// acompañan a un tipo de datos, por ejemplo a la clase bicicleta.
object Bicicleta {
  def crear(cadencia: Int): Bicicleta =
    new Bicicleta(cadencia, 100, 200)
}

// 8. Podemos aplicar herencia básica entre clases de forma muy sencilla.
// Declararemos una bicicleta de montaña que hereda de nuestra bicicleta,
// añadiendo un nuevo campo `alturaSillin`.
// En los miembros que se heredan del otro oconstructor no son necesarios los 'val'
class BicicletaDeMontaña(val alturaSillin: Int, cadencia: Int, marcha: Int, velocidad: Int) extends BicicletaMethod(cadencia, marcha, velocidad)

// 9. Si una clase tiene algún miembro no definido tendrá que permanecer
// abstracta. Se declara una bicicleta de carretera que no implementa una altura
// para el sillín.
abstract class AbstractBicicletaDeMontaña( cadencia: Int, marcha: Int, velocidad: Int) extends BicicletaMethod(cadencia, marcha, velocidad) {

	val alturaSillin: Int
}

// 10. Los traits son interfaces que permiten implementación parcial, con lo que
// se permite la herencia múltiple. Por ejemplo definiremos un motor con una
// cilindrada fija que también añade un campo revoluciones.
trait Motor {

	val cilindrada: Int = 3000
	val revoluciones: Int
}

// 11. De esta forma podemos definir nuestra bicicleta con motor, extendiendo la
// clase Bicicleta y el trait Motor.
//Solo se puede hacer un with de traits, y permiten herencia multiple
//EL PRIMERO SIEMPRE CON extends, AUNQUE SEA UN treat!!
class Motocicleta(cadencia: Int, marcha: Int, velocidad: Int) extends Bicicleta(cadencia, marcha, velocidad) with Motor {
	val revoluciones = 10000
}

//Otra manera
class Motocicleta2(override val revoluciones: Int = 10000, cadencia: Int, marcha: Int, velocidad: Int) extends Bicicleta(cadencia, marcha, velocidad) with Motor
 
// class BicicletaDeMontaña(
//   val alturaSillin: Int,
//   cadencia: Int,
//   velocidad: Int,
//   marcha: Int) extends Bicicleta(cadencia, velocidad, marcha)

// // 9. Si una clase tiene algún miembro no definido tendrá que permanecer
// // abstracta. Se declara una bicicleta de carretera que no implementa una altura
// // para el sillín.
// abstract class BicicletaDeMontaña(
//     cadencia: Int,
//     velocidad: Int,
//     marcha: Int) extends Bicicleta(cadencia, velocidad, marcha) {
//   val alturaSillin: Int
// }

// // 10. Los traits son interfaces que permiten implementación parcial, con lo que
// // se permite la herencia múltiple. Por ejemplo definiremos un motor con una
// // cilindrada fija que también añade un campo revoluciones.
// trait Motor {
//   val cilindrada: Int
//   val revoluciones: Int = 5000
// }

// // 11. De esta forma podemos definir nuestra bicicleta con motor, extendiendo la
// // clase Bicicleta y el trait Motor.
// class Motocicleta(
//   cadencia: Int,
//   velocidad: Int,
//   marcha: Int,
//   override val cilindrada: Int) extends Bicicleta(cadencia, velocidad, marcha) with Motor
