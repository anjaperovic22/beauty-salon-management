/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Smena implements ApstraktniDomenskiObjekat {
    private int idSmena;
    private String nazivSmena;
    private int brojSati;
    private int satnica;

    public Smena() {
    }

    public Smena(int idSmena, String nazivSmena, int brojSati, int satnica) {
        this.idSmena = idSmena;
        this.nazivSmena = nazivSmena;
        this.brojSati = brojSati;
        this.satnica = satnica;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public String getNazivSmena() {
        return nazivSmena;
    }

    public void setNazivSmena(String nazivSmena) {
        this.nazivSmena = nazivSmena;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public int getSatnica() {
        return satnica;
    }

    public void setSatnica(int satnica) {
        this.satnica = satnica;
    }

    @Override
    public String toString() {
        return "Smena{" + "idSmena=" + idSmena + ", nazivSmena=" + nazivSmena + ", brojSati=" + brojSati + ", satnica=" + satnica + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Smena other = (Smena) obj;
        return this.idSmena == other.idSmena;
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idSmena1 = rs.getInt("smena.idSmena");
            String nazivSmena1 = rs.getString("smena.nazivSmena");
            int brojSati1 = rs.getInt("smena.brojSati");
            int satnica1 = rs.getInt("smena.satnica");
            Smena smena1 = new Smena(idSmena1,nazivSmena1,brojSati1,satnica1);
            lista.add(smena1);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSmena,brojSati,satnica";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivSmena+"',"+brojSati+","+satnica;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "smena.idSmena="+idSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivSmena='"+nazivSmena+"', brojSati="+brojSati+", satnica="+satnica;
                
    }
    
    
}
