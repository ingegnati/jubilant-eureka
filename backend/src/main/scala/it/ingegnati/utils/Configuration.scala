package it.ingegnati.utils

import com.typesafe.config.{Config, ConfigFactory}

trait Configuration {
  protected val config : Config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")
  private val databaseConfig = config.getConfig("database")
  // In Memory database configuration

  val httpHost = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")
  val httpSelfTimeout = httpConfig.getDuration("self-timeout")

  // Database
  val jdbcUrl = databaseConfig.getString( "db.url")
  val dbUser = databaseConfig.getString("db.user")
  val dbPassword = databaseConfig.getString("db.password")
  // h2mem1
  val h2Conf = config.getObject("h2mem1")
}
