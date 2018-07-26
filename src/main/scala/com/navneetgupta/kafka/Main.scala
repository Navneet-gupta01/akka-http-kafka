package com.navneetgupta.kafka

import com.navneetgupta.akka.common.Server

object Main extends App {
  new Server(new KafkaBoot(), "akka-kafka")
}
