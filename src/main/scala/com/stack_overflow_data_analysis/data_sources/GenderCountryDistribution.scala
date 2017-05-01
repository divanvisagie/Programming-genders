package com.stack_overflow_data_analysis.data_sources

import com.stack_overflow_data_analysis.models.BarGraphGroup
import com.stack_overflow_data_analysis.models.BarGraphGroup
import kantan.csv._
import kantan.csv.ops._

import scala.collection.mutable

class GenderCountryDistribution extends CSVReader {
    def data(): Array[BarGraphGroup] = {

        val counter = mutable.Map[String,GenderCount]()

        readRawResponse()
            .filter(_.exists(x => {
                !x.gender.isEmpty && !x.country.isEmpty
            }))
            .mapResult(f => (
              f.gender,
              f.country
            ))
            .foldLeft(counter)((acc,x) => {
                x match {
                    case Success(r) =>
                        val (gender, country) = r
                        if(!counter.keys.toArray.contains(country)) acc += (country -> GenderCount(0,0,0,0))
                        gender match {
                            case "Male" => acc(country).male += 1
                            case "Female" => acc(country).female += 1
                            case "Other" => acc(country).other += 1
                            case _ => acc(country).undisclosed += 1
                        }

                    case _ => print("Error Ocurred")
                }
                acc
            })
            .filter(x => {
                x._2.male > 1000
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

object GenderCountryDistribution {
    def apply(): GenderCountryDistribution = new GenderCountryDistribution ()
}
