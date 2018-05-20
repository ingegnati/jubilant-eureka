package it.ingegnati.models

case class User(
                 id: Option[Long],
                 email: String,
                 password: String) {

}
