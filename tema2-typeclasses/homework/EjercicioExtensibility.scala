package org.hablapps.fpinscala.typeclasses
package diagrams

/**
 * Ejercicio XX.
 *
 * Se quiere extender la typeclass `Picture` para poder representar
 * dibujos en el que unas imagenes se encuentre encima de otras.
 *
 * Para ello necesitamos extender la type class, sintaxis e instancias
 * ya existentes dentro del paquete `Pictures`.
 *
 * También crearemos una nueva instancia que creará un pretty printer
 * de nuestros dibujos.
 */
object HomeworkExtensibility {
  import StyleSheets._, Shapes._, Drawings._

  /**
   * Parte I. La type class (RESUELTO)
   *
   * Crea una nueva type class, extendiendo a la ya existente,
   * que añada una nueva instrucción `beneath`, que se encargará
   * de poner una imagen encima de otra.
   */
  trait Picture[P] extends Pictures.Picture[P] {
    def beneath(front: P, back: P): P
  }

  object Picture {

    /**
     * Parte II. La instancia
     *
     * Crea una nueva instancia pata T=Drawing para la nueva type class.
     * Para ello extiende la antigua instancia situada en el paquete
     * `Pictures`.
     *
     * TIP: La implementación del metodo `beneath` se delegará al método
     * `beneath` existente para `Drawing`
     */
    implicit object drawPicture extends DrawPicture
    trait DrawPicture extends Picture[Drawing] with Pictures.Picture.DrawPicture {
      def beneath(front: Drawing, back: Drawing): Drawing = front beneath back
    }

    /**
     * Parte III. Una nueva instancia
     *
     * Se pide ahora crear una instancia para nuestra type class completamente
     * nueva, con T=String, que consistirá en hacer un pretty printer de
     * nuestras `Picture`s
     */
    implicit object stringPicture extends StringPicture
    trait StringPicture extends Picture[String] {
      def place(style: StyleSheet, shape: Shape): String = s"Place($style, $shape)"
      def above(top: String, bottom: String): String = s"Above($top, $bottom)"
      def beside(left: String, right: String): String = s"Beside($left, $right)"
      def beneath(front: String, back: String): String = s"Beneath($front, $back)"
    }
  }

  /**
   * Parte IV. La sintaxis
   *
   * Falta ampliar la sintaxis para poder utilizar el método `beneath` de
   * forma infija, ie.: p1 beneath p2
   *
   * TIP: fíjate en cómo estaba implementada la sintaxis en el paquete
   * `Pictures` si no te acuerdas de cómo se hacía.
   */
  implicit class PictureOps[P](p: P)(implicit P: Picture[P]) extends Pictures.PictureOps(p)(P) {
    def beneath(other: P): P =
      P.beneath(p, other)
  }

  /**
   * Dejamos algunos dibujos de ejemplo que hacen uso
   * de esta nueva funcionalidad, si todo ha ido bien
   * estos dibujos deberían compilar perfectamente.
   */
  def poc[P](implicit P: Picture[P]): P =
    P.place(FillColor(Red)  :: Nil, Circle(10))   beneath
    P.place(FillColor(Blue) :: Nil, Triangle(10))

  def poc2[P](implicit P: Picture[P]): P =
    P.place(FillColor(Red)  :: Nil, Rectangle(10, 10)) beneath
    P.place(FillColor(Blue) :: Nil, Triangle(10))      beneath
    P.place(FillColor(Red)  :: Nil, Rectangle(5, 5))   beneath
    P.place(FillColor(Blue) :: Nil, Triangle(5))

  /**
   * Como hemos visto, no hace falta modificar nada del código
   * existente, ni siquiera los dibujos antiguos. Por ejemplo,
   * podemos utilizar el dibujo `body` definido en `Pictures`
   * con nuestro nueva instancia sin problemas.
   */
  Pictures.body[Drawing](Picture.drawPicture)

}
