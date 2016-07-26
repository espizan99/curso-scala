package org.hablapps.fpinscalaz
package typeclasses
package monad
package exercises

/**
 * We'll see some examples on the `State` monad using a simple calculator.
 * As usual, we'll see first an impure implementation and then we will use
 * this version as a reference for conciseness and readability when we 
 * implement the pure version based on the `State` monad.
 */
object Ejercicio4 {

  /** 
   * Impure implementation of the calculator
   */
  object ImpureVersion{

    class Calculator {

      // The state of the calculator
      private var display: Int = 0

      // Show the specified number in the display
      def show(i: Int): Int = {
        display = i
        display
      }

      // Add a number to the current displayed value
      def add(x: Int): Int = 
        show(display+x)

      // Substract a number to the displayed value
      def substract(x: Int): Int = 
        show(display-x)

      // Multiply a number with the displayed value
      def multiply(x: Int): Int = 
        show(display*x)

      // Divide the displayed value by a number
      def divide(x: Int): Int = 
        show(display/x)
    }

    // Sample operation 
    def operation1: Int = { 
      val calc = new Calculator()
      calc.show(3)               
      calc.add(4)                
      calc.multiply(4)           
      calc.divide(7)             
      calc.substract(7)          
    }
  }

  object PureVersion {
    import scalaz.{State, MonadState, StateT}

    // Operations on the calculator are simple transformations on 
    // the display and the memory. Thus, the state of our `State`
    // monad is simply (Int, Int), and our type can be called 
    // `Calculation` instead of `Calculator` (which is more of 
    // a machine-oriented name).

    type CalculationState = Int
    type Calculation[T] = State[CalculationState, T] 

    object Calculation{
      // Besides the `bind` and `pure` operations of any monad, the
      // the `MonadState` type class offers particular smart constructors
      // for state manipulation, namely, `get` and `put`, as well as
      // derived operations such as `gets` and `modify` (which are defined
      // as combinations of get and put)

      implicit val CalculationMonad: MonadState[Calculation, CalculationState] = 
        StateT.stateMonad[CalculationState]

      import CalculationMonad.{put, get},
        scalaz.syntax.monad._
    
      /**
       * PART I. Auxiliary functions
       *
       * Before implementing the calculator API, let's implement some 
       * auxiliary functions. The purpose here is double:
       * first, we want to play with the basic operations `get`, `put`,
       * etc; second, these auxiliary functions will allow us to implement
       * the operations of the calculator in a more concise and readable
       * way (with the typical look-and-feel of impure programs we are used to).
       */
    
      /** Display-related auxiliary functions */

      def display: Calculation[Int] =
        get

      def setDisplay(i: Int): Calculation[Unit] =
        put(i)

      /**
       * PART II. 
       *
       * Implement the calculator API
       */
      def show(i: Int): Calculation[Int] =
        setDisplay(i) >>
        display

      def add(x: Int): Calculation[Int] =
        display >>= { d =>
          show(d+x)
        }

      def substract(x: Int): Calculation[Int] =
        display >>= { d =>
          show(d-x)
        }

      def multiply(x: Int): Calculation[Int] =
        display >>= { d => show(d*x) }

      def divide(x: Int): Calculation[Int] =
        display >>= { d => show(d/x) }

      // This is the rough equivalent to the `new` operator of the impure
      // version. Basically, it just returns a new calculation which 
      // reset both the memory and the display to 0.
      def reset(): Calculation[Int] =
        show(0)

    }


    /**
     * PART III.
     *
     * In order to "run" a calculation we evalaute the state transformation
     * on a dummy initial state, and pick up just the resulting value.
     */
    object Interpreter{
      def run[T](program: Calculation[T]): T =
        program eval 0
    }

    /** 
     * PART IV.
     *
     * Implement the sample operation from the `ImpureVersion` module.
     */    
    import scalaz.syntax.monad._

    val pureOperation1: Calculation[Int] =
      Calculation.reset        >>  // {display =  0, memory =  0}
      Calculation.show(3)      >>  // {display =  3, memory =  0}
      Calculation.add(4)       >>  // {display =  7, memory =  0}
      Calculation.multiply(4)  >>  // {display = 28, memory =  0}
      Calculation.divide(7)    >>  // {display =  4, memory = 28}
      Calculation.substract(7)     // {display = -3, memory =  0}

    val operation1: Int = Interpreter.run(pureOperation1)

  }


}
