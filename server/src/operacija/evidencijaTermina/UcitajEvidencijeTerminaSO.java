/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaTermina;

import java.util.ArrayList;
import java.util.List;
import model.EvidencijaTermina;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class UcitajEvidencijeTerminaSO extends ApstraktnaGenerickaOperacija {
    List<EvidencijaTermina> evidencijeTermina = new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {}

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        String uslov = " JOIN kozmeticar kozmeticar ON evidencijaTermina.kozmeticar = kozmeticar.idKozmeticar JOIN klijent klijent ON evidencijaTermina.klijent=klijent.idKlijent JOIN radnik radnik ON evidencijaTermina.radnik=radnik.idRadnik";
        try{
            evidencijeTermina = broker.ucitajSve(new EvidencijaTermina(), uslov);
        }catch(Exception e){
            System.out.println("UcitajEvidencijeTerminaSO: nije ucitao evidencije");
            e.printStackTrace();
        }
    }

    public List<EvidencijaTermina> getEvidencijeTermina() {
        return evidencijeTermina;
    }
}
