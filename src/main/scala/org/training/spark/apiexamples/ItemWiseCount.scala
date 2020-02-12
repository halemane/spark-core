package org.training.spark.apiexamples

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD


object ItemWiseCount {


  def getTuple(record: String) ={
    val columns = record.split(",")
    (columns(2), 1)
  }

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("apiexamples")
    conf.setMaster(args(0))
    val sc = new SparkContext(conf)

    val dataRDD = sc.textFile(args(1), 2)

    //val itemPair = dataRDD.map(getTuple)


      val itemPair = dataRDD.map(record => {
        val columns = record.split(",")
        (columns(2), 1)
      })

    val result = itemPair.reduceByKey((x, y) => x + y)
    //val resultRdd = itemPair.reduceByKey(_+_).sortBy(- _._2)

    //resultRdd.saveAsTextFile("output/path")
    //println(result.collect().foreach(println))

    result.foreach(println)


    /*val custItemPair = dataRDD.map(x => {
      val splits = x.split(",")
      ((splits(1),splits(2)),1)
    })

    custItemPair.groupByKey().map(x => s"${x._1._1} ${x._1._2} ${x._2.size}")//.foreach(println)
    */

  }
}
