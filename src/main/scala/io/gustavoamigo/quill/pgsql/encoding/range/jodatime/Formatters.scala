package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import org.joda.time.format.{ISODateTimeFormat, DateTimeFormat}

private[jodatime] object Formatters {
  val dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS")
  val timeZoneDateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZ")
  val dateFormatter = ISODateTimeFormat.date()
}
