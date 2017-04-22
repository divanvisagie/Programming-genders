package com.programming_gender

import com.programming_genders.GenderGroup

import scala.collection.mutable


class OSGenderDistribution extends CSVReader with GenderGraph {

    val csvPath = "./data/genderos.csv"

    type CountedGenderTuple = (String, Int, Int, Int, Int, Int)

    private def reduceDistros(distros: Array[GenderTuple]): Array[GenderTuple] = {
        distros.foldLeft[mutable.Map[String,GenderTuple]](mutable.Map(
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
    }

    def data: Array[GenderGroup] = {
        val data = reduceDistros(columns)
        flattenData(data)
    }

    def percentageData: Array[GenderGroup] = {
        val data =  reduceDistros(columns)
        val percData = data.map(x => {
            val men         = x._2 * 1.0
            val women       = x._3 * 1.0
            val other       = x._4 * 1.0
            val undisclosed = x._5 * 1.0

            val total = men + women + other + undisclosed

            val menperc         = men/total   * 100
            val womenperc       = women/total * 100
            val otherperc       = other/total * 100
            val undisclosedperc = other/total * 100

            (x._1,menperc.toInt,womenperc.toInt,otherperc.toInt, undisclosedperc.toInt)
        })
        flattenData(percData)
    }
}

object OSGenderDistribution {
    def apply(): OSGenderDistribution = new OSGenderDistribution()
}