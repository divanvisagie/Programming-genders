package com.stack_overflow_data_analysis

import com.stack_overflow_data_analysis.graphers.{BarGrapher, LinePlotter}


object Main {
    def main(args: Array[String]): Unit = {
        new BarGrapher().draw()
        new LinePlotter().draw()
    }
}
