package com.navneetgupta.kafka.manager

import akka.actor.Props
import com.navneetgupta.akka.common.BaseActor
import akka.stream.ActorMaterializer
import com.navneetgupta.kafka.commands.UserActionDetails
import com.navneetgupta.akka.common.FullResult
import scala.concurrent.Future
import java.util.Date

object UserActionKafkaManager {
  def props = Props[UserActionKafkaManager]
  val Name = "action-manager"
}

class UserActionKafkaManager extends BaseActor {
  implicit val mater = ActorMaterializer()
  import context.dispatcher

  override def receive = {
    case uad @ UserActionDetails(action, id, description, lat, lon, processed, createdOn, browser, devicePlatform, deviceType, userId, userType) =>
      log.info("@ UserActionDetails MAtch")
      pipeResponse(Future { Some(uad) })
    case _ =>
      log.info("@ Others MAtch")
      pipeResponse(Future { None })
  }
}
