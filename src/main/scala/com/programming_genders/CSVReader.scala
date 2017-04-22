package com.programming_gender

import com.programming_genders.GenderGroup

trait CSVReader {
    def csvPath: String

    def readCSV(): Array[Array[String]] = {
        val bufferedSource = io.Source.fromFile(csvPath)
        val lines = bufferedSource.getLines().toArray
        bufferedSource.close
        lines.map(x => {
            println(x)
            x
        })
        .map(line => line.split(","))
    }
}

trait GenderGrouper {
    def groupData(data: Array[Tuple5[String,Int,Int,Int,Int]]) : Array[GenderGroup] = {
        data.flatMap(x => List(
            GenderGroup("Women", x._1, x._3),
            GenderGroup("Men", x._1, x._2),
            GenderGroup("Other",x._1,x._4),
            GenderGroup("Undisclosed",x._1,x._5)
        ))
    }
}