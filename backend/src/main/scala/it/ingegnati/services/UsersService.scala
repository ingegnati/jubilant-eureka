package it.ingegnati.services

import it.ingegnati.models.User
import it.ingegnati.utils.Persistence

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UsersService(val persistence: Persistence)(implicit executionContext: ExecutionContext) {

  val getMe = () => "Alfredo"

  def getCount(): Future[Int] = persistence.countUsers()

  def retrieveUsers(): Future[Seq[User]] = persistence.fetchUsers()
}
