package org.hablapps.fpinscala
package lenguajes
package soluciones

/**
 * Implement missing commands to be used by the UNIX Terminal.
 *
 * This exercise has a main method, so you can run this program and test 
 * if your commands work as expected. Select this main from the list:
 *   org.hablapps.fpinscala.lenguajes.org.hablapps.meetup.fun
 *
 * You can also run some tests automatically by typing the sbt alias
 *   test-tema3-terminal
 */
object Homework2{

  /**
   * PART I.
   *
   * In this trait we store all commands available from the Terminal. Follow
   * the pattern used in `cp` method to implement the missing commands.
   *
   * Note: Arguments don't include the command name.
   */
  trait Commands[Program[_]] {
    import Monad.Syntax._
    import lenguajes.FS.Syntax._
    import lenguajes.IO.Syntax._

    type Command = Program[Unit]

    implicit def IO: IO[Program]
    implicit def FS: FS[Program]
    implicit def M: Monad[Program]


    def help: Command = {
      val helpText =
        """ |List of commands available:
            |- help: show this help message
            |- cp <orig> <dest>: copy a file from <orig> to <dest>
            |- mv <orig> <dest>: move a file from <orig> to <dest>
            |- rm <file>: remove <file> from the filesystem
            |- touch <file>: creates a file with empty content
            |- touch2 <file> [contents]: creates a file with [contents]
            |- cat <file>: shows the contents of <file>
            |- echo [contents]: prints [contents] to the stdout
            |- exit: close terminal
            |""".stripMargin
      write(helpText)
    }

    def cp(args: Seq[String]): Command = {
      val helpText = "Usage: cp <orig> <dest>\n"
      if (args.length < 2)
        write(helpText)
      else for {
        content <- readFile(args(0))
        _ <- writeFile(args(1))(content)
      } yield ()
    }

    def mv(args: Seq[String]): Command = {
      val helpText = "Usage: mv <orig> <dest>\n"
      if (args.length < 2)
        write(helpText)
      else for {
        content <- readFile(args(0))
        _ <- writeFile(args(1))(content)
        _ <- deleteFile(args(0))
      } yield ()
    }

    def rm(args: Seq[String]): Command = {
      val helpText = "Usage: rm <file-name>\n"
      if (args.length < 1)
        write(helpText)
      else
        deleteFile(args(0))
    }

    def touch(args: Seq[String]): Command = {
      val helpText = "Usage: touch <file-name>\n"
      if (args.length < 1)
        write(helpText)
      else
        writeFile(args(0))("")
    }

    /**
     * The difference between this command and `touch` is that this command
     * writes contents to the file, if any. For instance:
     * `touch2 file1.txt Dummy content` will create a file "file1.txt"
     * whose content is "Dummy content"
     */
    def touch2(args: Seq[String]): Command = {
      val helpText = "Usage: touch2 <file-name> [content]\n"
      if (args.length < 1)
        write(helpText)
      else
        writeFile(args(0))(
          if (args.length < 1) ""
          else args(1)
        )
    }
            
    def cat(args: Seq[String]): Command = {
      val helpText = "Usage: cat <file-name>\n"
      if (args.length < 1)
        write(helpText)
      else
        readFile(args(0)) flatMap {content =>
          write(content)
        }
    }

    /**
     * Execution example:
     * `> echo this is an echoed line` should print:
     * "this is an echoed line"
     */
    def echo(args: Seq[String]): Command = {
      val helpText = "Usage: echo [content]\n"
      if (args.length < 1)
        write(helpText)
      else
        write(args.mkString(" ")+"\n")
    }

    def exit: Command = write("Good bye!\n")

    def other(command: String): Command = write(s"Command not found: '$command'\n")

  }

  /**
   * UNIX Terminal
   *
   * Already implemented ;)
   * Take a look if you're interested on how things are done.
   */
  trait Terminal[Program[_]] extends Commands[Program]{
    import Monad.Syntax._
    import lenguajes.IO.Syntax._

    def readCommand(command: String): Command = {
      val splitted = command.split(" ")
      splitted(0) match {
        case "help" => help
        case "cp" => cp(splitted.tail)
        case "mv" => mv(splitted.tail)
        case "rm" => rm(splitted.tail)
        case "touch" => touch(splitted.tail)
        case "touch2" => touch2(splitted.tail)
        case "cat" => cat(splitted.tail)
        case "echo" => echo(splitted.tail)
        case "exit" => exit
        case c => other(c)
      }
    }

    def welcome: Command =
      for {
        _ <- write("Welcome to my custom terminal\n")
        _ <- write("write 'help' to see a list of commands available\n\n")
      } yield ()

    def getCommand: Program[(String, Command)] =
      for {
        _ <- write("$ ")
        c <- read
      } yield (c, readCommand(c))

    def processCommands: Program[Unit] =
      for {
        sc <- getCommand
        (name, command) = sc
        _ <- command
        _ <- if (name == "exit") returns(()) else processCommands
      } yield ()

    def run: Program[Unit] =
      for {
        _ <- welcome
        _ <- processCommands
      } yield ()

  }

  object Terminal {
    
    def apply[F[_]](implicit IOS: IO[F], FSS: FS[F], 
      Mo: Monad[F]): Terminal[F] = new Terminal[F] {
        lazy val M = Mo
        lazy val IO = IOS
        lazy val FS = FSS
      }
  }

  /**
   * PART II.
   *
   * Main method.
   */
  def main(args: Array[String]): Unit = {
    import typeclasses.Id

    implicit val fsId = FS.ScalaIOFS

    Terminal[Id].run
  }

}
