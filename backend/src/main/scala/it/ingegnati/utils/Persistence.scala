package it.ingegnati.utils

// Use H2Driver to connect to an H2 database
import it.ingegnati.models.User
import it.ingegnati.models.db.UsersTable
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class Persistence(implicit executionContext: ExecutionContext)  {
  // Database
  val db = Database.forConfig("h2mem1")
  val users = TableQuery[UsersTable]

  val setup = DBIO.seq(
    // Create the tables, including primary and foreign keys
    users.schema.create,

    // Insert some users
    users += User(Some(1), "test@example.com", "secret"),
    users += User(Some(2), "test2@example.com", "secret2"),
    users += User(Some(3), "test3@example.com", "secret3")
  )

  def initDatabase(): Future[Seq[User]] =
    db.run(setup.flatMap(_ => users.result))

  def fetchUsers(): Future[Seq[User]] = db.run(this.users.result)

  def countUsers(): Future[Int] = db.run(this.users.size.result)

}
