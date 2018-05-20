package it.ingegnati.utils

// Use H2Driver to connect to an H2 database
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
    users += (1, "test@example.com", "secret"),
    users += (2, "test2@example.com", "secret2"),
    users += (3, "test3@example.com", "secret3")
  )

  def initDatabase(): Unit = {
    println("Fallo cazzo")
    try {
      val setupFuture = db.run(setup)
      setupFuture.onSuccess { case x => {
          println("Users:")
          db.run(this.users.result).map(_.foreach {
            case (id, email, password) =>
              println("  " + id + "\t" + email + "\t" + password)
        })
      } }
      setupFuture.onFailure { case err => println(err) }
    } finally {
      // db.close()
      println("Chiuso il DB")
    }
  }

  def fetchUsers(): Future[Seq[(Long, String, String)]] = db.run(this.users.result)

}
