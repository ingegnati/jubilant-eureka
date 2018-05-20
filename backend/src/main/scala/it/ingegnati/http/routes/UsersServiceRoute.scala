package it.ingegnati.http.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import it.ingegnati.services.UsersService
import spray.json._

import scala.concurrent.ExecutionContext
// import models.User
// import models.db.UserRepository

class UsersServiceRoute(val usersService: UsersService)(implicit executionContext: ExecutionContext) {

  val routes = pathPrefix("users") {
    routeMe ~ routeCount ~ routeList
  }

  def routeMe =
    get {
      path("me") {
        complete(
          HttpEntity(ContentTypes.`text/plain(UTF-8)`, usersService.getMe())
        )
      }
    }

  def routeCount =
    get {
      path("count") {
        complete(
          HttpEntity(ContentTypes.`text/plain(UTF-8)`, usersService.getCount().toString)
        )
      }
    }

  def routeList =
    get {
      path("list") {
        onSuccess(usersService.retrieveUsers()) { usersSeq =>
          complete( HttpEntity(ContentTypes.`text/plain(UTF-8)`, usersSeq.toString) )
        }
      }
    }

}
