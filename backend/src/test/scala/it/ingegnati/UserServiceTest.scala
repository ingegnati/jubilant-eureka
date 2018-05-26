package it.ingegnati

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._

class UserServiceTest extends BaseServiceTest {

  trait Context {
    val userRoutes = httpService.usersRouter.routes
  }

  "The UserService" should {
    
    // me, count, list
    "retrieve the current user" in new Context {
      Get("/users/me") ~> userRoutes ~> check {
        userRoutes.map(r => println(s"Rotta: $r"))
        responseAs[String] shouldEqual "Alfredo"
      }
    }

  }

}
