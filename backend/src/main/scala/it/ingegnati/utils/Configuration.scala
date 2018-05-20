package it.ingegnati.utils

import com.typesafe.config.{Config, ConfigFactory}

trait Configuration {
  protected val config : Config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  private val databaseConfig = config.getConfig("database")
  private val appConfig = config.getConfig("app")

  // Application
  val applicationName = appConfig.getString("name")

  val httpHost = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")
  val httpSelfTimeout = httpConfig.getDuration("self-timeout")

  // Database
  val jdbcUrl = databaseConfig.getString( "db.url")
  val dbUser = databaseConfig.getString("db.user")
  val dbPassword = databaseConfig.getString("db.password")
  // In Memory database configuration
  val h2Conf = config.getObject("h2mem1")
}
