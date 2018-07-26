package com.navneetgupta.kafka.routes

import akka.actor.ActorRef
import com.navneetgupta.kafka.protocols.ActionsJsonProtocols
import scala.concurrent.ExecutionContext
import com.navneetgupta.akka.common.BaseRouteDefination
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.http.scaladsl.server.Route
import com.navneetgupta.kafka.commands.UserActionDetails

class ActionRoutes(actionManagerActor: ActorRef)(implicit val ec: ExecutionContext) extends BaseRouteDefination with ActionsJsonProtocols {

  import akka.pattern._
  import akka.http.scaladsl.server.Directives._

  override def routes(implicit system: ActorSystem, ec: ExecutionContext, mater: Materializer): Route = {
    pathPrefix("action") {
      pathEndOrSingleSlash {
        post {
          entity(as[UserActionDetails]) { uad =>
            println("received the Request uad ")
            serviceAndComplete[UserActionDetails](UserActionDetails(action = uad.action, id = uad.id, userId = uad.userId), actionManagerActor)
          }
        }
      }
    }
  }
}
