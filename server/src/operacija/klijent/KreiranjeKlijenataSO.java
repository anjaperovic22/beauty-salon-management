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
public class KreiranjeKlijenataSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da kreira klijenta.";
        if(param==null || !(param instanceof Klijent)){
            throw new Exception(porukaGreske);
        }
        Klijent k = (Klijent)param;
        if(k.getIme().equals("") || k.getPrezime().equals("") || k.getBrojTelefona().equals("")){
            throw new Exception(porukaGreske);
        }else if(k.getBrojTelefona().length()<12 || k.getBrojTelefona().length()>14){
            throw new Exception(porukaGreske);
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        broker.dodaj((Klijent)param);
    }
    
}
