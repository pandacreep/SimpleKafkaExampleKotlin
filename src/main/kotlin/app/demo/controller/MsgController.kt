package app.demo.controller

import app.demo.dto.Request
import app.demo.dto.UserDto
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("msg")
class MsgController {
    @Autowired
    private val kafkaTemplate: KafkaTemplate<Long, UserDto>? = null

    @PostMapping
    fun sendOrder(@RequestBody request: Request) {
        val msgId = request.msgId
        var msg = request.userDto

        println("***** kafka produce message")
        val future = kafkaTemplate?.send("msg", msgId, msg)
        future?.addCallback(System.out::println, System.err::println)
        println("message $msg was sent at${LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)}")
    }
}

//POST request http://localhost:8888/msg
//{
//    "msgId": 11,
//    "userDto": {
//    "age": 27,
//    "name": "Anna",
//    "address": null
//}
//}