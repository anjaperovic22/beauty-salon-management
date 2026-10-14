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
public class Kozmeticar implements ApstraktniDomenskiObjekat {
    private int idKozmeticar;
    private String ime;
    private String prezime;
    private Kategorija kategorija;

    public Kozmeticar() {
    }

    public Kozmeticar(int idKozmeticar, String ime, String prezime, Kategorija kategorija) {
        this.idKozmeticar = idKozmeticar;
        this.ime = ime;
        this.prezime = prezime;
        this.kategorija = kategorija;
    }

    public int getIdKozmeticar() {
        return idKozmeticar;
    }

    public void setIdKozmeticar(int idKozmeticar) {
        this.idKozmeticar = idKozmeticar;
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

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
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
        final Kozmeticar other = (Kozmeticar) obj;
        return this.idKozmeticar == other.idKozmeticar;
    }

    @Override
    public String toString() {
        if (kategorija==null)
            return ime + " " + prezime;
        else 
            return kategorija.getNazivKategorija() +": "+ime +" "+prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "kozmeticar";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idKozmeticar1 = rs.getInt("kozmeticar.idKozmeticar");
            String ime1 = rs.getString("kozmeticar.ime");
            String prezime1 = rs.getString("kozmeticar.prezime");
            
            int idKategorija1 = rs.getInt("kategorija.idKategorija");
            String nazivKategorije1 = rs.getString("kategorija.nazivKategorije");
            String opis1 = rs.getString("kategorija.opis");
            
            Kategorija kategorija1 = new Kategorija(idKategorija1, nazivKategorije1, opis1);
            
            Kozmeticar kozmeticar1 = new Kozmeticar(idKozmeticar1, ime1, prezime1, kategorija1);
            
            lista.add(kozmeticar1);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,kategorija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"',"+kategorija.getIdKategorija();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kozmeticar.idKozmeticar="+idKozmeticar;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', kategorija="+kategorija.getIdKategorija();
    }


}
