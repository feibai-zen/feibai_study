package com.atguigu.spark

import org.apache.spark.SparkConf
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

object SparkKafkaConsumer {

  def main(args: Array[String]): Unit = {

    // 1 初始化上下文环境
    val conf = new SparkConf().setMaster("local[*]").setAppName("spark-kafka")
    val ssc = new StreamingContext(conf, Seconds(3))


    // 2 消费数据
    val kafkapara  = Map[String,Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG->"hadoop102:9092,hadoop103:9092",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG->classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG->classOf[StringDeserializer],
      ConsumerConfig.GROUP_ID_CONFIG->"test"
    )
    val KafkaDSteam = KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String](Set("first"), kafkapara))

    // key "" value "atguigu"
    val valueDStream = KafkaDSteam.map(record => record.value())

    valueDStream.print()

    // 3 执行代码 并阻塞
    ssc.start()
    ssc.awaitTermination()
  }

}
