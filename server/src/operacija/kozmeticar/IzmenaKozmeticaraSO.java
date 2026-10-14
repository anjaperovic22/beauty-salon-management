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
public class IzmenaKozmeticaraSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da izmeni kozmetičara.";
        if(param==null || !(param instanceof Kozmeticar)){
            throw new Exception(porukaGreske);
        }
        Kozmeticar k = (Kozmeticar)param;
        if(k.getIme()==null||k.getPrezime()==null||k.getKategorija()==null){
            throw new Exception(porukaGreske);
        }
        if(k.getIme().equals("") || k.getPrezime().equals("")){
            throw new Exception(porukaGreske);
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        broker.izmeni((Kozmeticar)param);
    }
}
