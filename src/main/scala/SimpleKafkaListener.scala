/**
  * Created by gavelar on 03/05/16.
  */
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object SimpleKafkaListener {


    def main(args: Array[String]) {

      val Array(zkQuorum, group, topic) = args

      //val sc = new SparkContext()
      // Funciona para teste local
//      val conf = new SparkConf().setAppName("KafkaReceiver").setMaster("local[*]")
      val conf = new SparkConf().setAppName("KafkaReceiver").setMaster("yarn-client")
      val ssc = new StreamingContext(conf, Seconds(5))

       val topics = List((topic, 1)).toMap
      // Parametros SparkStreamContext, ZookeeperServer, Nome do grupo e nome do tópico criado.
       val kafkaStream = KafkaUtils.createStream(ssc, zkQuorum, group, topics)
//      val kafkaStream = KafkaUtils.createStream(ssc, "localhost:2181","spark-streaming-consumer-group", Map("spark-topic" -> 5))
      val lines = kafkaStream.map(_._2).filter(_.contains("error"))
      lines.print()

      ssc.start()
      ssc.awaitTermination()

    }
}

// /opt/spark-1.6.0-bin-hadoop2.6/bin/spark-submit --class SimpleKafkaListener file:/home/ubuntu/simple-spark-app_2.10-1.0.jar  localhost:2181 spark-group topic-spark