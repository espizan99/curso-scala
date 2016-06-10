package org.hablapps.fpinscala.hofs.modularidad

// 4. Aquí se muestra como se utilizan las funciones ordinarias
//    como mecanismo de modularidad. Las funciones ordinarias
//    abstraen sobre valores como veremos a continuación.
object Funciones {

  // Creamos una configuración dummy para utilizarla en el ejemplo
  object config {
    def get(s: String): Option[String] = s match {
      case "URL" => Option("my.awesome.uri")
      case "PORT" => Option("9000")
      case _ => None
    }
  }

  // (I) valores monolíticos
  val url: String = config.get("URL") match {
    case Some(u) => u
    case None => "default.url"
  }

  val port: String = config.get("PORT") match {
    case Some(p) => p
    case None => "8080"
  }

  // (II) Abstracción
  def getConfigProperty(name: String, default: String) =
    config.get(name) match {
      case Some(p) => p
      case None => default
    }

  // (III) Valores modularizados
  val urlMod: String = getConfigProperty("URL", "default.url")
  val portMod: String = getConfigProperty("PORT", "8080")
}
