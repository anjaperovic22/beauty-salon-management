/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class EvidencijaTermina implements ApstraktniDomenskiObjekat {
    private int idEvidencijaTermina;
    private Date datumTermina;
    private int ukupnaCena;
    private List<StavkaEvidencijeTermina> listaStavkiET;
    private Radnik radnik;
    private Kozmeticar kozmeticar;
    private Klijent klijent;

    public EvidencijaTermina() {
    }

    public EvidencijaTermina(int idEvidencijaTermina, Date datumTermina, int ukupnaCena, List<StavkaEvidencijeTermina> listaStavkiET, Radnik radnik, Kozmeticar kozmeticar, Klijent klijent) {
        this.idEvidencijaTermina = idEvidencijaTermina;
        this.datumTermina = datumTermina;
        this.ukupnaCena = ukupnaCena;
        this.listaStavkiET = listaStavkiET;
        this.radnik = radnik;
        this.kozmeticar = kozmeticar;
        this.klijent = klijent;
    }

    public int getIdEvidencijaTermina() {
        return idEvidencijaTermina;
    }

    public void setIdEvidencijaTermina(int idEvidencijaTermina) {
        this.idEvidencijaTermina = idEvidencijaTermina;
    }

    public Date getDatumTermina() {
        return datumTermina;
    }

    public void setDatumTermina(Date datumTermina) {
        this.datumTermina = datumTermina;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public List<StavkaEvidencijeTermina> getListaStavkiET() {
        return listaStavkiET;
    }

    public void setListaStavkiET(List<StavkaEvidencijeTermina> listaStavkiET) {
        this.listaStavkiET = listaStavkiET;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Kozmeticar getKozmeticar() {
        return kozmeticar;
    }

    public void setKozmeticar(Kozmeticar kozmeticar) {
        this.kozmeticar = kozmeticar;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String toString() {
        return "EvidencijaTermina{" + "idEvidencijaTermina=" + idEvidencijaTermina + ", datumTermina=" + datumTermina + ", ukupnaCena=" + ukupnaCena + ", listaStavkiET=" + listaStavkiET + ", radnik=" + radnik + ", kozmeticar=" + kozmeticar + ", klijent=" + klijent + '}';
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
        final EvidencijaTermina other = (EvidencijaTermina) obj;
        return this.idEvidencijaTermina == other.idEvidencijaTermina;
    }

    @Override
    public String vratiNazivTabele() {
        return "evidencijatermina";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            
            EvidencijaTermina et = new EvidencijaTermina();
            //1 id
            et.setIdEvidencijaTermina(rs.getInt("evidencijatermina.idEvidencijaTermina"));
            java.sql.Date datumSql = rs.getDate("evidencijatermina.datumTermina");
            java.util.Date datum;
            if (datumSql == null) {
                datum = null;
            } else {
                datum = new java.util.Date(datumSql.getTime());
            }
            //2 datum
            et.setDatumTermina(datum);
            //3 cena
            et.setUkupnaCena(rs.getInt("evidencijatermina.ukupnaCena"));
            
            Radnik r = new Radnik();
            r.setIdRadnik(rs.getInt("radnik.idRadnik"));
            r.setIme(rs.getString("radnik.ime"));
            r.setPrezime(rs.getString("radnik.prezime"));
            r.setDnevnica(rs.getInt("radnik.dnevnica"));
            r.setKorisnickoIme(rs.getString("radnik.korisnickoIme"));
            r.setSifra(rs.getString("radnik.sifra"));
            
            Kozmeticar k = new Kozmeticar();            
            k.setIdKozmeticar(rs.getInt("kozmeticar.idKozmeticar"));
            k.setIme(rs.getString("kozmeticar.ime"));
            k.setPrezime(rs.getString("kozmeticar.prezime"));
            
            Klijent kl = new Klijent();
            kl.setIdKlijent(rs.getInt("klijent.idKlijent"));
            kl.setIme(rs.getString("klijent.ime"));
            kl.setPrezime(rs.getString("klijent.prezime"));
            kl.setBrojTelefona(rs.getString("klijent.brojTelefona"));
            
            //4 radnik
            et.setRadnik(r);
            //5 kozmeticar
            et.setKozmeticar(k);
            //6 klijent
            et.setKlijent(kl);
            
            //7 stavke
            et.setListaStavkiET(new ArrayList<>());
            
            lista.add(et);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumTermina,ukupnaCena,radnik,kozmeticar,klijent";
    }
    
    @Override
    public String vratiVrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = sdf.format(datumTermina);
    
        return "'" + datumStr + "'," + ukupnaCena + "," + radnik.getIdRadnik()+ "," + kozmeticar.getIdKozmeticar()+ "," + klijent.getIdKlijent();    
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "evidencijatermina.idEvidencijaTermina="+idEvidencijaTermina;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekat(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = sdf.format(datumTermina);
        
        return "kozmeticar="+kozmeticar.getIdKozmeticar()+", klijent="+klijent.getIdKlijent()+", datumTermina='"+datumStr+"', ukupnaCena="+ukupnaCena;
    }
    
}
