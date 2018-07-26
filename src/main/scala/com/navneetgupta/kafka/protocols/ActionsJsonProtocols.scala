package com.navneetgupta.kafka.protocols

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._
import com.navneetgupta.kafka.commands.UserActionDetails
import spray.json.{ DeserializationException, JsString, JsValue, RootJsonFormat }
import com.navneetgupta.kafka.commands._
import java.util.Date

trait ActionsJsonProtocols extends SprayJsonSupport with DefaultJsonProtocol {

  implicit object DateFormat extends JsonFormat[Date] {
    override def write(date: Date): JsValue = JsNumber(date.getTime)
    override def read(json: JsValue): Date = json match {
      case JsNumber(epoch) => new Date(epoch.toLong)
      case unknown         => deserializationError(s"Expected JsString, got $unknown")
    }
  }

  implicit object AnyJsonFormat extends JsonFormat[Any] {
    override def write(x: Any) = x match {
      case n: Int                   => JsNumber(n)
      case s: String                => JsString(s)
      case b: Boolean if b == true  => JsTrue
      case b: Boolean if b == false => JsFalse
    }
    override def read(value: JsValue) = value match {
      case JsNumber(n) => n.intValue()
      case JsString(s) => s
      case JsTrue      => true
      case JsFalse     => false
    }
  }
  implicit val actionEnumFormat = new EnumJsonConverter(Action)
  implicit val platformEnumFormat = new EnumJsonConverter(DevicePlatform)
  implicit val deviceTypeEnumFormat = new EnumJsonConverter(DeviceType)
  implicit val userTypeEnumFormat = new EnumJsonConverter(UserType)
  implicit val userActionDetails = jsonFormat12(UserActionDetails.apply)
}

/**
 * Based on the code found: https://groups.google.com/forum/#!topic/spray-user/RkIwRIXzDDc
 */
class EnumJsonConverter[T <: scala.Enumeration](enu: T) extends RootJsonFormat[T#Value] {
  override def write(obj: T#Value): JsValue = JsString(obj.toString)

  override def read(json: JsValue): T#Value = {
    json match {
      case JsString(txt) => enu.withName(txt)
      case somethingElse => throw DeserializationException(s"Expected a value from enum $enu instead of $somethingElse")
    }
  }
}
