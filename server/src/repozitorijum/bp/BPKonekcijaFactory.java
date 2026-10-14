/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.bp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BPKonekcijaFactory {
    //konekcija sa bp
    
    private static BPKonekcijaFactory instance;
    private Connection connection;
    private BPKonekcijaFactory(){
        
        try {
            if(connection==null || connection.isClosed()){
                String url = konfiguracija.Konfiguracija.getInstance().getProperty("url");
                String korisnickoIme = konfiguracija.Konfiguracija.getInstance().getProperty("username");
                String lozinka = konfiguracija.Konfiguracija.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url,korisnickoIme,lozinka);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BPKonekcijaFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static BPKonekcijaFactory getInstance(){
        if(instance==null)instance=new BPKonekcijaFactory();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
}
