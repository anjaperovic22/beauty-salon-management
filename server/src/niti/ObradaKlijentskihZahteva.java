/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;
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
public class ObradaKlijentskihZahteva extends Thread {
    Socket soket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket soket) {
        this.soket=soket;
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
    }
    
    @Override
    public void run() {
        while(!kraj){
            try{
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                try{
                    switch(zahtev.getOperacija()){
                        
                        case LOGIN:
                            Radnik r = (Radnik) zahtev.getParametar();
                            Radnik ulogovani = Kontroler.getInstance().login(r);
                            odgovor.setOdgovor(ulogovani);
                            break;
                            
                        case VRATI_LISTU_SVIH_KLIJENATA:
                            List<Klijent> listaKlijenata = Kontroler.getInstance().ucitajKlijente();
                            odgovor.setOdgovor(listaKlijenata);
                            break;
                            
                        case OBRISI_KLIJENTA:
                            Klijent k = (Klijent) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().obrisiKlijenta(k);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case KREIRAJ_KLIJENTA:
                            Klijent k1 = (Klijent) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().kreirajKlijenta(k1);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case IZMENI_KLIJENTA:
                            Klijent k2 = (Klijent) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().izmeniKlijenta(k2);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case OBRISI_KOZMETICARA:
                            Kozmeticar ko = (Kozmeticar) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().obrisiKozmeticara(ko);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                        
                        case VRATI_LISTU_SVIH_KOZMETICARA:
                            List<Kozmeticar> listaKozmeticara = Kontroler.getInstance().ucitajKozmeticare();
                            odgovor.setOdgovor(listaKozmeticara);
                            break;
                            
                        case VRATI_LISTU_SVIH_KATEGORIJA:
                            List<Kategorija> listaKategorija = Kontroler.getInstance().ucitajKategorije();
                            odgovor.setOdgovor(listaKategorija);
                            break;
                            
                        case KREIRAJ_KOZMETICARA:
                            Kozmeticar ko1 = (Kozmeticar) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().kreirajKozmeticara(ko1);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case IZMENI_KOZMETICARA:
                            Kozmeticar ko2 = (Kozmeticar) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().izmeniKozmeticara(ko2);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                        
                        case UBACI_SMENU:
                            Smena s = (Smena) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().ubaciSmenu(s);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case VRATI_LISTU_SVIH_SMENA:
                            List<Smena> listaSmena = Kontroler.getInstance().ucitajSmene();
                            odgovor.setOdgovor(listaSmena);
                            break;
                        
                        case VRATI_LISTU_SVIH_EVIDENCIJA_TERMINA:
                            List<EvidencijaTermina> listaET = Kontroler.getInstance().ucitajEvidencijeTermina();
                            odgovor.setOdgovor(listaET);
                            break;
                        
                        case VRATI_LISTU_SVIH_STAVKI_EVIDENCIJA_TERMINA:
                            List<StavkaEvidencijeTermina> listaStavki = Kontroler.getInstance().ucitajStavkeET();
                            odgovor.setOdgovor(listaStavki);
                            break;
                            
                        case VRATI_LISTU_STAVKI_ZA_EVIDENCIJU:
                            List<StavkaEvidencijeTermina> listaStavkiZaEvidenciju = Kontroler.getInstance().ucitajStavkeZaEvidenciju((int)zahtev.getParametar());
                            odgovor.setOdgovor(listaStavkiZaEvidenciju);
                            break;
                            
                        case OBRISI_EVIDENCIJU_TERMINA:
                            EvidencijaTermina et = (EvidencijaTermina) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().obrisiEvidencijuTermina(et);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                        
                        case KREIRAJ_EVIDENCIJU_TERMINA:
                            EvidencijaTermina et1 = (EvidencijaTermina) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().kreirajEvidencijuTermina(et1);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case OBRISI_STAVKU_EVIDENCIJE_TERMINA:
                            StavkaEvidencijeTermina st = (StavkaEvidencijeTermina) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().obrisiStavkuET(st);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case IZMENI_STAVKU_EVIDENCIJE_TERMINA:
                            StavkaEvidencijeTermina st1 = (StavkaEvidencijeTermina) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().izmeniStavkuEvidencijeTermina(st1);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                            
                        case IZMENI_EVIDENCIJU_TERMINA:
                            EvidencijaTermina et2 = (EvidencijaTermina) zahtev.getParametar();
                            odgovor.setOdgovor(true);
                            try{
                                Kontroler.getInstance().izmeniEvidencijuTermina(et2);
                            }catch(Exception e){
                                odgovor.setOdgovor(false);
                            }
                            break;
                        
                        case VRATI_LISTU_SVIH_RADNIKA:
                            List<Radnik> listaRadnika = Kontroler.getInstance().ucitajRadnike();
                            odgovor.setOdgovor(listaRadnika);
                            break;
                        
                        case PRETRAZI_EVIDENCIJE_TERMINA2:
                            EvidencijaTermina et3 = (EvidencijaTermina) zahtev.getParametar();
                            List<EvidencijaTermina> l = Kontroler.getInstance().pretraziEvidencijeTermina2(et3);
                            odgovor.setOdgovor(l);
                            break;
                            
                        default:
                            System.out.println("Nepoznata operacija, "+zahtev.getOperacija());
                            
                        
                    }
                    
                } catch (NullPointerException e) {
                } posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
    }

    public void zaustaviKlijenta() {
        kraj=true;
        try {
            soket.close();
        } catch (IOException ex) {
            System.out.println("Prekinuta veza sa klijentom");
        }
        interrupt();
    }
    
    
}
