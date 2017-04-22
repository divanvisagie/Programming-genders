package com.programming_gender

import com.programming_genders.GenderGroup

trait CSVReader {
    def csvPath: String

    def readCSVToArray(): Array[Array[String]] = {
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

trait GenderGraph {
    type GenderTuple = (String, Int, Int, Int, Int)

    def readCSVToArray(): Array[Array[String]]

    def data: Array[GenderGroup]

    def flattenData(data: Array[GenderTuple]) : Array[GenderGroup] = {
        data.flatMap(x => List(
            GenderGroup("Women", x._1, x._3),
            GenderGroup("Men", x._1, x._2),
            GenderGroup("Other",x._1,x._4),
            GenderGroup("Undisclosed",x._1,x._5)
        ))
    }

    def columns: Array[GenderTuple] = {
        readCSVToArray()
            .map(columns => {
                (columns(0),columns(1).toInt, columns(2).toInt, columns(3).toInt, columns(4).toInt)
            })
    }
}

