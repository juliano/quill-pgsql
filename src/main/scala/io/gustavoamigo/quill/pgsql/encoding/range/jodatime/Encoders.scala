package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import java.sql.{PreparedStatement, Types}

import io.getquill.source.jdbc.JdbcSource
import org.joda.time.LocalDateTime

trait Encoders {
  this: JdbcSource[_, _] =>

  import Formatters._

  private def genericEncoder[T](valueToString: (T => String)): Encoder[T] = {
    new Encoder[T] {
      override def apply(index: Int, value: T, row: PreparedStatement) = {
        val sqlLiteral = valueToString(value)
        row.setObject(index + 1, sqlLiteral, Types.OTHER)
        row
      }
    }
  }

  private def tuple[T](t: (T, T))(valToStr: T => String) = s"[${valToStr(t._1)}, ${valToStr(t._2)}]"

  implicit val localDateTimeTupleEncoder: Encoder[(LocalDateTime, LocalDateTime)] = genericEncoder(tuple(_)(_.toString(dateTimeFormatter)))
}
