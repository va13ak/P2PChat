package com.va13ak.p2pchat;

import com.va13ak.p2pchat.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Valery
 */
public class ChatFrame extends javax.swing.JFrame implements MessageListener {

    private MessageReceiver receiver;
    private final int port = 9060;
    private final DefaultListModel<ChatMessage> dlmHistory;
    private final DefaultListModel<ChatContact> dlmContacts;

    /**
     * Creates new form ChatFrame
     */
    public ChatFrame() {
        initComponents();

        jlContacts.setModel(dlmContacts = new DefaultListModel<>());
        jlHistory.setModel(dlmHistory = new DefaultListModel<>());
        
        if (dlmContacts.size() == 0) {
            dlmContacts.addElement(new ChatContact("127.0.0.1"));
        }
        
        jlContacts.setSelectedIndex(0);

        initReceiver();
    }

    private void initReceiver() {
        try {
            this.receiver = new MessageReceiver(port);
            this.receiver.add(this);
            this.receiver.start();

        } catch (IOException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlContacts = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaMessage = new javax.swing.JTextArea();
        jbSend = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlHistory = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSplitPane1.setDividerLocation(170);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setOneTouchExpandable(true);

        jlContacts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlContacts.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlContactsFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jlContacts);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jtaMessage.setColumns(20);
        jtaMessage.setRows(5);
        jScrollPane3.setViewportView(jtaMessage);

        jbSend.setText("SEND");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(jlHistory);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbSend))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSend))
        );

        jSplitPane1.setRightComponent(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed
        int index = jlContacts.getSelectedIndex();
        if (index < 0) return;
        
        String address = dlmContacts.get(index).getAddress();
        ChatMessage message = new ChatMessage(address, jtaMessage.getText());
        if (sendMessage(message)) {
            jtaMessage.setText("");
            addMessageToHistory(message);
        };
    }//GEN-LAST:event_jbSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jlContactsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlContactsFocusGained
        if (evt.getSource() == jlContacts) {
                int index = jlContacts.getSelectedIndex();
                if (index >= 0) System.out.println(dlmContacts.get(index).getAddress());
        }
        
    }//GEN-LAST:event_jlContactsFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbSend;
    private javax.swing.JList<ChatContact> jlContacts;
    private javax.swing.JList<ChatMessage> jlHistory;
    private javax.swing.JTextArea jtaMessage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageIncome(ChatMessage message) {
        System.out.println("RECEIVE: " + message.getAddress() + ": " + message.getMessage());
        onMessageIncome(message);
    }
    
    public void onMessageIncome(ChatMessage message) {
        ChatContact contact = new ChatContact(message.getAddress());
        if (!dlmContacts.contains(contact)) {
            dlmContacts.addElement(contact);
        }
        addMessageToHistory(message);
    }
    
    public synchronized void addMessageToHistory(ChatMessage message) {
        dlmHistory.addElement(message);
        jlHistory.ensureIndexIsVisible(dlmHistory.getSize()-1);
    }
    
    public boolean sendMessage(ChatMessage message) {
        try (Socket client = new Socket(message.getAddress(), port)) {
            System.out.println("CONNECTED!");
            
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());
            
            dos.writeUTF(message.getMessage());
            dos.flush();
            
            String response = dis.readUTF();
            
            System.out.println(response);
            
            return "ok".equalsIgnoreCase(response);
            
        } catch (IOException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
