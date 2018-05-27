package it.ingegnati

import akka.http.scaladsl.testkit.ScalatestRouteTest
import it.ingegnati.utils.Persistence
import it.ingegnati.http.HttpService
import it.ingegnati.services.{AppService, UsersService}
import org.scalatest.{Matchers, WordSpec}

/**
  * A common trait that has to be extended for testing routes and services
  */
trait BaseServiceTest extends WordSpec with Matchers with ScalatestRouteTest {

  val persistence = new Persistence()
  persistence.initDatabase()
  // Services
  val usersService = new UsersService(persistence)
  val appService = new AppService()
  // Main HTTP service
  val httpService = new HttpService(usersService, appService)
}
