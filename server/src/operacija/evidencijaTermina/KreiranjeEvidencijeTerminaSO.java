/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaTermina;

import java.util.List;
import model.EvidencijaTermina;
import model.StavkaEvidencijeTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class KreiranjeEvidencijeTerminaSO extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da zapamti evidenciju termina.";
        if(param==null || !(param instanceof EvidencijaTermina)){
            throw new Exception(porukaGreske);
        }
        EvidencijaTermina et = (EvidencijaTermina)param;
        if (et.getDatumTermina()== null || et.getKlijent()==null || et.getKozmeticar()==null || et.getKozmeticar().getIme().equals("Ivanka"))  {
            throw new Exception(porukaGreske);
        } 
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        EvidencijaTermina ev = (EvidencijaTermina) param;

        // Insert i dobijanje ID-a
        int id = broker.vratiKljuc(ev);
        ev.setIdEvidencijaTermina(id);

        // Ubacivanje stavki
        for (StavkaEvidencijeTermina s : ev.getListaStavkiET()) {
            s.setEvidencijaTermina(ev);
            System.out.println("Stavka "+s);
            broker.dodaj(s);
        }
    }

}
