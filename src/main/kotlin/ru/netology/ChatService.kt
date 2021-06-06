package ru.netology


class ChatService {
    private var chats = emptyArray<Chat>()
    private var inComingMessages = emptyArray<Message>()
    private var outGoingMessages = emptyArray<Message>()

    fun Array<Message>.getCount(): Int =
        if (isEmpty()) 1 else last().count + 1

    fun Array<Message>.getLastId(): Int =
        if (isEmpty()) 1 else last().messageId + 1

    private fun copyMessage(target: Array<Message>, message: Message): Message =
        message.copy(
            messageId = target.getLastId(),
            count = target.getCount()
        )

    fun addMessage(userId: Int, incomingOrOutgoingMessage: Boolean, message: Message): Message =
        if (incomingOrOutgoingMessage) {
            inComingMessages += copyMessage(inComingMessages, message)
            inComingMessages.last()
        } else {
            outGoingMessages += copyMessage(outGoingMessages, message)
            outGoingMessages.last()
        }

    fun deleteMessage(userId: Int, message: Message): List<Message> =
        outGoingMessages.filterNot { it.messageId == message.messageId }
            .also {
                // Если размеры одинаковы, значит не нашли сообщение в списке
                if (outGoingMessages.size == it.size) {
                    throw MessageNotFoundException("error")
                }
                outGoingMessages = it.toTypedArray()
            }

        //    fun deleteMessage(userId: Int, message: Message): List<Message> {
      //  for ((index, deleteMessage) in outGoingMessages.withIndex()) {
       //     if (chats[index].message == deleteMessage) {
       //         outGoingMessages = outGoingMessages.filterNot { it.messageId == message.messageId }
       //             .toTypedArray()
       //         return outGoingMessages.toList()
       //     }
       // }
     //   throw MessageNotFoundException("error")
   // }

    fun createMessage(userId: Int, message: Message) {
        if (chats.none { it.message == message }) {
            throw MessageNotFoundException("no note with id")
        }
        outGoingMessages + message.copy(messageId = outGoingMessages.lastOrNull()?.messageId ?: 0)
    }

    fun Array<Chat>.getChatId(): Int =
        if (isEmpty()) 1 else last().chatId + 1

    private fun copyChat(target: Array<Chat>, chat: Chat): Chat =
        chat.copy(
            chatId = target.getChatId(),
        )

    fun addChat(userId: Int, chat: Chat): Chat {
        if (chat.message.messageId == 1) {
            chats += copyChat(chats, chat)

        }
        return chats.last()
    }
    fun deleteChat(userId: Int, chat: Chat): List<Chat> =
        chats.filterNot { it.chatId == chat.chatId }
            .also {
                // Если размеры одинаковы, значит не нашли чат в списке
                if (chats.size == it.size) {
                    throw MessageNotFoundException("error")
                }
                chats = it.toTypedArray()
            }

  //  fun deleteChat(userId: Int, chat: Chat): List<Chat> {
   //     for ((index, deleteChat) in chats.withIndex()) {
   //         if (chats[index].chatId == deleteChat.chatId) {
    //            chats = chats.filterNot { it.chatId == chat.chatId }
   //                 .toTypedArray()
    //            return chats.toList()
   //         }
   //     }
 //       throw MessageNotFoundException("error")
  //  }


    fun getUnreadChatsCount(userId: Int, incomingOrOutgoingMessage: Boolean, countUnReadMessage: Int, chat: Chat): Int {
        if (incomingOrOutgoingMessage && chat.unReadMessage > 0) {
            countUnReadMessage + 1
        }
        return countUnReadMessage
    }


    fun getChats(userId: Int): List<Chat> {
        return chats.filter { it.unReadMessage > 0 }
    }

    fun getMessagesInComingMessages(chatId: Int): List<Message> {
        return inComingMessages.filter { it.count > 0 }

    }

    fun getMessagesOutGoingMessages(chatId: Int): List<Message> {
        return outGoingMessages.filter { it.count > 0 }
    }


}