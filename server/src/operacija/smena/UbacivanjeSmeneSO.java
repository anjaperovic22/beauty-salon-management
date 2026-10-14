/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smena;

import model.Smena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UbacivanjeSmeneSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da zapamti smenu.";
        if(param==null || !(param instanceof Smena)){
            throw new Exception(porukaGreske);
        }
        Smena s = (Smena)param;
        if(s.getNazivSmena().equals("") || s.getBrojSati()<=0 || s.getSatnica()<=0){
            throw new Exception(porukaGreske);
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        broker.dodaj((Smena)param);
    }
}
