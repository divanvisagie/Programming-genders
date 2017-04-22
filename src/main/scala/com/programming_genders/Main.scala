package com.programming_genders

import com.programming_gender.{LanguageGenderDistribution, OSGenderDistribution}

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

  def drawBarGraph(dataSet: DefaultCategoryDataset, path: String): Unit = {
    val chart = BarChart(dataSet,stacked = true,threeDimensional = true)
    chart.saveAsPNG(path)
  }

  def main(args: Array[String]): Unit = {
    val genderPercentageDataset = graphDataSetFromCount(LanguageGenderDistribution().percentageData)
    drawBarGraph(genderPercentageDataset, "./charts/languageGenderPercentageDistribution.png")

    val genderDataset = graphDataSetFromCount(LanguageGenderDistribution().data)
    drawBarGraph(genderDataset, "./charts/languageGenderDistribution.png")

    val genderOSDataset = graphDataSetFromCount(OSGenderDistribution().data)
    drawBarGraph(genderOSDataset, "./charts/osGenderDistribution.png")

    val genderOSPercentageDataset = graphDataSetFromCount(OSGenderDistribution().percentageData)
    drawBarGraph(genderOSPercentageDataset, "./charts/osGenderPercentageDistribution.png")
  }
}
