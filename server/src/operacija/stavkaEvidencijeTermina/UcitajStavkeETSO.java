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
public class UcitajStavkeETSO extends ApstraktnaGenerickaOperacija {
    List<StavkaEvidencijeTermina> stavke = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        try{
            stavke = broker.ucitajSve(new StavkaEvidencijeTermina(), "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<StavkaEvidencijeTermina> getStavke() {
        return stavke;
    }
}
