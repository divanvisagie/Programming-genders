var bufferedSource = io.Source.fromResource("2016_StackOverflow_Data.csv")

bufferedSource.getLines().next()


//for (line <- bufferedSource.getLines) {
//  val cols = line.split(",").map(_.trim)
//  // do whatever you want with the columns here
//  println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
//}
//bufferedSource.close