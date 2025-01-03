package com.example.kotlinspring

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/")
class MessageController(private val service: MessageService) {
//    @GetMapping("hello")
//    fun index(@RequestParam("name") name: String?) = "Hello, ${name ?: "World"}!"
    private val logger = LoggerFactory.getLogger(MessageController::class.java)

    @GetMapping
    fun listMessages() = service.findMessages()

    @GetMapping("{id}")
    fun getMessage(@PathVariable id: String): ResponseEntity<Message> =
            service.findMessage(id).toResponseEntity()

    @PostMapping
    fun post(@RequestBody message: Message): ResponseEntity<Message> {
        logger.info("Received message: $message")
        val saved = service.save(message)
        return ResponseEntity.created(URI("/${saved.id}")).body(saved)
    }

    private fun Message?.toResponseEntity() =
            this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}