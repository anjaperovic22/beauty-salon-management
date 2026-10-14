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
public class Radnik implements ApstraktniDomenskiObjekat{
    private int idRadnik;
    private String ime;
    private String prezime;
    private int dnevnica;
    private String korisnickoIme;
    private String sifra;

    public Radnik() {
    }

    public Radnik(int idRadnik, String ime, String prezime, int dnevnica, String korisnickoIme, String sifra) {
        this.idRadnik = idRadnik;
        this.ime = ime;
        this.prezime = prezime;
        this.dnevnica = dnevnica;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdRadnik() {
        return idRadnik;
    }

    public void setIdRadnik(int idRadnik) {
        this.idRadnik = idRadnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getDnevnica() {
        return dnevnica;
    }

    public void setDnevnica(int dnevnica) {
        this.dnevnica = dnevnica;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
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
        final Radnik other = (Radnik) obj;
        return this.idRadnik == other.idRadnik;
    }

    @Override
    public String vratiNazivTabele() {
        return "radnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idRadnik1 = rs.getInt("radnik.idRadnik");
            String ime1 = rs.getString("radnik.ime");
            String prezime1 = rs.getString("radnik.prezime");
            int dnevnica1 = rs.getInt("radnik.dnevnica");
            String korisnickoIme1 = rs.getString("radnik.korisnickoIme");
            String sifra1 = rs.getString("radnik.sifra");
            
            Radnik r1 = new Radnik(idRadnik1, ime1, prezime1, dnevnica1, korisnickoIme1, sifra1);
            lista.add(r1);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,dnevnica,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"',"+dnevnica+",'"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "radnik.idRadnik="+idRadnik;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', dnevnica="+dnevnica+", korisnickoIme='"+korisnickoIme+"', sifra='"+sifra+"'";
    }
    
}
