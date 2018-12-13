/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernandopaniagua.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author fernando.paniagua
 */
public class ServerSocketJ23 extends Thread{
    boolean running=true;
    JTextArea jta;
    
    public ServerSocketJ23(JTextArea _jta){
        this.jta = _jta;
    }
    
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress iSA = new InetSocketAddress("10.10.1.50",5555);
            serverSocket.bind(iSA);
            System.out.println("Esperando peticiones " + 
                    iSA.getAddress() + 
                    ":" + 
                    iSA.getPort());
            Socket socket;
            while(running){
                socket = serverSocket.accept();
                System.out.println("Conexión establecida por " + socket.getInetAddress());
                //PROCESADO 
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String linea;
                while((linea = br.readLine())!=null){
                    jta.append(linea);
                    jta.append("\n");
                }
                br.close();
                is.close();
                //FIN DE PROCESADO
            }
            serverSocket.close();
            System.out.println("Conexión cerrada");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
}
