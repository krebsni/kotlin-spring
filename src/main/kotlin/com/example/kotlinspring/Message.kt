package com.example.kotlinspring

data class Message(val id: String?, val text: String) {
    fun send() {
        println("Sending message: $text")
    }
}