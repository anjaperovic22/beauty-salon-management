/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kordinator.Kordinator;
import model.EvidencijaTermina;
import model.Kategorija;
import model.Klijent;
import model.Kozmeticar;
import model.Radnik;
import model.Smena;
import model.StavkaEvidencijeTermina;

/**
 *
 * @author Admin
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;
    
    private Komunikacija(){
        
    }
    public static Komunikacija getInstance(){
        if(instance==null)instance=new Komunikacija();
        return instance;
    }
    
    public void konekcija() throws Exception{
        try{
            soket = new Socket("localhost",9000);
            primalac = new Primalac(soket);
            posiljalac = new Posiljalac(soket);
        }catch(IOException e){
            System.out.println("Greska prilikom povezivanja!");
        }
        
    }

    public Radnik login(String korisnickoIme, String lozinka) {
        Radnik r = new Radnik(0, null, null,0, korisnickoIme, lozinka);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, r);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        Radnik ulogovani = (Radnik)odgovor.getOdgovor();
        return ulogovani;
    }

    public List<Klijent> vratiListuSvihKlijenata() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_KLIJENATA, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        List<Klijent> lista = new ArrayList<>();
        lista = (List<Klijent>)odgovor.getOdgovor();
        return lista;
    }

    public String obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je obrisao klijenta.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public String kreirajKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je obrisao klijenta.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public String izmeniKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            Kordinator.getInstance().otvoriPretraziKlijentFormu();
            return "Sistem je izmenio klijenta.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public String obrisiKozmeticara(Kozmeticar k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KOZMETICARA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je obrisao kozmetičara.";
        }else{
            throw new Exception();
        }
    }
    
    public List<Kozmeticar> vratiListuSvihKozmeticara() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_KOZMETICARA, null);
        List<Kozmeticar> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<Kozmeticar>)odgovor.getOdgovor();
        return lista;
    }

    public List<Kategorija> vratiListuSvihKategorija() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_KATEGORIJA, null);
        List<Kategorija> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<Kategorija>)odgovor.getOdgovor();
        return lista;
    }

    public String kreirajKozmeticara(Kozmeticar k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_KOZMETICARA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je kreirao kozmetičara.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public String izmeniKozmeticara(Kozmeticar k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KOZMETICARA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            Kordinator.getInstance().otvoriPrikazKozmeticaraFormu();
            return "Sistem je izmenio kozmetičara.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public List<Smena> vratiListuSvihSmena() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_SMENA, null);
        List<Smena> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<Smena>)odgovor.getOdgovor();
        return lista;
    }

    public String ubaciSmenu(Smena s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_SMENU, s);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je zapamtio smenu.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public List<EvidencijaTermina> vratiListuSvihEvidencijaTermina() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_EVIDENCIJA_TERMINA, null);
        List<EvidencijaTermina> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<EvidencijaTermina>)odgovor.getOdgovor();
        return lista;
    }

    public List<StavkaEvidencijeTermina> vratiListuSvihStavkiET() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_STAVKI_EVIDENCIJA_TERMINA, null);
        List<StavkaEvidencijeTermina> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<StavkaEvidencijeTermina>)odgovor.getOdgovor();
        return lista;
    }

    public List<StavkaEvidencijeTermina> ucitajStavkeZaET(int id) {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_STAVKI_ZA_EVIDENCIJU, id);
        List<StavkaEvidencijeTermina> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<StavkaEvidencijeTermina>)odgovor.getOdgovor();
        return lista;
    }

    public String obrisiEvidencijuTermina(EvidencijaTermina et) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_EVIDENCIJU_TERMINA, et);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je obrisao evidenciju termina.";
        }else{
            throw new Exception();
        }
    }
    
    public String kreirajEvidencijuTermina(EvidencijaTermina et) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_EVIDENCIJU_TERMINA, et);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je kreirao evidenciju termina.";
        }else{
            throw new Exception();
            //return "Sistem ne može da obriše klijenta.";
        }
    }

    public String obrisiStavkuEvidencijeTermina(StavkaEvidencijeTermina s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STAVKU_EVIDENCIJE_TERMINA, s);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je obrisao stavku evidenciju termina.";
        }else{
            throw new Exception();
        }
    }

    public String izmeniStavkuET(StavkaEvidencijeTermina s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_STAVKU_EVIDENCIJE_TERMINA, s);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je izmenio stavku.";
        }else{
            throw new Exception();
        }
    }

    public String izmeniEvidencijuTermina(EvidencijaTermina et) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_EVIDENCIJU_TERMINA, et);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je izmenio evidenciju termina.";
        }else{
            throw new Exception();
        }
    }

    public String ubaciStavku(StavkaEvidencijeTermina s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_STAVKU_EVIDENCIJE_TERMINA, s);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        boolean flag = (boolean)odgovor.getOdgovor();
        if(flag){
            return "Sistem je zapamtio stavku.";
        }else{
            throw new Exception();
        }
    }

    public List<Radnik> vratiListuSvihRadnika() {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVIH_RADNIKA, null);
        List<Radnik> lista = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        lista = (List<Radnik>)odgovor.getOdgovor();
        return lista;
    }

    public List<EvidencijaTermina> pretrazi(Map<String, Object> kriterijumi) {
        Zahtev z=new Zahtev(Operacija.PRETRAZI_EVIDENCIJE_TERMINA,kriterijumi);
        posiljalac.posalji(z);
        
        List<EvidencijaTermina> lista=new ArrayList<>();
        Odgovor odg=(Odgovor) primalac.primi();
        lista= (List<EvidencijaTermina>) odg.getOdgovor();
        System.out.println("listaET  "+lista);
        return lista;
    }

    public List<EvidencijaTermina> filtrirajEvidencijuTermina(EvidencijaTermina et) {
        Zahtev z=new Zahtev(Operacija.PRETRAZI_EVIDENCIJE_TERMINA2, et);
        posiljalac.posalji(z);
        
        List<EvidencijaTermina> lista=new ArrayList<>();
        Odgovor odg=(Odgovor) primalac.primi();
        lista= (List<EvidencijaTermina>) odg.getOdgovor();
        System.out.println("listaET  "+lista);
        return lista;
    }

    
}
