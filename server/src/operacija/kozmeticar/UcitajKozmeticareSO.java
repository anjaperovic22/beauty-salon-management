/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kozmeticar;

import java.util.ArrayList;
import java.util.List;
import model.Kozmeticar;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajKozmeticareSO extends ApstraktnaGenerickaOperacija {

    List<Kozmeticar> kozmeticari = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov = " JOIN kategorija ON kozmeticar.kategorija = kategorija.idKategorija";
        try{
            kozmeticari = broker.ucitajSve(new Kozmeticar(), uslov);
        }catch(Exception e){
            System.out.println("UcitajKozmeticareSO: nije ucitao kozmeticare");
            e.printStackTrace();
        }
    }

    public List<Kozmeticar> getKozmeticari() {
        return kozmeticari;
    }
}
