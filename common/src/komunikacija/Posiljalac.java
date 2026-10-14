/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.SocketException;
/**
 *
 * @author Admin
 */

public class Posiljalac {
    
    private Socket s;

    public Posiljalac() {
    }

    public Posiljalac(Socket s) {
        this.s = s;
    }
    
    public void posalji(Object obj){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(obj);
            oos.flush();
        }catch (SocketException e) {
            //upadalo u besk petlju ovde
        } catch (IOException e) {
            System.out.println("Greška pri slanju podataka: " + e.getMessage());
        }
    }
    
}