/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.radnik;

import java.util.ArrayList;
import java.util.List;
import model.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajRadnikeSO extends ApstraktnaGenerickaOperacija {
    List<Radnik> radnici = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        try{
            radnici = broker.ucitajSve(new Radnik(), "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Radnik> getRadnici() {
        return radnici;
    }
}
