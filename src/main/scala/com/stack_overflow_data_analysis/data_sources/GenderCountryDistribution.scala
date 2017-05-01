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
                (x._2.male + x._2.female + x._2.other + x._2.undisclosed) > 1000
            })
            .flatMap(x => {
                val (key, value) = x

                val keyToUse = key match {
                    case "United Kingdom" => "U.K"
                    case s: String => s
                }

                List(
                    BarGraphGroup("Women", keyToUse, value.female),
                    BarGraphGroup("Men", keyToUse, value.male),
                    BarGraphGroup("Other", keyToUse, value.other),
                    BarGraphGroup("Undisclosed", keyToUse, value.undisclosed)
                )
            }).toArray
    }
}

object GenderCountryDistribution {
    def apply(): GenderCountryDistribution = new GenderCountryDistribution ()
}
