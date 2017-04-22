package com.programming_gender

import com.programming_genders.GenderGroup

import scala.collection.mutable

class OSGenderDistribution extends CSVReader with GenderGrouper {

    val csvPath = "./data/genderos.csv"

    def data(): Array[GenderGroup] = {
        val data = readCSV()
            .map(columns => {
                (columns(0),columns(1).toInt, columns(2).toInt, columns(3).toInt, columns(4).toInt)
            })
        val reducedData = data.foldLeft[mutable.Map[String,Tuple5[String,Int,Int,Int,Int]]](mutable.Map(
            "OSX"     -> ("OSX",    0,0,0,0),
            "Windows" -> ("Windows",0,0,0,0),
            "Linux"   -> ("Linux",  0,0,0,0)
        ))( (acc, distro) => {
            val key = distro._1 match {
                case s if Array("Mac OS X").contains(s) => "OSX"
                case s if s.contains("Windows") => "Windows"
                case _ => "Linux"
            }
            acc(key) = (
              key,
              acc(key)._2 + distro._2,
              acc(key)._3 + distro._3,
              acc(key)._4 + distro._4,
              acc(key)._5 + distro._5
            )
            acc
        }).values.toArray

        groupData(reducedData)
    }
}

object OSGenderDistribution {
    def apply(): OSGenderDistribution = new OSGenderDistribution()
}