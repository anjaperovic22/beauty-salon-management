/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kozmeticar;

import model.Kozmeticar;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class KreiranjeKozmeticaraSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da zapamti kozmetičara.";
        if(param==null || !(param instanceof Kozmeticar)){
            throw new Exception(porukaGreske);
        }
        Kozmeticar k = (Kozmeticar)param;
        if (k.getIme() == null || k.getIme().isEmpty()) {
            throw new Exception(porukaGreske);
        }
        for (int i = 0; i < k.getIme().length(); i++) {
            char c = k.getIme().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception(porukaGreske);
            }
        }
        if (k.getPrezime() == null || k.getPrezime().isEmpty()) {
            throw new Exception(porukaGreske);
        }
        for (int i = 0; i < k.getPrezime().length(); i++) {
            char c = k.getPrezime().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception(porukaGreske);
            }
        }
        if(k.getKategorija()==null){
            throw new Exception(porukaGreske+" kategorija");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        broker.dodaj((Kozmeticar)param);
    }
}