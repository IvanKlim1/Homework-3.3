package ru.netology

data class Chat (
    val chatId: Int,
    val userId: Int,
    val date: Int,
    val count: Int,
    val message:Message,
    val readMessage: Int,
    val unReadMessage: Int,
    val countUnReadMessage: Int,
)