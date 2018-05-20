package it.ingegnati.http

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Directives._
import it.ingegnati.http.routes.UsersServiceRoute
import it.ingegnati.services.UsersService

class HttpService(
                   val usersService: UsersService
                 )(implicit executionContext: ExecutionContext, actorSystem: ActorSystem) {

  // routes
  val usersRouter = new UsersServiceRoute(usersService)

  val routes = {
    pathPrefix("v1") {
      // service1.routes ~
      // service2.routes ~
      usersRouter.routes
    }
  }

}
