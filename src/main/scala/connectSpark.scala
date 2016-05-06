/**
  * Created by gavelar on 29/04/16.
  * #  ___________________________________________________________
    # |    Evaluate connection between Kafka and Spark Streaming  |
    # |              *Using zookeeper*                            |
    # |                                                           |
    # |           Author: Gustavo de Paula Avelar                 |
    # |___________________________________________________________|
*/


/* PageRank.scala */
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object connectSpark{
  def main(args: Array[String]) {

      // SparkContext

      val conf = new SparkConf().setAppName("KafkaReceiver").setMaster("local[*]")

     println(conf)
      // val ssc = new StreamingContext(conf, Seconds(1))

    //val conf = new SparkConf().setAppName("KafkaReceiver").setMaster("local[2]")
      // val conf = new SparkConf().setAppName("KafkaReceiver").setMaster("spark://spark01.ctweb.inweb.org.br:7077")
      // val conf = new SparkConf().setMaster("spark://spark01.ctweb.inweb.org.br:7077").setAppName("KafkaRecei").set("spark.ui.port", "44040" ).set("spark.driver.allowMultipleContexts", "true")
      // val conf = new SparkConf().setAppName("KafkaReceiver").setMaster('yarn-client')


      val ssc = new StreamingContext(conf, Seconds(10))

      val kafkaStream = KafkaUtils.createStream(ssc, "localhost:2181", "spark-streaming-consumer-group", Map("spark-topic" -> 5))

      kafkaStream.print()

      ssc.start
  }
}