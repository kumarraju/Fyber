package com.data.test.fyber
import scala.io.Source
import scala.collection.mutable.Queue
//import scala.math.BigDecimal.RoundingMode
/**
 * @author ${user.name}
 */
object App {
  var queue_timestamp = Queue[Long]()
  var queue_measurement = Queue[Double]()
  def main(args: Array[String]) {

    val period = 60 // 60 seconds
    var output = ""
    if (!(args.length > 0)) {
      println("provide inputfile path")
      println("usage: java -cp jar-file-name class-name inputfilepath")
      System.exit(1)
    }
    val filePath = args(0)
    output = "T" + "\t\t" + "V" + "\t" + "N" + "\t " + "RS" + " \t" + "MinV " + "\t" + "MaxV"
    println(output)
    println("--------------------------------------------------------")
    for (line <- Source.fromFile(filePath).getLines()) {
      output = processRecord(line, period)
      println(output)
    }
  }
  def processRecord(line: String, period: Long): String = {
    val elems = line.split('\t')
    val timestamp = elems(0).toLong
    queue_timestamp.enqueue(timestamp)
    queue_measurement.enqueue(elems(1).toDouble)
    while (timestamp - queue_timestamp.head > period) {
      queue_timestamp.dequeue()
      queue_measurement.dequeue()
    }
    val output = timestamp + "\t" + elems(1) + "\t" + queue_measurement.size + "\t" + Math.round((queue_measurement.sum) * 100000.0) / 100000.0 + "\t" + queue_measurement.min + "\t" + queue_measurement.max
    output
    //BigDecimal(queue_measurement.sum).setScale(5, RoundingMode.HALF_UP) 
  }
}
