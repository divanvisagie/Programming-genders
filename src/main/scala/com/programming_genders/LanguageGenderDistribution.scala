package com.programming_gender

import com.programming_genders.GenderGroup

class LanguageGenderDistribution extends CSVReader with GenderGraph {
    val csvPath = "./data/genderlang.csv"

    def percentageData: Array[GenderGroup] = {
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

            (columns(0),menperc.toInt,womenperc.toInt,otherperc.toInt, undisclosedperc.toInt)
        })
        flattenData(filteredData)
    } 

    def data: Array[GenderGroup] = {
        val data = readCSV()
            .map(columns => {
                (columns(0),columns(1).toInt, columns(2).toInt, columns(3).toInt, columns(4).toInt)
            })
        flattenData(data)
    }

}
object LanguageGenderDistribution {
    def apply(): LanguageGenderDistribution = new LanguageGenderDistribution()
}