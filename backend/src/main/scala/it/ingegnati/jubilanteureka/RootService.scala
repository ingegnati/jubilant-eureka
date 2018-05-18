package it.ingegnati.jubilanteureka

import cats.effect.Effect
import io.circe.Json
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class RootService[F[_]: Effect] extends Http4sDsl[F] {

  val service: HttpService[F] = {
    HttpService[F] {
      case _ -> Root =>
        // The default route result is NotFound. Sometimes MethodNotAllowed is more appropriate.
        MethodNotAllowed()

      case GET -> Root / "hello" / name =>
        Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))

      case GET -> Root / "ping" =>
        // EntityEncoder allows for easy conversion of types to a response body
        Ok(Json.obj("message" -> Json.fromString("pong")))

      case req @ GET -> Root / "ip" =>
        // It's possible to define an EntityEncoder anywhere so you're not limited to built in types
        val json = Json.obj("origin" -> Json.fromString(req.remoteAddr.getOrElse("unknown")))
        Ok(json)
    }
  }
}
