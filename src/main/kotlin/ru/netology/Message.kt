package ru.netology

data class Message(
    val messageId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val sizes: Int,
    val count:Int,
    val incomingOrOutgoingMessage:Boolean // если true то сообщение входящее
)