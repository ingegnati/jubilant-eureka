package it.ingegnati.utils

import com.typesafe.config.{Config, ConfigFactory, ConfigObject}

trait Configuration {
  protected val config : Config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  private val databaseConfig = config.getConfig("database")
  private val appConfig = config.getConfig("app")

  // Application
  val applicationName: String = appConfig.getString("name")

  val httpHost: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")
  val httpSelfTimeout = httpConfig.getDuration("self-timeout")

  // Database
  val jdbcUrl: String = databaseConfig.getString( "db.url")
  val dbUser: String = databaseConfig.getString("db.user")
  val dbPassword: String = databaseConfig.getString("db.password")
  // In Memory database configuration
  val h2Conf: ConfigObject = config.getObject("h2mem1")
}
