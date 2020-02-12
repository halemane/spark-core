package org.training.spark.apiexamples.advanced

import org.apache.spark.{SparkConf, SparkContext}

object MapPartitions {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("map partition example")
    conf.setMaster(args(0))
    val sc = new SparkContext(conf)
    val salesData = sc.textFile(args(1),3)

//    val (itemMin,itemMax)=salesData.mapPartitions(partitionItertor => {
//
//      val (min,max) = partitionItertor.foldLeft((Double.MaxValue,Double.MinValue))((acc,salesRecord) => {
//        val itemValue = salesRecord.split(",")(3).toDouble
//        (acc._1 min itemValue , acc._2 max itemValue)
//      })
//      println("min" + min + "max " + max)
//      List((min,max)).iterator
//    }).reduce((a,b)=> (a._1 min b._1 , a._2 max b._2))

    val result=salesData.mapPartitions(partitionItertor => {
      val (min,max) = partitionItertor.foldLeft((Double.MaxValue,Double.MinValue))((acc,salesRecord) => {
        val itemValue = salesRecord.split(",")(3).toDouble
        println((acc._1 min itemValue , acc._2 max itemValue))
        (acc._1 min itemValue , acc._2 max itemValue)
      })
      //println("min" + min + "max " + max)
      List((min,max)).iterator
    })

    //result.foreach(println)

    val (itemMin,itemMax)=result.reduce((a,b)=> (a._1 min b._1 , a._2 max b._2))

    println("min = "+itemMin + " max ="+itemMax)

  }

}
