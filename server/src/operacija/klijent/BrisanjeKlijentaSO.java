/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.klijent;

import model.Klijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class BrisanjeKlijentaSO extends ApstraktnaGenerickaOperacija{
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Klijent)){
            throw new Exception("Sistem ne može da obriše klijenta.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        broker.obrisi((Klijent)param);
    }
    
}
