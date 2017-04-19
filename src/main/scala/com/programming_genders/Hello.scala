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
    var bufferedSource = io.Source.fromResource("genderlang.csv")

    val filteredData = bufferedSource.getLines()
      .map(x => {
        println(x)
        x
      })
      .map(line => line.split(","))
      .map(columns => {
        (columns(0),columns(1),columns(2))
      })


    println(s"Filtered pure data contains: ${filteredData.length} participants")


    bufferedSource.close
  }

  def main(args: Array[String]): Unit = {
   readCsvManually



    //val result = Parser.parse[Person](csv)
  }
}
