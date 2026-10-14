/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavkaEvidencijeTermina;

import java.util.ArrayList;
import java.util.List;
import model.StavkaEvidencijeTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajStavkeZaEvidencijuSO extends ApstraktnaGenerickaOperacija {
    List<StavkaEvidencijeTermina> stavke = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String upit = " JOIN evidencijatermina evidencijatermina ON stavkaevidencijetermina.evidencijatermina = evidencijatermina.idevidencijatermina WHERE stavkaevidencijetermina.evidencijaTermina="+(int)param;
        try{
            stavke = broker.ucitajSve(new StavkaEvidencijeTermina(), upit);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<StavkaEvidencijeTermina> getStavke() {
        return stavke;
    }
}
