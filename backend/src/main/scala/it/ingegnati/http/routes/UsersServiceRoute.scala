package it.ingegnati.http.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import it.ingegnati.services.UsersService
import spray.json._
import DefaultJsonProtocol._
import it.ingegnati.models.User

import scala.concurrent.ExecutionContext

class UsersServiceRoute(val usersService: UsersService)(implicit executionContext: ExecutionContext) {

  val routes = pathPrefix("users") {
    routeMe ~ routeCount ~ routeList
  }

  def routeMe =
    get {
      path("me") {
        complete(usersService.getMe())
      }
    }

  def routeCount =
    get {
      path("count") {
        complete(usersService.getCount().map(c => Map("count" -> c)))
      }
    }

  def routeList =
    get {
      path("list") {
        complete(usersService.retrieveUsers())
      }
    }

}
