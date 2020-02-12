package org.training.spark.apiexamples

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("wordcount")
      .setMaster(args(0))

    val sc = new SparkContext(conf)

    val inputRDD = sc.textFile(args(1))

    val wordPairRDD = inputRDD.flatMap(rec => rec.split(" ")).map(word => (word, 1))

    val wordCountRDD = wordPairRDD.reduceByKey(_ + _)

    wordCountRDD.foreach(println)

    //Thread.sleep(1000000)

  }
}
