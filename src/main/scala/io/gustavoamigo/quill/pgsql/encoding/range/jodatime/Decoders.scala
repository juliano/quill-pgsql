package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.range.DateRangeDecoder
import org.joda.time.{DateTime, LocalDate, LocalDateTime}

trait Decoders extends DateRangeDecoder {
  this: JdbcSource[_, _] =>

  import Formatters._

  implicit val localDateTimeTupleDecoder: Decoder[(LocalDateTime, LocalDateTime)] = decodeRange(LocalDateTime.parse(_, dateTimeFormatter))
  implicit val dateTimeTupleDecoder: Decoder[(DateTime, DateTime)] = decodeRange(DateTime.parse(_, timeZoneDateTimeFormatter))
  implicit val localDateTupleDecoder: Decoder[(LocalDate, LocalDate)] = decodeRange(LocalDate.parse(_, dateFormatter))
}
