package ru.netology

import junit.framework.Assert.assertTrue
import org.junit.Test

class ChatServiceTest {

    @Test
    fun addMessage() {
    val service =ChatService()
        val message=service.addMessage(1,false,(Message(1,1,"re",
            11,11,0,false)))
        val chat=service.addChat(1,Chat(1,1,1,1,
            message,1,1,1))
        assertTrue(service.getMessagesOutGoingMessages(1).isNotEmpty())
    }

    @Test
    fun deleteMessage() {
        val service =ChatService()
        val note=service.addMessage(1,false,(Message(1,1,"re",
            11,11,0,false)))
        val chat=(Chat(1,1,1,1,note,1,1,1))
        service.addChat(1,chat)
        val message=(Message(1,1,"re",11,11,1,false))
        service.deleteMessage(1,message)
        assertTrue(service.getMessagesOutGoingMessages(1).isEmpty())
    }
    @Test
    fun addChat() {
        val service =ChatService()
        val notes=service.addMessage(1,false,Message(0,1,
            "re",11,11,2,false))
        val chat=service.addChat(1,Chat(1,1,1,1,notes,
            1,1,1))
        assertTrue(service.getChats(1).isNotEmpty())
    }
    @Test
    fun deleteChat() {
        val service =ChatService()
        val message=(Message(1,1,"re",11,11,2,false))
        val chats=service.addChat(1,Chat(1,1,1,1,
            message,1,1,1))
        val chat=(Chat(1,1,1,1,
            (Message(1,1,"re",11,11,2,false)),
            1,1,1))
        service.deleteChat(1,chat)
        assertTrue(service.getChats(1).isEmpty())
    }
}