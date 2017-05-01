package com.stack_overflow_data_analysis.graphers

import com.stack_overflow_data_analysis.BarGraphGroup
import com.stack_overflow_data_analysis.data_sources.LanguageGenderDistribution
import org.jfree.data.category.DefaultCategoryDataset

import scalax.chart.api._

class BarGrapher {
    def graphDataSetFromCount(dataSet: Array[BarGraphGroup]): DefaultCategoryDataset = {
        dataSet.foldLeft(new DefaultCategoryDataset)((acc,x) => {
            acc.addValue(x.count, x.group,x.label)
            acc
        })
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




