package io.gustavoamigo.quill.pgsql.encoding.range

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.GenericDecoder

trait DateRangeDecoder extends GenericDecoder {
  this: JdbcSource[_, _] =>

  private val rangePattern = """([0-9\-\+\. :]+)""".r

  def decodeRange[T](map: String => T) = decode(s => {
    val dates = rangePattern.findAllIn(s).toList
    (map(dates.head), map(dates.last))
  })
}