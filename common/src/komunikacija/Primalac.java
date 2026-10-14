/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Primalac {

    private Socket s;

    public Primalac() {
    }

    public Primalac(Socket s) {
        this.s = s;
    }

    public Object primi() {
        try {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(s.getInputStream());
            } catch (SocketException e) {
                // ignorisano
            }

            try {
                if (ois == null) return null;
                return ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Primalac.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EOFException e) {
                // ignorisano
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
