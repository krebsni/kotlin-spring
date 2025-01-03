package com.example.kotlinspring

import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>