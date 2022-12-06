package app.demo

import app.demo.dto.UserDto
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener

@EnableKafka
@SpringBootApplication
class SimpleKafkaExampleKotlinApplication {
    @KafkaListener(topics = ["msg"])
    fun msgListener(record: ConsumerRecord<Long, UserDto>) {
        println("***** kafka consume message")
        println("record.partition(): ${record.partition()}")
        println("record.key(): ${record.key()}")
        println("record.value(): ${record.value()}")
    }
}

fun main(args: Array<String>) {
    runApplication<SimpleKafkaExampleKotlinApplication>(*args)
}
