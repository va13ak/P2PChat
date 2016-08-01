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
public interface MessageListener {
    
    void messageIncome(ChatMessage message);
    
}
