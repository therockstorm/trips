package dev.rocky

import java.io.{File, PrintWriter}

import scala.io.Source

object ReadCsv extends App {
  private val stationIdIdx = 5
  val commaNotInQuotes = """,(?=(?:[^"]*"[^"]*")*[^"]*$)""".r
  Source.fromFile("Divvy_Trips_2019_Q4.csv")
    .getLines()
    // Drop header
    .drop(1)
    // Stream so the full file isn't held in memory
    .to(LazyList)
    // Map to tuple of (stationId: String, line: String)
    .map(line => (commaNotInQuotes.split(line)(stationIdIdx), line))
    // Group by stationId, results in Map[stationId: String, Seq[(stationId: String, line: String)]]
    .groupBy(_._1)
    .view
    // Map to Map[stationId: String, Seq[line: String]]
    .mapValues(_.map(_._2))
    .foreach {
      case (k, v) =>
        val writer = new PrintWriter(new File(s"$k.csv"))
        v.foreach(line => writer.write(s"$line\n"))
        writer.close()
    }
}
