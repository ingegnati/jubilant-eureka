package it.ingegnati

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import it.ingegnati.http.HttpService
import it.ingegnati.services.UsersService
// The Server dependencies
import scala.concurrent.Await
import scala.concurrent.duration.Duration
// Utils
import utils.Configuration

object Main extends App with Configuration {


  // needed to run the route
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext = system.dispatcher

  val route =
    path("ping") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "pong"))
      }
    }
  // Persistence ???
  // Services
  val usersService = new UsersService()
  // Main HTTP service
  val httpService = new HttpService(usersService)

  Http().bindAndHandle(httpService.routes, httpHost, httpPort)

  val h2ConfString: String = h2Conf.toString
  println(s"hmem1: $h2ConfString")
  println(s"Server online at http://$httpHost:$httpPort/\nPress RETURN to stop...")

  Await.result(system.whenTerminated, Duration.Inf)

}
