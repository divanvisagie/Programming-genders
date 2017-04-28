package com.programming_genders

import com.programming_gender.{LanguageGenderDistribution, OSGenderDistribution}
import java.io.File

import kantan.csv

import scalax.chart.api._
import org.jfree.data.category._
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

case class GenderGroup(gender: String, language: String, count: Int)

class Grapher {
    def graphDataSetFromCount(dataSet: Array[GenderGroup]): DefaultCategoryDataset = {
        val ds = new DefaultCategoryDataset
        dataSet.foreach(x => ds.addValue(x.count, x.gender,x.language))
        ds
    }

    def drawBarGraph(dataSet: DefaultCategoryDataset, path: String): Unit = {
        val chart = BarChart(dataSet,stacked = true,threeDimensional = true)
        chart.saveAsPNG(path)
    }

    def draw(): Unit = {
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




object Main {

    def readRawResponse() : CsvReader[ReadResult[RawResponseRow]] = {
        new File("./data/2016 Stack Overflow Survey Results/2016 Stack Overflow Survey Responses.csv")
          .asCsvReader[RawResponseRow](rfc.withHeader)
    }

    def genderLanguageReader(): Unit = {
        readRawResponse()
            .filter(_.exists(x => {
                !x.gender.isEmpty && !x.techDo.isEmpty && !x.techWant.isEmpty
            }))
            .mapResult(f => (
                f.gender,
                f.techDo.split(';').map(_.trim),
                f.techWant.split(';').map(_.trim)
            ))
            .foreach {
                case Success(r: (String,Array[String],Array[String])) => println(s"${r._1}")
                case _ => println("Invalid Row")
            }
    }

    def testReader(): Unit = {
        readRawResponse()
            .filter(_.exists(!_.country.isEmpty))
            .mapResult(f => f)
            .foreach {
                case Success(r: RawResponseRow) => println(s"${r.id} |${r.country} | ${r.gender} | ${r.jobSatisfaction} | ${r.googleInterviewLikelihood}")
                case _ => println("Invalid Row")
            }
    }

    def main(args: Array[String]): Unit = {
        genderLanguageReader()
    }
}
