import java.util
import java.util.concurrent.Future

import AkkaProducer.{kafkaTopic, partition}
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, ProducerSettings, Subscriptions}
import akka.stream.{ActorMaterializer, Materializer}
import akka.stream.scaladsl.Sink
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.producer.{Producer, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, ByteArraySerializer, StringDeserializer, StringSerializer}

import scala.collection.convert.ImplicitConversionsToScala.`iterator asScala`
import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}
import scala.concurrent.duration._

object AryaProducer extends App {

  case class TestEhsan(ehsan: String)
  implicit val sys: ActorSystem = ActorSystem("Test-Actor-System")
  implicit val mat: Materializer = ActorMaterializer()
  val kafkaServers = "localhost:9092"
  val topic = "ehsan-topic"
  val partition        = 0

  val producerSettings = ProducerSettings(sys, new StringSerializer, new StringSerializer)
    .withBootstrapServers(kafkaServers)
  val producer: Producer[String, String] = producerSettings.createKafkaProducer()
  producer.send(new ProducerRecord[String, String](topic, "dddddddddddddd"))

  val consumerSettings: ConsumerSettings[String, Array[Byte]] = ConsumerSettings(sys, new StringDeserializer, new ByteArrayDeserializer)
    .withBootstrapServers(kafkaServers).withGroupId("tttttttttttttttt")
//  val consumer = consumerSettings.createKafkaConsumer()
//  consumer.subscribe(util.Arrays.asList(topic))
//    val x: ConsumerRecords[String, String] = consumer.poll(10)

  println("end of consuming")


  val subscription     = Subscriptions.assignment(new TopicPartition(topic, partition))
  val done = Consumer
    .plainSource(consumerSettings, subscription)
//    .plainSource(consumerSettings, Subscriptions.topics(topic))
    .runWith(Sink.foreach(a => println("adfadsf"))) // just print each message for debugging

  implicit val ec: ExecutionContextExecutor = sys.dispatcher
  done onComplete  {
    case Success(_) => println("Done"); sys.terminate()
    case Failure(err) => println(err.toString); sys.terminate()
  }



}
