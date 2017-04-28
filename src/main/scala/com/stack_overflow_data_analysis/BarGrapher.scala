package com.stack_overflow_data_analysis

import org.jfree.data.category.DefaultCategoryDataset
import scalax.chart.api._

class BarGrapher {
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

        val functionalFilters = Array("Haskell","Rust","Scala","Clojure", "F#","R")
        val functionalGenderDataSet = graphDataSetFromCount(LanguageGenderDistribution().data(functionalFilters))
        drawBarGraph(functionalGenderDataSet,"./charts/functionalLanguageGenderDistribution.png")
    }
}
