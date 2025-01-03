package com.example.kotlinspring

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("MESSAGES")
data class Message(val text: String, @Id var id: String? = null) {
    fun send() {
        println("Sending message: $text")
    }
}