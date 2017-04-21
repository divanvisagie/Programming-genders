package com.programming_gender

import com.programming_genders.GenderGroup

class LanguageGenderDistribution {
    def readCSV(): Array[Array[String]] = {
        var bufferedSource = io.Source.fromFile("./data/genderlang.csv")
        val lines = bufferedSource.getLines().toArray
        bufferedSource.close
        lines.map(x => {
            println(x)
            x
        })
        .map(line => line.split(","))
    }

    def getPercentageData(): Array[GenderGroup] = {
        val filteredData = readCSV()
        .map(columns => {
            val men         = columns(1).toInt * 1.0
            val women       = columns(2).toInt * 1.0
            val other       = columns(3).toInt * 1.0
            val undisclosed = columns(4).toInt * 1.0

            val total = men + women + other + undisclosed

            val menperc         = men/total   * 100
            val womenperc       = women/total * 100
            val otherperc       = other/total * 100
            val undisclosedperc = other/total * 100

            //println(s"men $men - ${menperc} women $women - ${womenperc}")
            (columns(0),menperc.toInt,womenperc.toInt,otherperc.toInt, undisclosedperc.toInt)
        })
        groupData(filteredData)
    } 

    def getData(): Array[GenderGroup] = {
        val data = readCSV()
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
object LanguageGenderDistribution {
    def apply(): LanguageGenderDistribution = new LanguageGenderDistribution()
}