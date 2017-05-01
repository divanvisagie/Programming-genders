package com.stack_overflow_data_analysis.graphers

import com.stack_overflow_data_analysis.data_sources.GenderAgeLine
import com.stack_overflow_data_analysis.models.LineGraphItem
import org.jfree.data.category.DefaultCategoryDataset

import scalax.chart.api._

class LinePlotter {
    val ATTENTION = "Attention"
    val MEDITATION = "Meditation"

    def graphDataFromGroup(dataSet: Array[LineGraphItem]): DefaultCategoryDataset = {
        dataSet.foldLeft(new DefaultCategoryDataset)((acc,x) => {
            println(s"adding ${x.y} | ${x.label} | ${x.x}")
            acc.addValue(x.x,x.label,x.y)
            acc
        })
    }

    def drawLineGraph(dataSet: DefaultCategoryDataset, path: String): Unit = {
        val chart = LineChart(dataSet,threeDimensional = false)
        chart.saveAsSVG(s"$path.svg", (800,500))
    }


        def draw(): Unit = {
        val genderDataSet = graphDataFromGroup(GenderAgeLine().data())
        drawLineGraph(genderDataSet,"./docs/charts/genderAgeLine")
    }
}
