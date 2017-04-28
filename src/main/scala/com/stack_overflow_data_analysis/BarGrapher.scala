package com.stack_overflow_data_analysis

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

case class LineGraphItem(label: String,x: Double, y: Double)

class LinePlotter {
    val ATTENTION = "Attention"
    val MEDITATION = "Meditation"

    def graphDataFromGroup(dataSet: Array[LineGraphItem]): DefaultCategoryDataset = {
        dataSet.foldLeft(new DefaultCategoryDataset)((acc,x) => {
            acc.addValue(x.y,x.label,x.x)
            acc
        })
    }

    def drawLineGraph(dataSet: DefaultCategoryDataset, path: String): Unit = {
        val chart = LineChart(dataSet,threeDimensional = false)
        chart.saveAsPNG(path)
    }

    def draw(): Unit = {
        val data = new DefaultCategoryDataset
        data.addValue(100.0, ATTENTION, 1)
        data.addValue(200.0, ATTENTION, 2)
        data.addValue(300.0, ATTENTION, 3)
        data.addValue(400.0, ATTENTION, 4)
        data.addValue(500.0, ATTENTION, 5)

        data.addValue(500.0, MEDITATION, 1)
        data.addValue(400.0, MEDITATION, 2)
        data.addValue(300.0, MEDITATION, 3)
        data.addValue(200.0, MEDITATION, 4)
        data.addValue(100.0, MEDITATION, 5)

        drawLineGraph(data, "./charts/lineTest.png")
    }
}
