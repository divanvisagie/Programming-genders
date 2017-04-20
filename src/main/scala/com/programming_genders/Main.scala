package com.programming_genders

import scalax.chart.api._
import org.jfree.data.category._

import zamblauskas.csv.parser._
import zamblauskas.functional._

case class GenderGroup(gender: String, language: String, count: Int)

object Main {

  def graphDataSetFromCount(dataSet: Array[GenderGroup]): DefaultCategoryDataset = {
    val ds = new DefaultCategoryDataset
    dataSet.foreach(x => ds.addValue(x.count, x.gender,x.language))
    ds
  }


  def drawBarGraph(dataSet: DefaultCategoryDataset): Unit = {
    val chart = BarChart(dataSet,stacked = true,threeDimensional = true)
    chart.saveAsPNG("./output.png")
  }

  def readCsvManually: Unit = {
    var bufferedSource = io.Source.fromResource("genderlang.csv")

    val filteredData = bufferedSource.getLines()
      .map(x => {
        println(x)
        x
      })
      .map(line => line.split(","))
      .map(columns => {
        val men = columns(1).toInt * 1.0
        val women = columns(2).toInt * 1.0

        val total = men + women

        val menperc = men/total * 100
        val womenperc = women/total * 100
        println(s"men $men - ${menperc} women $women - ${womenperc}")
        (columns(0),menperc.toInt,womenperc.toInt)
      }).toArray


    filteredData.foreach(println)

    bufferedSource.close

    val dataArray = filteredData.map(x => List(GenderGroup("Women",x._1,x._3), GenderGroup("Men",x._1,x._2)))
      .flatten

    val dataset = graphDataSetFromCount(dataArray)
    drawBarGraph(dataset)

    println(s"Filtered pure data contains: ${filteredData.length} participants")
  }

  def main(args: Array[String]): Unit = {
   readCsvManually
  }
}
