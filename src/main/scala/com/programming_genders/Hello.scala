package com.programming_genders

import scalax.chart.api._
import org.jfree.data.category._

import zamblauskas.csv.parser._
import zamblauskas.functional._

object Hello {
  case class ProgrammerCount(gender: String, language: String, count: Int)

  def graphDataSetFromCount(dataSet: Array[ProgrammerCount]): DefaultCategoryDataset = {
    val ds = new DefaultCategoryDataset
    dataSet.foreach(x => ds.addValue(x.count, x.gender,x.language))
    ds
  }


  def drawBarGraph(dataSet: DefaultCategoryDataset): Unit = {
    val chart = BarChart(dataSet,stacked = true,threeDimensional = true)
    chart.show("Diversity according to programming language")
  }

  def demoBar: Unit = {

      val dataArray = Array(
        ProgrammerCount("Women","Scala",5),
        ProgrammerCount("Men","Scala",7),

        ProgrammerCount("Men","C#",13),
        ProgrammerCount("Women","C#",4)
      )

      drawBarGraph(graphDataSetFromCount(dataArray))
  }


  def readCsvManually: Unit = {
    var bufferedSource = io.Source.fromResource("2016_StackOverflow_Data.csv")

    val filteredData = bufferedSource.getLines().drop(1)
      .filter(x => x.indexOf(",,") < 0)
      .map(x => {
        println(x)
        x
      })
      .map(line => line.split(","))
      .map(columns => {
        (columns(7),columns(16),columns(17))
      })
//      .map(x => {
//        println(s"${x._1} | ${x._2} | ${x._3}")
//        x
//      })

    println(s"Filtered pure data contains: ${filteredData.length} participants")


    bufferedSource.close
  }

  def main(args: Array[String]): Unit = {
//     demoBar
    readCsvManually

    //val result = Parser.parse[Person](csv)
  }
}
