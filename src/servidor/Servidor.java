/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author carlo
 */
public class Servidor extends Thread{
    
    protected ServerSocket serverSocket;
    protected Socket socketIN;
    protected Socket socketOUT;
    protected Paquete mensaje;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;
    protected final int PUERTO;
    
    public Servidor(int puerto)throws Exception{
        this.PUERTO = puerto;
        serverSocket = new ServerSocket(PUERTO);
    }
    
    public void run(){
        try {
            while(true){
                socketIN = serverSocket.accept();
                in = new ObjectInputStream(socketIN.getInputStream());
                mensaje = (Paquete)in.readObject();
                System.out.println("Imprimer aqui el objeto para");
                socketOUT = new Socket(mensaje.ip,50500);
                out = new ObjectOutputStream(socketOUT.getOutputStream());
                out.writeObject(in);
                socketOUT.close();
                socketIN.close();
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
}
