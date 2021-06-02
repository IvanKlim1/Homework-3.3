package ru.netology

import junit.framework.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ChatServiceTest {

    @Test
    fun addMessage() {
    val service =ChatService()
        //val message=(Message(1,1,"re",11,11,2,true))
        val message=service.addMessage(1,true,(Message(1,1,"re",
            11,11,2,true)))
        val messageTwo=(Message(1,1,"re",
            11,11,2,true))

        assertEquals(message,messageTwo)
        // сравниваю изменился ли id ,если да, то тест прошел
    }

    @Test
    fun deleteMessage() {
        val service =ChatService()
        val note=(Message(1,1,"re",11,11,2,false))
        service.addMessage(1,false,note)
        val chat=(Chat(1,1,1,1,note,1,1,1))
        service.addChat(1,chat)
        val message=(Message(1,1,"re",11,11,2,false))
        service.deleteMessage(1,message)
    }
    @Test
    fun addChat() {
        val service =ChatService()
        val notes=service.addMessage(1,false,Message(0,1,
            "re",11,11,2,false))
        val chat=service.addChat(1,Chat(1,1,1,1,notes,1,1,1))
        assertTrue(service.chats.isNotEmpty())
    }
    @Test
    fun deleteChat() {
        val service =ChatService()
        val message=(Message(0,2,"re",11,11,2,false))
        val chats=service.addChat(1,Chat(1,1,1,1,message,1,1,1))
        val notes=(Message(0,2,"re",11,11,2,false))
        val chat=service.addChat(1,Chat(1,1,1,1,notes,1,1,1))
        service.deleteChat(1,chat)
    }
}