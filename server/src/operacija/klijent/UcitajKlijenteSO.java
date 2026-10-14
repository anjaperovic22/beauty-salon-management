/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijent;

import java.util.List;
import model.Klijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajKlijenteSO extends ApstraktnaGenerickaOperacija{

    List<Klijent> klijenti;
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        klijenti = broker.ucitajSve(new Klijent(), "");
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }
    
    
    
}
