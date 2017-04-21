package com.programming_gender

import com.programming_genders.GenderGroup

class OSGenderDistribution {
    def readCSV(): Array[Array[String]] = {
        var bufferedSource = io.Source.fromFile("./data/genderos.csv")
        val lines = bufferedSource.getLines().toArray
        bufferedSource.close
        lines.map(x => {
            print(x)
            x
        })
        .map(line => line.split(","))
    }

    def getData(): Array[GenderGroup] = {
        var data = readCSV()
            .map(columns => {
                (columns(0),columns(1).toInt, columns(2).toInt, columns(3).toInt, columns(4).toInt)
            })
        groupData(data)
    }

    def groupData(data: Array[Tuple5[String,Int,Int,Int,Int]]) : Array[GenderGroup] = {
         data.flatMap(x => List(
            GenderGroup("Women", x._1, x._3),
            GenderGroup("Men", x._1, x._2),
            GenderGroup("Other",x._1,x._4),
            GenderGroup("Undisclosed",x._1,x._5)
        ))
    }
}