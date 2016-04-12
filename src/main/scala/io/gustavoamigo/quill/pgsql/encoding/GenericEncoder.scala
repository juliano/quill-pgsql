package io.gustavoamigo.quill.pgsql.encoding

import java.sql.{Types, PreparedStatement}

import io.getquill.source.jdbc.JdbcSource

trait GenericEncoder {
  this: JdbcSource[_, _] =>

  def encode[T](valueToString: (T => String)): Encoder[T] = {
    new Encoder[T] {
      override def apply(index: Int, value: T, row: PreparedStatement) = {
        val sqlLiteral = valueToString(value)
        row.setObject(index + 1, sqlLiteral, Types.OTHER)
        row
      }
    }
  }
}
