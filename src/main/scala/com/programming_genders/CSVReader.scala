package com.programming_gender

import com.programming_genders.GenderGroup

trait CSVReader {
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

    
}