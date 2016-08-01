/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.va13ak.p2pchat.net;

/**
 *
 * @author Valery
 */
public class ChatMessage {
    private String address;
    private String message;

    public ChatMessage(String address, String message) {
        this.address = address;
        this.message = message;
    }

    public ChatMessage(ChatContact contact, String message) {
        this.address = contact.getAddress();
        this.message = message;
    }
    
    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "" + address + ": " + message;
    }
    
    
}
