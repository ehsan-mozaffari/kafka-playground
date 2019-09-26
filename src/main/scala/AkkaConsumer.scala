import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util
import scala.collection.JavaConverters._

class AkkaConsumer {
//  def main(args: Array[String]): Unit = {
//    consumeFromKafka("quick-start")
//  }
//  def consumeFromKafka(topic: String): Unit = {
//    val props = new Properties()()
//
//    props.put("bootstrap.servers", "localhost:9094")
//    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    props.put("auto.offset.reset", "latest")
//    props.put("group.id", "consumer-group")
//    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
//    consumer.subscribe(Array(topic))
//    while (true) {
//      val record = consumer.poll(1000).asScala
//      for (data <- record.iterator)
//        println(data.value())
//    }
//  }
}
