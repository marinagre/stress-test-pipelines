package webHost

import java.net.InetAddress

import akka.actor.{Actor, ActorRef, ActorRefFactory, Props}
import spray.http._
import spray.routing._

// this trait defines our service behavior independently from the service actor
class IngestHttpService
  extends Actor
    with HttpService
    with Logger {

  println("Listening on: " + InetAddress.getLocalHost.getHostAddress + ":8080")

  val ingestRoute: Route = {
    path("stresstest") {
      post {
        entity(as[String]) {
          userProfileContent =>
            ctx => {
              println("Received: " + userProfileContent)
              ctx.complete(HttpResponse(StatusCodes.OK))
            }
        }
      }
    }
  }

  override def receive: Receive = runRoute(ingestRoute)
  override def actorRefFactory: ActorRefFactory = context
}