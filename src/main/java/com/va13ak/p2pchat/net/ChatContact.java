/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.va13ak.p2pchat.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Valery
 */
public class ChatContact {
    private String address;
    private List history;

    public ChatContact(String address) {
        this.address = address;
        this.history = new ArrayList<>();
    }

    public List getHistory() {
        return history;
    }

    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return "" + address;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChatContact other = (ChatContact) obj;
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
    
}
