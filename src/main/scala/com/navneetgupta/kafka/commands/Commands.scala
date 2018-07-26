package com.navneetgupta.kafka.commands

import java.util.Date

object Action extends Enumeration {
  type Action = Value
  val VIEWED, RATED, LIKED, SHARED, FOCUSED, FAVOURITED, UNFAVOURITED, DELETED, REDEMPTED, PURCHASED, POSITIVE_FEEDBACK, NEGATIVE_FEEDBACK, FEEDBACK, CALLED, EMAILED, NAVIGATED = Value
}

object DevicePlatform extends Enumeration {
  type DevicePlatform = Value
  val IOS, WINDOWS, OSX, LINUX, ANDROID, OTHERS = Value
}

object DeviceType extends Enumeration {
  type DeviceType = Value
  val ANDROID, IPHONE, WEB, LINUX, UNKNOWN = Value
}

object UserType extends Enumeration {
  type UserType = Value
  val SYSTEM, CLIENT, ADMIN, CUSTOMER, CUSTOMER_CARE, EMPLOYEE = Value
}

case class UserActionDetails(
  action: Action.Value,
  id: Long,
  description: Option[String],
  lat: Option[Double],
  lon: Option[Double],
  processed: Option[Boolean] = Some(false),
  createdOn: Option[Date] = Some(new Date()),
  browser: Option[String],
  devicePlatform: Option[DevicePlatform.Value],
  deviceType: Option[DeviceType.Value],
  userId: Long,
  userType: Option[UserType.Value])
