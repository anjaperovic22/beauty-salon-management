/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidencijaTermina;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.EvidencijaTermina;
import model.Klijent;
import model.Kozmeticar;
import model.Radnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Admin
 */
public class PretragaEvidencijaTerminaSO extends ApstraktnaGenerickaOperacija {
    private List<EvidencijaTermina> listaET=new ArrayList<>();
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) {
        try {
            String uslov = " JOIN kozmeticar kozmeticar ON evidencijaTermina.kozmeticar = kozmeticar.idKozmeticar JOIN klijent klijent ON evidencijaTermina.klijent=klijent.idKlijent JOIN radnik radnik ON evidencijaTermina.radnik=radnik.idRadnik ";
            
            EvidencijaTermina ev = (EvidencijaTermina) param;

            Klijent klijent = ev.getKlijent();
            Kozmeticar kozmeticar = ev.getKozmeticar();
            Radnik radnik = ev.getRadnik();
            Date datum = ev.getDatumTermina();

            boolean dodajWhere = false;

            if (radnik != null) {
                if (!dodajWhere) {
                    uslov += " WHERE";
                    dodajWhere = true;
                } else {
                    uslov += " AND";
                }
                uslov += " radnik = " + radnik.getIdRadnik();
            }

            if (klijent != null) {
                if (!dodajWhere) {
                    uslov += " WHERE";
                    dodajWhere = true;
                } else {
                    uslov += " AND";
                }
                uslov += " klijent = " + klijent.getIdKlijent();
            }

            if (kozmeticar != null) {
                if (!dodajWhere) {
                    uslov += " WHERE";
                    dodajWhere = true;
                } else {
                    uslov += " AND";
                }
                uslov += " kozmeticar = " + kozmeticar.getIdKozmeticar();
            }

            if (datum != null) {
                if (!dodajWhere) {
                    uslov += " WHERE";
                    dodajWhere = true;
                } else {
                    uslov += " AND";
                }
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sqlDateString = sdf.format(datum);
                uslov += " datumTermina > '" + sqlDateString + "'"; 
            }
            System.out.println(uslov);
            listaET = broker.ucitajSve(new EvidencijaTermina(), uslov);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<EvidencijaTermina> getListaET() {
        return listaET;
    }
    
    
    
}
