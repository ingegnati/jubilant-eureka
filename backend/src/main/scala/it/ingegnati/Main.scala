package it.ingegnati

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import it.ingegnati.http.HttpService
import it.ingegnati.models.User
import it.ingegnati.services.{AppService, UsersService}
import it.ingegnati.utils.Persistence

import scala.io.StdIn
// The Server dependencies
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
// Utils
import utils.Configuration

object Main extends App with Configuration {
  // needed to run the route
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext = system.dispatcher
  val persistence = new Persistence()
  val initialized = persistence.initDatabase()
  // here we use andThen because we don't want to make the Future to complete but to "keep going"
  // in order to mix it with the rest of futures using flatMap below
  val initDatabase = initialized.andThen {
    case Success(users) => users.foreach {
                  case User(id, email, password) =>
                    println("  " + id + "\t" + email + "\t" + password)
              }
  }

  // Flatten all the Futures into a single flow of actions
  val bindingFuture = initDatabase.flatMap { _ => // We are not collecting anything from the previous flow using _
    // Services
    val usersService = new UsersService(persistence)
    val appService = new AppService()
    // Main HTTP service
    val httpService = new HttpService(usersService, appService)

    // Only the last one, in this case will be returned as a Result of "binding"
    Http().bindAndHandle(httpService.routes, httpHost, httpPort)
  }

  bindingFuture.onComplete {
    case Success(b) => println(s"Service is up: $b")
    case Failure(ex) =>
      println("**** ERROR ****")
      ex.printStackTrace()
  }

  println(s"Server online at http://$httpHost:$httpPort/v1")
  println("Press RETURN to stop")


  Await.result(system.whenTerminated, Duration.Inf)
}
