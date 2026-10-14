/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kategorija;

import java.util.ArrayList;
import java.util.List;
import model.Kategorija;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajKategorijeSO extends ApstraktnaGenerickaOperacija {
    List<Kategorija> kategorije = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        try{
            kategorije = broker.ucitajSve(new Kategorija(), "");
        }catch(Exception e){
            System.out.println("Ucitaj kategorije SO: nije ucitao kozmeticare");
            e.printStackTrace();
        }
    }

    public List<Kategorija> getKategorije() {
        return kategorije;
    }
}
