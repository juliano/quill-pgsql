package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.GenericDecoder
import org.joda.time.{LocalDate, LocalDateTime, DateTime}

trait Decoders extends GenericDecoder {
  this: JdbcSource[_, _] =>

  import Formatters._

  private val rangePattern = """([0-9\-\+\. :]+)""".r

  private def decoder[T](map: String => T) = decode(s => {
    val dates = rangePattern.findAllIn(s).toList
    (map(dates.head), map(dates.last))
  })

  implicit val localDateTimeTupleDecoder: Decoder[(LocalDateTime, LocalDateTime)] = decoder(LocalDateTime.parse(_, dateTimeFormatter))
  implicit val dateTimeTupleDecoder: Decoder[(DateTime, DateTime)] = decoder(DateTime.parse(_, timeZoneDateTimeFormatter))
  implicit val localDateTupleDecoder: Decoder[(LocalDate, LocalDate)] = decoder(LocalDate.parse(_, dateFormatter))
}
