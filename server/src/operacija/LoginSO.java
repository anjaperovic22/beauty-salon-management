/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Radnik;

/**
 *
 * @author Admin
 */
public class LoginSO extends ApstraktnaGenerickaOperacija {
    
    Radnik radnik = null;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        //dovrsi
    }
    
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        try {
            List<Radnik> sviRadnici = broker.ucitajSve((Radnik)param, null);
            System.out.println("Radnici: "+sviRadnici);
            for(Radnik r: sviRadnici){
                if(((Radnik)param).getKorisnickoIme().equals(r.getKorisnickoIme())&&((Radnik)param).getSifra().equals(r.getSifra())){
                    radnik = r;
                    return;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Radnik getRadnik() {
        return radnik;
    }
    
}
