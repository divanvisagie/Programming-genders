package com.stack_overflow_data_analysis.data_sources

import com.stack_overflow_data_analysis.models.BarGraphGroup
import kantan.csv._
import kantan.csv.ops._

import scala.collection.mutable




class LanguageGenderDistribution extends CSVReader {
    def defaultFilters = Array("Java","C#","JavaScript","C++", "C", "Python")

    def data(filters: Array[String] = defaultFilters): Array[BarGraphGroup] = {

        val counter = mutable.Map[String,GenderCount]()

        readRawResponse()
            .filter(_.exists(x => {
                !x.gender.isEmpty && !x.techDo.isEmpty && !x.techWant.isEmpty
            }))
            .mapResult(f => (
                f.gender,
                f.techDo.split(';').map(_.trim),
                f.techWant.split(';').map(_.trim)
            ))
            .foldLeft(counter)((acc,x) => {
                x match {
                    case Success(r) =>
                        r._2.foreach(tech => {
                            if (!acc.keys.toArray.contains(tech)) acc += (tech -> GenderCount(0,0,0,0))
                            r._1 match {
                                case "Male" => acc(tech).male += 1
                                case "Female" => acc(tech).female += 1
                                case "Other" => acc(tech).other += 1
                                case _ => acc(tech).undisclosed += 1
                            }
                        })
                        r._3.foreach(tech => {
                            if (!acc.keys.toArray.contains(tech)) acc += (tech -> GenderCount(0,0,0,0))
                            r._1 match {
                                case "Male" => acc(tech).male += 1
                                case "Female" => acc(tech).female += 1
                                case "Other" => acc(tech).other += 1
                                case _ => acc(tech).undisclosed += 1
                            }
                        })
                    case _ => println("Error Occurred")
                }
                acc
            })
            .filter(x => {
                println(x._1)
                filters.contains(x._1)
            })
            .flatMap(x => {
                val (key, value) = x
                List(
                    BarGraphGroup("Women", key, value.female),
                    BarGraphGroup("Men", key, value.male),
                    BarGraphGroup("Other", key, value.other),
                    BarGraphGroup("Undisclosed", key, value.undisclosed)
                )
            }).toArray
    }
}
object LanguageGenderDistribution {
    def apply(): LanguageGenderDistribution = new LanguageGenderDistribution()
}