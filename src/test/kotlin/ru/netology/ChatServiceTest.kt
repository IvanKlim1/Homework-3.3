package ru.netology

import org.junit.Test

class ChatServiceTest {

    @Test
    fun addMessage() {
    val service =ChatService()
        val message=(Message(1,1,"re",11,11,2,true))
        service.addMessage(1,true,message)
    }

    @Test
    fun deleteMessage() {
        val service =ChatService()
        val note=(Message(2,2,"re",11,11,2,false))
        service.addMessage(2,false,note)
        val message=(Message(1,1,"re",11,11,2,false))
        service.deleteMessage(1,message)
    }
    @Test
    fun addChat() {
        val service =ChatService()
        val notes=(Message(0,2,"re",11,11,2,false))
        val chat=(Chat(1,1,1,1,notes,1,1,1))
        service.addChat(1,chat)
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