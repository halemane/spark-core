package org.training.spark.apiexamples.serialization


case class SalesRecord(transactionId: String,
                  customerId: String,
                  itemId: String,
                  itemValue: Double)
{

/*
  override def toString: String = {
    transactionId+","+customerId+","+itemId+","+itemValue
  } */
}
