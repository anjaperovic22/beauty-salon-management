/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaTermina;

import model.EvidencijaTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class IzmenaEvidencijeTerminaSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da izmeni kozmetičara.";
        if(param==null || !(param instanceof EvidencijaTermina)){
            throw new Exception(porukaGreske);
        }
        EvidencijaTermina et = (EvidencijaTermina)param;
        if(et.getDatumTermina()==null || et.getKlijent()==null||et.getKozmeticar()==null||et.getListaStavkiET()==null||et.getListaStavkiET().isEmpty()){
            throw new Exception(porukaGreske);
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        broker.izmeni((EvidencijaTermina)param);
    }
}
