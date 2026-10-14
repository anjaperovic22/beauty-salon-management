/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Kategorija implements ApstraktniDomenskiObjekat {
    private int idKategorija;
    private String nazivKategorije;
    private String opis;

    public Kategorija() {
    }

    public Kategorija(int idKategorija, String nazivKategorije, String opis) {
        this.idKategorija = idKategorija;
        this.nazivKategorije = nazivKategorije;
        this.opis = opis;
    }

    public int getIdKategorija() {
        return idKategorija;
    }

    public void setIdKategorija(int idKategorija) {
        this.idKategorija = idKategorija;
    }

    public String getNazivKategorija() {
        return nazivKategorije;
    }

    public void setNazivKategorija(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return nazivKategorije;
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
        final Kategorija other = (Kategorija) obj;
        return this.idKategorija == other.idKategorija;
    }
    

    @Override
    public String vratiNazivTabele() {
        return "kategorija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idKategorija1 = rs.getInt("kategorija.idKategorija");
            String nazivKategorije1 = rs.getString("kategorija.nazivKategorije");
            String opis1 = rs.getString("kategorija.opis");
            Kategorija kategorija1 = new Kategorija(idKategorija1, nazivKategorije1, opis1);
            lista.add(kategorija1);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKategorije,opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivKategorije+"','"+opis+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kategorija.idKategorija="+idKategorija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivKategorije='"+nazivKategorije+"', opis='"+opis+"'";
    }

    
    
}
