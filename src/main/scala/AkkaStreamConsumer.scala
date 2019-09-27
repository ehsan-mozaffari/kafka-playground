import akka.Done
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.{ActorMaterializer, Materializer}
import akka.stream.scaladsl.{Keep, Sink}
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

import scala.concurrent.Future

// bin/windows/zookeeper-server-start.bat config/zookeeper.properties
// bin/windows/kafka-server-start.bat config/server.properties

object AkkaStreamConsumer extends App {
  implicit val system: ActorSystem  = ActorSystem("Test-Actor-System")
  implicit val mat:    Materializer = ActorMaterializer()
  val bootstrapServers = "localhost:9092"
  val topic            = "ehsan-test"
  val consumerGroup    = "group1"

  val config = system.settings.config.getConfig("akka.kafka.consumer")

  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new ByteArrayDeserializer)
      .withBootstrapServers(bootstrapServers)
      .withGroupId(consumerGroup)
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  val subscription = Subscriptions.topics(topic)
  val consumer1    = Consumer.plainSource(consumerSettings, subscription)
//  val consumer = consumerSettings.createKafkaConsumer()
  val db = new OffsetStore
  consumer1
    .mapAsync(1)(db.businessLogicAndStoreOffset)
    .toMat(Sink.seq)(Keep.both)
    .mapMaterializedValue(DrainingControl.apply)
    .run()
}

class OffsetStore {

  def businessLogicAndStoreOffset(record: ConsumerRecord[String, Array[Byte]]): Future[Done] =
    ??? // ...
  def loadOffset(): Future[Long] = ??? // ...
}
