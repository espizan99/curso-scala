package org.hablapps.meetup
package fun
package logic
package templates

import common.logic.Domain._

/**
 * La primera versión del servicio purificado no utiliza azúcar sintáctico
 * en absoluto. 
 */
object ServicesWithoutSugar{ 
  
  trait Services { S: oo.logic.Store =>

    def join(request: JoinRequest): JoinResponse = {
      val _      = S.getUser(request.uid)
      val group  = S.getGroup(request.gid) 
      val result = Either.cond(
        test  = group.must_approve, 
        left  = S.putJoin(request), 
        right = S.putMember(Member(None, request.uid, request.gid))
      )
      result
    }
  }
    
}

/** 
 * En esta versión utilizamos context-bounds y los operadores infijos
 */ 
object ServicesWithSomeSugar{ 
  import Store.Syntax._

  
}

/** 
 * Para terminar con el azúcar sintáctico, aprovechamos la sintaxis
 * especial que ofrece Scala para los operadores `flatMap` y `map`: 
 * las for-comprehensions.
 */
object ServicesWithForComprehensions{ 
  import Store.Syntax._

  
}

/** 
 * Por último, utilizamos el operador derivado `cond`
 */
object Services{ 
  import Store.Syntax._

  
}

