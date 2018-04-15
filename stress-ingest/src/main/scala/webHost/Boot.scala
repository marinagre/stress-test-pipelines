package webHost

import java.net.InetAddress

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._
import scala.language.postfixOps
import pureconfig._

object Boot {

  def main(args: Array[String]) {

    // we need an ActorSystem to host our application in
    implicit val system = ActorSystem("on-spray-can")
    // create and start our service actor
    val service = system.actorOf(
      Props(classOf[IngestHttpService]),
      "ingest-service")

    implicit val timeout = Timeout(5 seconds)

    val interface = InetAddress.getLocalHost.getHostAddress
    // start a new HTTP server on port 8080 with our service actor as the handler
    IO(Http) ? Http.Bind(service, interface = interface, port = 8080)

  }
}
