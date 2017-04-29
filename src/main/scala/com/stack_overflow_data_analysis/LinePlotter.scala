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

        var genderDataSet = graphDataFromGroup(GenderAgeLine().data())
        drawLineGraph(genderDataSet,"./charts/genderAgeLine.png")
    }
}
