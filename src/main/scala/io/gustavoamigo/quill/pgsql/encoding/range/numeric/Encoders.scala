package io.gustavoamigo.quill.pgsql.encoding.range.numeric

import io.getquill.source.jdbc.JdbcSource
import io.gustavoamigo.quill.pgsql.encoding.GenericEncoder

import scala.collection.immutable.NumericRange

trait Encoders extends GenericEncoder {
  this: JdbcSource[_, _] =>

  private def tuple[T](t: (T, T)) = s"[${t._1}, ${t._2}]"

  private def range[T](r: NumericRange[T]) = s"[${r.head}, ${r.last}]"

  implicit val intTupleEncoder: Encoder[(Int, Int)] = encode(tuple)
  implicit val intRangeEncoder: Encoder[NumericRange[Int]] = encode(range)
  implicit val bigIntTupleEncoder: Encoder[(BigInt, BigInt)] = encode(tuple)
  implicit val bigIntRangeEncoder: Encoder[NumericRange[BigInt]] = encode(range)
  implicit val longTupleEncoder: Encoder[(Long, Long)] = encode(tuple)
  implicit val longRangeEncoder: Encoder[NumericRange[Long]] = encode(range)
  implicit val doubleTupleEncoder: Encoder[(Double, Double)] = encode(tuple)
  implicit val bigDecimalTupleEncoder: Encoder[(BigDecimal, BigDecimal)] = encode(tuple)
  implicit val bigDecimalRangeEncoder: Encoder[NumericRange[BigDecimal]] = encode(range)
}