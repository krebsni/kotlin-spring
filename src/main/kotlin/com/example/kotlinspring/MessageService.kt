package com.example.kotlinspring

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import java.util.UUID

@Service
class MessageService(private val db: JdbcTemplate) {
    fun findMessages(): List<Message> = db.query("SELECT * FROM messages") { rs, _ ->
        Message(rs.getString("id"), rs.getString("text"))
    }

    fun save(message: Message): Message {
        val id = message.id ?: UUID.randomUUID().toString()
        db.update("INSERT INTO messages (id, text) VALUES (?, ?)", id, message.text)
        return message.copy(id = id)
    }

    fun findMessage(id: String): Message? = db.query("SELECT * FROM messages WHERE id = ?", id) { rs, _ ->
        Message(rs.getString("id"), rs.getString("text"))
    }.singleOrNull()
}