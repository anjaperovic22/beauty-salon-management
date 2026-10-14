package kontroler;

import java.util.List;
import model.EvidencijaTermina;
import model.Kategorija;
import model.Klijent;
import model.Kozmeticar;
import model.Radnik;
import model.Smena;
import model.StavkaEvidencijeTermina;
import operacija.LoginSO;
import operacija.evidencijaTermina.BrisanjeEvidencijeTerminaSO;
import operacija.evidencijaTermina.IzmenaEvidencijeTerminaSO;
import operacija.evidencijaTermina.KreiranjeEvidencijeTerminaSO;
import operacija.evidencijaTermina.PretragaEvidencijaTerminaSO;
import operacija.evidencijaTermina.UcitajEvidencijeTerminaSO;
import operacija.kategorija.UcitajKategorijeSO;
import operacija.kozmeticar.UcitajKozmeticareSO;
import operacija.klijent.BrisanjeKlijentaSO;
import operacija.klijent.IzmenaKlijenataSO;
import operacija.klijent.KreiranjeKlijenataSO;
import operacija.klijent.UcitajKlijenteSO;
import operacija.kozmeticar.BrisanjeKozmeticaraSO;
import operacija.kozmeticar.IzmenaKozmeticaraSO;
import operacija.kozmeticar.KreiranjeKozmeticaraSO;
import operacija.radnik.UcitajRadnikeSO;
import operacija.smena.UbacivanjeSmeneSO;
import operacija.smena.UcitajSmeneSO;
import operacija.stavkaEvidencijeTermina.BrisanjeStavkeETSO;
import operacija.stavkaEvidencijeTermina.IzmenaStavkeETSO;
import operacija.stavkaEvidencijeTermina.UcitajStavkeETSO;
import operacija.stavkaEvidencijeTermina.UcitajStavkeZaEvidencijuSO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Kontroler {
    private static Kontroler instance;
    private Kontroler(){
        
    }
    public static Kontroler getInstance(){
        if(instance==null)instance=new Kontroler();
        return instance;
    }

    public Radnik login(Radnik r) throws Exception {
        LoginSO so = new LoginSO();
        so.izvrsi(r, null);
        System.out.println("Zaposleni dobijen u kontroleru:" + so.getRadnik());
        return so.getRadnik();
    }

    public List<Klijent> ucitajKlijente() throws Exception {
        UcitajKlijenteSO so = new UcitajKlijenteSO();
        so.izvrsi(null,null);
        System.out.println("Kontroler: "+so.getKlijenti());
        return so.getKlijenti();
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        BrisanjeKlijentaSO so = new BrisanjeKlijentaSO();
        so.izvrsi(k,null);
    }

    public void kreirajKlijenta(Klijent k) throws Exception {
        KreiranjeKlijenataSO so = new KreiranjeKlijenataSO();
        so.izvrsi(k,null);
    }

    public void izmeniKlijenta(Klijent k) throws Exception {
        IzmenaKlijenataSO so = new IzmenaKlijenataSO();
        so.izvrsi(k,null);
    }

    public void obrisiKozmeticara(Kozmeticar ko) throws Exception {
        BrisanjeKozmeticaraSO so = new BrisanjeKozmeticaraSO();
        so.izvrsi(ko,null);
    }

    public List<Kozmeticar> ucitajKozmeticare() throws Exception {
        UcitajKozmeticareSO so = new UcitajKozmeticareSO();
        so.izvrsi(null,null);
        
        System.out.println("SQL rezultat: " + so.getKozmeticari().size());
        for(Kozmeticar k : so.getKozmeticari()) {
            System.out.println(k);
        }
        
        System.out.println("Kontroler: "+so.getKozmeticari());
        return so.getKozmeticari();
    }

    public List<Kategorija> ucitajKategorije() throws Exception {
        UcitajKategorijeSO so = new UcitajKategorijeSO();
        so.izvrsi(null,null);
        return so.getKategorije();
    }

    public void kreirajKozmeticara(Kozmeticar k) throws Exception {
        KreiranjeKozmeticaraSO so = new KreiranjeKozmeticaraSO();
        so.izvrsi(k, null);
    }

    public void izmeniKozmeticara(Kozmeticar k) throws Exception {
        IzmenaKozmeticaraSO so = new IzmenaKozmeticaraSO();
        so.izvrsi(k,null);
    }

    public void ubaciSmenu(Smena s) throws Exception {
        UbacivanjeSmeneSO so = new UbacivanjeSmeneSO();
        so.izvrsi(s,null);
    }

    public List<Smena> ucitajSmene() throws Exception {
        UcitajSmeneSO so = new UcitajSmeneSO();
        so.izvrsi(null,null);
        return so.getSmene();
    }

    public List<EvidencijaTermina> ucitajEvidencijeTermina() throws Exception {
        UcitajEvidencijeTerminaSO so = new UcitajEvidencijeTerminaSO();
        so.izvrsi(null,null);
        return so.getEvidencijeTermina();
    }

    public List<StavkaEvidencijeTermina> ucitajStavkeET() throws Exception {
        UcitajStavkeETSO so = new UcitajStavkeETSO();
        so.izvrsi(null,null);
        return so.getStavke();
    }

    public List<StavkaEvidencijeTermina> ucitajStavkeZaEvidenciju(int id) throws Exception {
        UcitajStavkeZaEvidencijuSO so = new UcitajStavkeZaEvidencijuSO();
        so.izvrsi(id,null);
        return so.getStavke();
    }

    public void obrisiEvidencijuTermina(EvidencijaTermina et) throws Exception {
        BrisanjeEvidencijeTerminaSO so = new BrisanjeEvidencijeTerminaSO();
        so.izvrsi(et,null);
    }

    public void kreirajEvidencijuTermina(EvidencijaTermina et) throws Exception {
        KreiranjeEvidencijeTerminaSO so = new KreiranjeEvidencijeTerminaSO();
        so.izvrsi(et, null);
    }

    public void obrisiStavkuET(StavkaEvidencijeTermina s) throws Exception {
        BrisanjeStavkeETSO so = new BrisanjeStavkeETSO();
        so.izvrsi(s, null);
    }

    public void izmeniStavkuEvidencijeTermina(StavkaEvidencijeTermina s) throws Exception {
        IzmenaStavkeETSO so = new IzmenaStavkeETSO();
        so.izvrsi(s,null);
    }

    public void izmeniEvidencijuTermina(EvidencijaTermina et) throws Exception {
        IzmenaEvidencijeTerminaSO so = new IzmenaEvidencijeTerminaSO();
        so.izvrsi(et,null);
    }

    public List<Radnik> ucitajRadnike() throws Exception {
        UcitajRadnikeSO so = new UcitajRadnikeSO();
        so.izvrsi(null,null);
        return so.getRadnici();
    }
    
    public List<EvidencijaTermina> pretraziEvidencijeTermina2(EvidencijaTermina et) throws Exception {
        PretragaEvidencijaTerminaSO so = new PretragaEvidencijaTerminaSO();
        so.izvrsi(et,null);
        return so.getListaET();
    }

}
