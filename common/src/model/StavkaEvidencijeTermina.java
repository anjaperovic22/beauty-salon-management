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
public class StavkaEvidencijeTermina implements ApstraktniDomenskiObjekat {
    private int rb;
    private EvidencijaTermina evidencijaTermina;
    private String nazivStavkeET;
    private int trajanjeMinuti;
    private int cena;
    
    public StavkaEvidencijeTermina() {
    }

    public StavkaEvidencijeTermina(int rb, EvidencijaTermina evidencijaTermina, String nazivStavkeET, int trajanjeMinuti, int cena) {
        this.rb = rb;
        this.evidencijaTermina = evidencijaTermina;
        this.nazivStavkeET = nazivStavkeET;
        this.trajanjeMinuti = trajanjeMinuti;
        this.cena = cena;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public EvidencijaTermina getEvidencijaTermina() {
        return evidencijaTermina;
    }

    public void setEvidencijaTermina(EvidencijaTermina evidencijaTermina) {
        this.evidencijaTermina = evidencijaTermina;
    }

    public String getNazivStavkeET() {
        return nazivStavkeET;
    }

    public void setNazivStavkeET(String nazivStavkeET) {
        this.nazivStavkeET = nazivStavkeET;
    }

    public int getTrajanjeMinuti() {
        return trajanjeMinuti;
    }

    public void setTrajanjeMinuti(int trajanjeMinuti) {
        this.trajanjeMinuti = trajanjeMinuti;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final StavkaEvidencijeTermina other = (StavkaEvidencijeTermina) obj;
        return this.rb == other.rb;
    }
    
    public String toString() {
    // StavkaEvidencijeTermina
    return "StavkaEvidencijeTermina{" +
           "rb=" + rb +
           ", evidencijaId=" + (evidencijaTermina != null ? evidencijaTermina.getIdEvidencijaTermina() : "null") +
           ", nazivStavkeET=" + nazivStavkeET +
           ", trajanjeMinuti=" + trajanjeMinuti +
           ", cena=" + cena +
           '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaevidencijetermina";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            
            StavkaEvidencijeTermina s = new StavkaEvidencijeTermina();
            s.setRb(rs.getInt("stavkaevidencijetermina.rb"));
            s.setCena(rs.getInt("stavkaevidencijetermina.cena"));
            s.setTrajanjeMinuti(rs.getInt("stavkaevidencijetermina.trajanjeMinuti"));
            s.setNazivStavkeET(rs.getString("stavkaevidencijetermina.nazivStavkeET"));
            
            EvidencijaTermina et = new EvidencijaTermina();
            et.setIdEvidencijaTermina(rs.getInt("evidencijatermina.idEvidencijaTermina"));
            java.sql.Date datumSql = rs.getDate("evidencijatermina.datumTermina");
            java.util.Date datum;
            if (datumSql == null) {
                datum = null;
            } else {
                datum = new java.util.Date(datumSql.getTime());
            }
            et.setDatumTermina(datum);
            et.setUkupnaCena(rs.getInt("evidencijatermina.ukupnaCena"));
            
            s.setEvidencijaTermina(et);
            
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb,evidencijaTermina,nazivStavkeET,trajanjeMinuti,cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb + "," + evidencijaTermina.getIdEvidencijaTermina() + ",'" + nazivStavkeET + "'," + trajanjeMinuti + "," + cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rb="+rb+" AND evidencijatermina="+evidencijaTermina.getIdEvidencijaTermina();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "cena="+cena+", nazivStavkeET='"+nazivStavkeET+"', trajanjeMinuti="+trajanjeMinuti;
    }
    
    
    
}
