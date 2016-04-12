package io.gustavoamigo.quill.pgsql.encoding.range.datetime

import java.time.{LocalDate, LocalDateTime, ZonedDateTime}
import java.util.Date

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.GenericEncoder

trait Encoders extends GenericEncoder {
  this: JdbcSource[_, _] =>

  import Formatters._

  private def tuple[T](t: (T, T))(valToStr: T => String) = s"[${valToStr(t._1)}, ${valToStr(t._2)}]"

  implicit val dateTupleEncoder: Encoder[(Date, Date)] = encode(tuple(_)(formatDate))
  implicit val localDateTimeTupleEncoder: Encoder[(LocalDateTime, LocalDateTime)] =
    encode(tuple(_)(formatLocalDateTime))
  implicit val zonedDateTimeTupleEncoder: Encoder[(ZonedDateTime, ZonedDateTime)] =
    encode(tuple(_)(formatZonedDateTime))
  implicit val dateTimeTupleEncoder: Encoder[(LocalDate, LocalDate)] =
    encode(t => s"[${formatLocalDate(t._1)}, ${formatLocalDate(t._2)})")
}