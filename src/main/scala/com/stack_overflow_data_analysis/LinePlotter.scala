package com.stack_overflow_data_analysis

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
        chart.saveAsPNG(path)
    }

    def draw(): Unit = {

        val genderDataSet = graphDataFromGroup(GenderAgeLine().data())
        drawLineGraph(genderDataSet,"./charts/genderAgeLine.png")
    }
}
