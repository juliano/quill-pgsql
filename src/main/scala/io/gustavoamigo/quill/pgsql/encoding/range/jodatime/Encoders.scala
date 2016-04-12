package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.GenericEncoder

import org.joda.time.{DateTime, LocalDate, LocalDateTime}

trait Encoders extends GenericEncoder {
  this: JdbcSource[_, _] =>

  import Formatters._

  private def tuple[T](t: (T, T))(valToStr: T => String) = s"[${valToStr(t._1)}, ${valToStr(t._2)})"

  implicit val localDateTimeTupleEncoder: Encoder[(LocalDateTime, LocalDateTime)] = encode(tuple(_)(_.toString(dateTimeFormatter)))
  implicit val dateTimeTupleEncoder: Encoder[(DateTime, DateTime)] = encode(tuple(_)(_.toString(timeZoneDateTimeFormatter)))
  implicit val localDateTupleEncoder: Encoder[(LocalDate, LocalDate)] = encode(tuple(_)(_.toString(dateFormatter)))
}
