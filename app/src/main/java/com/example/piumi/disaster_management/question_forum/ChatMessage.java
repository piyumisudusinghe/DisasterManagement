package com.example.piumi.disaster_management.question_forum;

import java.util.Date;

/**
 * Created by Piumi on 2/20/2018.
 * This is java class used to get the detaills of the chats
 */

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser){
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new Date().getTime();
    }

    public  ChatMessage(){

    }

    public String getMessageText(){
        return messageText;
    }

    public void setMessageText(String messageText){
        this.messageText = messageText;
    }

    public void setMessageUser(String messageUser){
        this.messageUser = messageUser;
    }

    public String getMessageUser(){
        return messageUser;
    }

    public void setMessageTime(){
        this.messageTime = messageTime;
    }

    public long getMessageTime(){
        return messageTime;
    }
}
