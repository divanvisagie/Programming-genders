package com.stack_overflow_data_analysis.graphers

import com.stack_overflow_data_analysis.data_sources.{GenderCountryDistribution, LanguageGenderDistribution}
import com.stack_overflow_data_analysis.models.BarGraphGroup
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

        chart.saveAsPNG(s"$path.png", (1000,600))
        chart.saveAsSVG(s"$path.svg")
    }

    def draw(): Unit = {
        val genderDataset = graphDataSetFromCount(LanguageGenderDistribution().data())
        drawBarGraph(genderDataset, "./charts/languageGenderDistribution")

        val functionalFilters = Array("Haskell","Rust","Scala","Clojure", "F#","R")
        val functionalGenderDataSet = graphDataSetFromCount(LanguageGenderDistribution().data(functionalFilters))
        drawBarGraph(functionalGenderDataSet,"./charts/functionalLanguageGenderDistribution")

        val genderCountryData = graphDataSetFromCount(GenderCountryDistribution().data())
        drawBarGraph(genderCountryData, "./charts/genderCountryDistribution")
    }
}




