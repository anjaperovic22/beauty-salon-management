/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smena;

import java.util.ArrayList;
import java.util.List;
import model.Smena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajSmeneSO extends ApstraktnaGenerickaOperacija {
    List<Smena> smene = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        try{
            smene = broker.ucitajSve(new Smena(), "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Smena> getSmene() {
        return smene;
    }
}
