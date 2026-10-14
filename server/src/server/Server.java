/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Admin
 */
public class Server extends Thread{
    
    private boolean kraj=false;
    private ServerSocket serverskiSoket;
    private List<ObradaKlijentskihZahteva> klijenti=new ArrayList<>();

    @Override
    public void run() {
       try {
            serverskiSoket=new ServerSocket(9000);
            System.out.println("Soket otvoren, ceka se klijent");
            try {
               while (!kraj) {                
                Socket s=serverskiSoket.accept();
                System.out.println("Klijent povezan");
                ObradaKlijentskihZahteva nit=new ObradaKlijentskihZahteva(s);
                klijenti.add(nit);
                nit.start();
            }
           } catch (SocketException e) {
           }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
  
    public void zaustaviServer(){
        kraj=true;
        try {
            for (int i = 0; i < klijenti.size(); i++) {
                klijenti.get(i).zaustaviKlijenta();
            }
            serverskiSoket.close();
            this.interrupt();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
