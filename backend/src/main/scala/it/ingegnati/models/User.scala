package it.ingegnati.models

import spray.json.{JsonFormat, JsonWriter}
import spray.json.DefaultJsonProtocol._

case class User(id: Option[Long], email: String, password: String)

object User {
  implicit val userWriter: JsonFormat[User] = jsonFormat3(User.apply _)
}
