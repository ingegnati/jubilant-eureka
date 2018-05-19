package it.ingegnati

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
// The Server dependencies
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object MainServer extends App {


  // needed to run the route
  implicit val system: ActorSystem = ActorSystem("IngegnatiActorSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext = system.dispatcher

  val route =
    path("ping") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "pong"))
      }
    }

  Http().bindAndHandle(route, "localhost", 8090)

  println(s"Server online at http://localhost:8090/\nPress RETURN to stop...")

  Await.result(system.whenTerminated, Duration.Inf)

}
