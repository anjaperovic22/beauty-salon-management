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
public class BrisanjeEvidencijeTerminaSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof EvidencijaTermina)){
            throw new Exception("Sistem ne može da obriše evidenciju termina.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        EvidencijaTermina et = (EvidencijaTermina) param;
        
        List<StavkaEvidencijeTermina> stavke = et.getListaStavkiET();
        
        for(StavkaEvidencijeTermina s: stavke){
            broker.obrisi(s);
        }
        
        broker.obrisi((EvidencijaTermina)param);
    }
}
