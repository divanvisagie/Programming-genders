package com.programming_genders

import java.io.File

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

trait CSVReader {
    def readRawResponse() : CsvReader[ReadResult[RawResponseRow]] = {
        new File("./data/2016 Stack Overflow Survey Results/2016 Stack Overflow Survey Responses.csv")
          .asCsvReader[RawResponseRow](rfc.withHeader)
    }
}



