package it.ingegnati

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import it.ingegnati.http.HttpService
import it.ingegnati.services.{AppService, UsersService}
import it.ingegnati.utils.Persistence
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
  // TODO improve the Persistence.initDatabase()
  val persistence = new Persistence()
  persistence.initDatabase()
  // Services
  val usersService = new UsersService(persistence)
  val appService = new AppService()
  // Main HTTP service
  val httpService = new HttpService(usersService, appService)

  Http().bindAndHandle(httpService.routes, httpHost, httpPort)

  println(s"Server online at http://$httpHost:$httpPort/v1")

  Await.result(system.whenTerminated, Duration.Inf)
}
