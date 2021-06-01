package ru.netology


class ChatService {
    var chats = emptyArray<Chat>()
    private var inComingMessages = emptyArray<Message>()
    private var outGoingMessages = emptyArray<Message>()

    fun addMessage(userId: Int,incomingOrOutgoingMessage:Boolean, message: Message): Message {
        if (incomingOrOutgoingMessage == true) {
            inComingMessages += message.copy(
                messageId = if (inComingMessages.isEmpty()) 1
                else inComingMessages.last().messageId + 1
            )
            return inComingMessages.last()
        } else {
            outGoingMessages += message.copy(
                messageId = if (outGoingMessages.isEmpty()) 1
                else outGoingMessages.last().messageId + 1
            )
            return outGoingMessages.last()
        }
    }

    fun deleteMessage(userId: Int, message: Message): List<Message> {
        for ((index, deleteMessage) in outGoingMessages.withIndex()) {
            if (chats[index].message == deleteMessage) {
                outGoingMessages = outGoingMessages.filterNot { it.messageId == message.messageId }
                    .toTypedArray()
                return outGoingMessages.toList()
            }
        }
        throw MessageNotFoundException("error")
    }

    fun createMessage(userId: Int, message: Message) {
        if (chats.none { it.message == message }) {
            throw MessageNotFoundException("no note with id")
        }
        outGoingMessages + message.copy(messageId = outGoingMessages.lastOrNull()?.messageId ?: 0)
    }

    fun addChat(userId: Int, chat: Chat): Chat {
        if (chat.message.messageId == 0) {
            chats += chat
        }
        return chats.last()
    }

    fun deleteChat(userId: Int, chat: Chat): List<Chat> {
        for ((index, deleteChat) in chats.withIndex()) {
            if (chats[index].chatId == deleteChat.chatId) {
                chats = chats.filterNot { it.chatId == chat.chatId }
                    .toTypedArray()
                return chats.toList()
            }
        }
        throw MessageNotFoundException("error")
    }

    fun getUnreadChatsCount(userId: Int,incomingOrOutgoingMessage:Boolean, countUnReadMessage: Int, chat: Chat): Int {
        if (incomingOrOutgoingMessage==true && chat.unReadMessage in chats > 0) {
            countUnReadMessage + 1
        }
        return countUnReadMessage
    }


    fun getChats(userId: Int): List<Chat> {
        return chats.filter { it.unReadMessage > 0 }
    }

    fun getMessages(chatId: Int, count: Int) {
        inComingMessages
        outGoingMessages
    }


}