package it.ingegnati.jubilanteureka

import cats.effect.{Effect, IO}
import org.http4s.server.blaze.BlazeBuilder
import fs2._

import scala.concurrent.ExecutionContext

object MainServer extends StreamApp[IO] {
  import scala.concurrent.ExecutionContext.Implicits.global

  def stream(args: List[String], requestShutdown: IO[Unit]) = ServerStream.stream[IO]
}

object ServerStream {

  def rootService[F[_]: Effect] = new RootService[F].service

  def stream[F[_]: Effect](implicit ec: ExecutionContext) =
    BlazeBuilder[F]
      .bindHttp(8080, "0.0.0.0")
      .mountService(rootService, "/")
      .serve
}
