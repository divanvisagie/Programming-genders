package com.programming_gender

import com.programming_genders.GenderGroup

class LanguageGenderDistribution {
    def getData(): Array[GenderGroup] = {
        var bufferedSource = io.Source.fromFile("./data/genderlang.csv")

        val filteredData = bufferedSource.getLines()
        .map(x => {
            println(x)
            x
        })
        .map(line => line.split(","))
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
        }).toArray

        filteredData.foreach(println)

        bufferedSource.close
        
        filteredData.flatMap(x => List(
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