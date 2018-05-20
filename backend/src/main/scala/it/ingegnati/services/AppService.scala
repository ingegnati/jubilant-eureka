package it.ingegnati.services

import it.ingegnati.utils.Configuration
import scala.concurrent.ExecutionContext

class AppService(implicit executionContext: ExecutionContext) extends Configuration {

  val fetchAppName: String = applicationName

}
