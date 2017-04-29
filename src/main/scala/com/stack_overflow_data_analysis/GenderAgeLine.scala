package com.stack_overflow_data_analysis

import com.stack_overflow_data_analysis.{BarGraphGroup, CSVReader}
import kantan.csv._
import kantan.csv.ops._

import scala.collection.mutable

class GenderAgeLine extends CSVReader {
    def data(): Array[LineGraphItem] = {
        type AgeCounter = mutable.Map[Double,Int]
        val counter = mutable.Map[String,AgeCounter]()
        
        readRawResponse()
            .filter(_.exists(x => {
                !x.gender.isEmpty
            }))  
            .mapResult(f => (
                f.gender,
                f.ageMidPoint
            ))
            .foldLeft(counter)((acc,x) => {
                x match {
                    case Success(r) => {
                        val (gender, ageMidPoint) = r
                        if (!acc.keys.toArray.contains(gender)) {
                            acc += (gender -> mutable.Map[Double,Int]())
                        }
                        if (!acc(gender).keys.toArray.contains(ageMidPoint)) {
                            acc(gender) += (ageMidPoint -> 0)
                        }
                        acc(gender)(ageMidPoint) += 1 
                    }
                    case _ => println("Error Occurred")
                }
                acc
            })
            .flatMap(x => {
                val (gender,ageCounter) = x
                ageCounter.keys.map(key => {
                    LineGraphItem(gender,ageCounter(key),key)
                })
            })
            .map(f => {
                println(f)
                f
            })
            .toArray.sortBy(_.y)
    }
}
object GenderAgeLine {
    def apply(): GenderAgeLine = new GenderAgeLine()
}