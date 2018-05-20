package it.ingegnati.http.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import it.ingegnati.services.AppService

import scala.concurrent.ExecutionContext

class AppServiceRoute(val appService: AppService)(implicit executionContext: ExecutionContext) {

  val routes = pathPrefix("app") {
    routeAppName //~ route2
  }

  val routeAppName =
    get {
      path("name") {
        complete( {
          val name = appService.fetchAppName
          HttpEntity(ContentTypes.`application/json`, s"""{"message": "$name"}""")
        }
        )
      }
    }

}
