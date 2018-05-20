package it.ingegnati.models.db

import slick.jdbc.H2Profile.api._

/**
  * Definition of the users table
  * @param tag
  */
class UsersTable(tag: Tag) extends Table[(Long, String, String)](tag, "users") {
  def id = column[Long]("id", O.PrimaryKey)
  def email= column[String]("email")
  def password = column[String]("password")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, email, password)
}
