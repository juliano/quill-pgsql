package io.gustavoamigo.quill.pgsql.encoding.range.jodatime

import io.getquill._
import io.getquill.naming.CamelCase
import io.gustavoamigo.quill.pgsql.PostgresJdbcSource
import org.joda.time.LocalDateTime
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAll

class JodaTimeRangeSupportSpec extends Specification with BeforeAll {

  object db extends PostgresJdbcSource[CamelCase] with JodaTimeRangeSupport

  def beforeAll = {
    db.execute("DROP TABLE IF EXISTS EncodeRange")
    db.execute(
      """
        |CREATE TABLE EncodeRange (
        |   name TEXT,
        |   tsr TSRANGE,
        |   tstzr TSTZRANGE,
        |   dr DATERANGE)
        | """.stripMargin
    )
  }

  case class EncodeLocalDateTimeTuple(name: String, tsr: (LocalDateTime, LocalDateTime))

  "Tuple (LocalDateTime, LocalDateTime) mapped to TSRANGE" should {
    "just work" in {
      val encodeLocalDateTuple = quote(query[EncodeLocalDateTimeTuple]("EncodeRange"))
      val now = LocalDateTime.now
      val tomorrow = LocalDateTime.now().plusDays(1)

      val insert = quote(encodeLocalDateTuple.insert)
      val select = quote(encodeLocalDateTuple.filter(_.name == "test1"))
      db.run(insert)(List(EncodeLocalDateTimeTuple("test1", (now, tomorrow))))
      val found = db.run(select)
      found.head.tsr.toString must beEqualTo((now, tomorrow).toString)
    }
  }
}