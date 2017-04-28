package com.programming_genders

import com.programming_gender.LanguageGenderDistribution
import java.io.File

import kantan.csv

import scalax.chart.api._
import org.jfree.data.category._
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

import scala.collection.mutable

case class BarGraphGroup(gender: String, language: String, count: Int)

class Grapher {
    def graphDataSetFromCount(dataSet: Array[BarGraphGroup]): DefaultCategoryDataset = {
        val ds = new DefaultCategoryDataset
        dataSet.foreach(x => ds.addValue(x.count, x.gender,x.language))
        ds
    }

    def drawBarGraph(dataSet: DefaultCategoryDataset, path: String): Unit = {
        val chart = BarChart(dataSet,stacked = true,threeDimensional = true)
        chart.saveAsPNG(path)
    }

    def draw(): Unit = {
        val genderDataset = graphDataSetFromCount(LanguageGenderDistribution().data())
        drawBarGraph(genderDataset, "./charts/languageGenderDistribution.png")

        val functionalFilters = Array("Haskell","Rust","Scala","Clojure")
        val functionalGenderDataSet = graphDataSetFromCount(LanguageGenderDistribution().data(functionalFilters))
        drawBarGraph(functionalGenderDataSet,"./charts/functionalLanguageGenderDistribution.png")
    }
}


object Main {
    def main(args: Array[String]): Unit = {
        new Grapher().draw()
    }
}
