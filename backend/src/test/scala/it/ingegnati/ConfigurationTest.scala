package it.ingegnati

import it.ingegnati.utils.Configuration
import org.scalatest.{Matchers, WordSpec}

class ConfigurationTest extends WordSpec with Matchers with Configuration {
  "Config" should {
    "should load all values from configuration file for test" in {
      applicationName.isEmpty shouldBe false
      applicationName shouldBe a [String]
      httpHost.isEmpty shouldBe false
      httpHost shouldBe a [String]
      httpPort should (be > 0 and be <= 65535)
      httpSelfTimeout.getSeconds.toInt should (be < 60)
      jdbcUrl.isEmpty shouldBe false
      dbUser.isEmpty shouldBe false
      dbUser shouldBe a [String]
      dbPassword.isEmpty shouldBe false
      dbPassword shouldBe a [String]
    }
  }

}
