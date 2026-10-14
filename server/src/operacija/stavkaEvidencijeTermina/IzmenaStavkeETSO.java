/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavkaEvidencijeTermina;

import model.StavkaEvidencijeTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class IzmenaStavkeETSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object param) throws Exception {
        String porukaGreske = "Sistem ne može da izmeni stavku evidencije.";
        if(param==null || !(param instanceof StavkaEvidencijeTermina)){
            throw new Exception(porukaGreske);
        }
        StavkaEvidencijeTermina s = (StavkaEvidencijeTermina)param;
        if(s.getNazivStavkeET()==null||s.getEvidencijaTermina()==null||s.getCena()<0 ||s.getTrajanjeMinuti()<0){
            throw new Exception(porukaGreske);
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
                StavkaEvidencijeTermina s = (StavkaEvidencijeTermina)param;

        System.out.println("RB u objektu koji šaljem: " + s.getRb());
        broker.izmeni((StavkaEvidencijeTermina)param);
    }
}
