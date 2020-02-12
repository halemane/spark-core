package org.training.spark.anatomy.partition

import org.apache.spark.Partitioner


class CustomerPartitioner(np: Int) extends Partitioner {
  
  def numPartitions: Int = np

  def getPartition(key: Any): Int = {
    println(s"customer partitioner is called for key:$key")
    key match {
      case null => 0
      case _ => {
        val keyValue = key.toString.toInt
        if (keyValue >= 3) 1 else 0
      }
    }
  }
}
