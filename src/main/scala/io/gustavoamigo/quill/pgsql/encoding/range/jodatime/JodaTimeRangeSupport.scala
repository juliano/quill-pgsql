package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import io.getquill.source.jdbc.JdbcSource

trait JodaTimeRangeSupport extends Decoders with Encoders {
  this: JdbcSource[_, _] =>
}
