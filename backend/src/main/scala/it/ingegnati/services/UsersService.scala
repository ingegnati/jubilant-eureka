package it.ingegnati.services

import it.ingegnati.utils.Persistence
import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UsersService(val persistence: Persistence)(implicit executionContext: ExecutionContext) {

  val getMe = () => "Alfredo"
  val getCount = () => 1

  def retrieveUsers(): Future[Seq[(Long, String, String)]] = persistence.fetchUsers()
}
