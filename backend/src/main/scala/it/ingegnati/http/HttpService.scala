package it.ingegnati.http

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Directives._
import it.ingegnati.http.routes.{AppServiceRoute, UsersServiceRoute}
import it.ingegnati.services.{AppService, UsersService}

class HttpService(
                   val usersService: UsersService,
                   val appService: AppService
                 )(implicit executionContext: ExecutionContext, actorSystem: ActorSystem) {

  // Routers
  val usersRouter = new UsersServiceRoute(usersService)
  val appRouter = new AppServiceRoute(appService)

  val routes = {
    pathPrefix("v1") {
      usersRouter.routes ~
      appRouter.routes
    }
  }

}
