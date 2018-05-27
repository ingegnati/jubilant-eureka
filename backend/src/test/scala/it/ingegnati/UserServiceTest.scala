package it.ingegnati

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import StatusCodes._
import akka.http.scaladsl.model.ContentTypes._
import spray.json.JsonFormat

class UserServiceTest extends BaseServiceTest {

  trait Context {
    val userRoutes = httpService.usersRouter.routes
  }

  "The UserService" should {

    "not handle the root path" in new Context {
      // tests:
      Get("/users/") ~> userRoutes ~> check {
        handled shouldBe false
      }
    }
    
    // me, count, list
    "retrieve the current user" in new Context {
      Get("/users/me") ~> userRoutes ~> check {
        userRoutes.map(r => println(s"Rotta: $r"))
        responseAs[String] shouldEqual "Alfredo"
      }
    }

    "return a MethodNotAllowed error for PUT requests to /me path" in new Context {
      Put("/users/me") ~> Route.seal(userRoutes) ~> check {
        status shouldEqual StatusCodes.MethodNotAllowed
        responseAs[String] shouldEqual "HTTP method not allowed, supported methods: GET"
      }
    }

    "return a JSON object when requesting a users count" in new Context {
      Get("/users/count") ~> userRoutes ~> check {
        status shouldBe OK
        contentType shouldBe `application/json`
        responseAs[String] should include("count")
      }
    }

  }

}
