package com.navneetgupta.kafka

import com.navneetgupta.akka.common.Bootstrap
import akka.actor.ActorSystem
import com.navneetgupta.kafka.manager.UserActionKafkaManager
import com.navneetgupta.kafka.routes.ActionRoutes

class KafkaBoot extends Bootstrap {
  override def bootstrap(system: ActorSystem) = {
    import system.dispatcher
    val actionManager = system.actorOf(UserActionKafkaManager.props, UserActionKafkaManager.Name)
    List(new ActionRoutes(actionManager))
  }
}
