/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class RS implements ApstraktniDomenskiObjekat {
    private Date datumRS;
    private Radnik radnik;
    private Smena smena;

    public RS() {
    }

    public RS(Date datumRS, Radnik radnik, Smena smena) {
        this.datumRS = datumRS;
        this.radnik = radnik;
        this.smena = smena;
    }

    public Date getDatumRS() {
        return datumRS;
    }

    public void setDatumRS(Date datumRS) {
        this.datumRS = datumRS;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    @Override
    public String toString() {
        return "RS{" + "datumRS=" + datumRS + ", radnik=" + radnik + ", smena=" + smena + '}';
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
        final RS other = (RS) obj;
        if (!Objects.equals(this.datumRS, other.datumRS)) {
            return false;
        }
        if (!Objects.equals(this.radnik, other.radnik)) {
            return false;
        }
        return Objects.equals(this.smena, other.smena);
    }

    @Override
    public String vratiNazivTabele() {
        return "rs";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumRS,radnik,smena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+datumRS+"',"+radnik.getIdRadnik()+","+smena.getIdSmena();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rs.datumRS='"+datumRS+"' AND rs.radnik="+radnik.getIdRadnik()+" AND rs.smena="+smena.getIdSmena();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
