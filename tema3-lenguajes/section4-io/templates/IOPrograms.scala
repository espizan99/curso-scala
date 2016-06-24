package org.hablapps.fpinscala
package lenguajes.io
package templates

object IOPrograms{
  
  trait Logic{ IO: lenguajes.ImpureIO.IO => 
    def echo: String = {
      val msg: String = IO.read
      IO.write(read)
      msg
    }
  }

}

