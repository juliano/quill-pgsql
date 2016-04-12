package io.gustavoamigo.quill.pgsql.encoding.range.datetime

import java.time.{LocalDate, LocalDateTime, ZonedDateTime}
import java.util.Date

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.range.DateRangeDecoder

trait Decoders extends DateRangeDecoder {
  this: JdbcSource[_, _] =>

  import Formatters._

  implicit val dateTupleDecoder: Decoder[(Date, Date)] = decodeRange(parseDate)
  implicit val localDateTimeTupleDecoder: Decoder[(LocalDateTime, LocalDateTime)] = decodeRange(parseLocalDateTime)
  implicit val zonedDateTimeTupleDecoder: Decoder[(ZonedDateTime, ZonedDateTime)] = decodeRange(parseZonedDateTime)
  implicit val localDateTupleDecoder: Decoder[(LocalDate, LocalDate)] = decodeRange(parseLocalDate)
}