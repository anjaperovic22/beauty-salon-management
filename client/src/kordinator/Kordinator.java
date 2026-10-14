/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;


import forme.FormaMod;
import forme.LoginForma;
import kontroler.LoginKontroler;
import forme.GlavnaForma;
import forme.KreiranjeEvidencijeTerminaForma;
import forme.KreiranjeKlijenataForma;
import forme.KreiranjeKozmeticaraForma;
import forme.PretragaKlijenataForma;
import forme.PrikazEvidencijaTerminaForma;
import forme.PrikazKozmeticaraForma;
import forme.PrikazSmenaForma;
import java.util.HashMap;
import java.util.Map;
import kontroler.GlavnaFormaKontroler;
import kontroler.KreiranjeEvidencijeTerminaKontroler;
import kontroler.KreiranjeKlijenataKontroler;
import kontroler.KreiranjeKozmeticaraKontroler;
import kontroler.PretragaKlijenataKontroler;
import kontroler.PrikazEvidencijaTerminaKontroler;
import kontroler.PrikazKozmeticaraKontroler;
import kontroler.PrikazSmenaKontroler;
import model.Radnik;

/**
 *
 * @author Admin
 */
public class Kordinator {
    private static Kordinator instance;
    private Radnik ulogovani;
    private LoginKontroler loginKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    private PretragaKlijenataKontroler pretragaKlijenataKontroler;
    private KreiranjeKlijenataKontroler kreiranjeKlijenataKontroler;
    private PrikazKozmeticaraKontroler prikazKozmeticaraKontroler;
    private KreiranjeKozmeticaraKontroler kreiranjeKozmeticaraKontroler;
    private PrikazSmenaKontroler prikazSmenaKontroler;
    private PrikazEvidencijaTerminaKontroler prikazEvidencijaTerminaKontroler;
    private KreiranjeEvidencijeTerminaKontroler kreiranjeEvidencijeTerminaKontroler;
    private Map<String, Object> parametri;
    
    private Kordinator(){
        parametri = new HashMap<>();
    }
    public static Kordinator getInstance(){
        if(instance==null) instance=new Kordinator();
        return instance;
    }
    
    public Radnik getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Radnik ulogovani) {
        this.ulogovani = ulogovani;
    }
    public void otvoriLoginFormu() {
        loginKontroler=new LoginKontroler(new LoginForma());
        loginKontroler.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriFormu();
    }
    
    public void otvoriPretraziKlijentFormu() {
        pretragaKlijenataKontroler = new PretragaKlijenataKontroler(new PretragaKlijenataForma());
        pretragaKlijenataKontroler.otvoriFormu();
    }

    public void otvoriKreiranjeKlijenataFormu() {
        kreiranjeKlijenataKontroler = new KreiranjeKlijenataKontroler(new KreiranjeKlijenataForma());
        kreiranjeKlijenataKontroler.otvoriFormu(FormaMod.KREIRANJE);
    }

    public void dodajParametar(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParametar(String s){
        return parametri.get(s);
    }

    public void otvoriIzmenaKlijentaFormu() {
        kreiranjeKlijenataKontroler = new KreiranjeKlijenataKontroler(new KreiranjeKlijenataForma());
        kreiranjeKlijenataKontroler.otvoriFormu(FormaMod.IZMENA);
    }

    public void otvoriPrikazKozmeticaraFormu() {
        prikazKozmeticaraKontroler = new PrikazKozmeticaraKontroler(new PrikazKozmeticaraForma());
        prikazKozmeticaraKontroler.otvoriFormu();
    }
    
    public void otvoriKreiranjeKozmeticaraFormu(){
        kreiranjeKozmeticaraKontroler = new KreiranjeKozmeticaraKontroler(new KreiranjeKozmeticaraForma());
        kreiranjeKozmeticaraKontroler.otvoriFormu(FormaMod.KREIRANJE);
    }

    public void otvoriIzmenaKozmeticaraFormu() {
        kreiranjeKozmeticaraKontroler = new KreiranjeKozmeticaraKontroler(new KreiranjeKozmeticaraForma());
        kreiranjeKozmeticaraKontroler.otvoriFormu(FormaMod.IZMENA);
    }

    public void otvoriPrikazSmenaFormu() {
        prikazSmenaKontroler = new PrikazSmenaKontroler(new PrikazSmenaForma());
        prikazSmenaKontroler.otvoriFormu();
    }

    public void otvoriPrikazEvidencijaTerminaFormu() {
        prikazEvidencijaTerminaKontroler = new PrikazEvidencijaTerminaKontroler(new PrikazEvidencijaTerminaForma());
        prikazEvidencijaTerminaKontroler.otvoriFormu();
    }

    public void otvoriKreiranjeEvidencijaTerminaFormu() {
        kreiranjeEvidencijeTerminaKontroler = new KreiranjeEvidencijeTerminaKontroler(new KreiranjeEvidencijeTerminaForma());
        kreiranjeEvidencijeTerminaKontroler.otvoriFormu(FormaMod.KREIRANJE);
    }

    public void otvoriAzuriranjeEvidencijeTerminaFormu() {
        kreiranjeEvidencijeTerminaKontroler = new KreiranjeEvidencijeTerminaKontroler(new KreiranjeEvidencijeTerminaForma());
        kreiranjeEvidencijeTerminaKontroler.otvoriFormu(FormaMod.IZMENA);
    }
    
}
