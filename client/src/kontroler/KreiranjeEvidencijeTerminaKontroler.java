/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.FormaMod;
import forme.KreiranjeEvidencijeTerminaForma;
import forme.model.ModelTabeleEvidencijaTermina;
import forme.model.ModelTabeleStavkaET;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;
import model.EvidencijaTermina;
import model.Klijent;
import model.Kozmeticar;
import model.Radnik;
import model.StavkaEvidencijeTermina;

/**
 *
 * @author Admin
 */
public class KreiranjeEvidencijeTerminaKontroler {
    private final KreiranjeEvidencijeTerminaForma kef;
    private List<Kozmeticar> kozmeticariCB = new ArrayList<>();
    private List<Klijent> klijentiCB = new ArrayList<>();
    private List<Radnik> radniciCB = new ArrayList<>();
    private List<StavkaEvidencijeTermina> stavkeLista = new ArrayList<>();
    
    public KreiranjeEvidencijeTerminaKontroler(KreiranjeEvidencijeTerminaForma kef){
        this.kef=kef;
        addActionListeners();
        addMouseListeners();
    }

    public void otvoriFormu(FormaMod fm){
        popuniComboBoxeve();
        
        
        kreirajFormu(fm);
        kef.setVisible(true);
    }
    
    private void addActionListeners() {
        
        kef.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku(e);
            }

            private void dodajStavku(ActionEvent e) {
                int cena = 0;
                int trajanjeMinuti = 0;
                try{
                    cena = Integer.parseInt(kef.getjTextFieldCenaStavke().getText());
                    trajanjeMinuti = Integer.parseInt(kef.getjTextFieldTrajanje().getText());
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da kreira evidenciju termina, stavke cena trajanje.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
                
                if(cena<0 || trajanjeMinuti<0){
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da kreira evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
                
                String naziv = kef.getjTextFieldNazivStavke().getText();
                
                StavkaEvidencijeTermina s= new StavkaEvidencijeTermina();
                s.setCena(cena);
                s.setTrajanjeMinuti(trajanjeMinuti);
                s.setNazivStavkeET(naziv);

                ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
                mtse.dodajStavku(s);
                if(kef.getjTextFieldIdEvidencijeTermina().getText()!=null && !kef.getjTextFieldIdEvidencijeTermina().getText().isEmpty()){
                    int id = Integer.parseInt(kef.getjTextFieldIdEvidencijeTermina().getText());
                    int rb = mtse.getTrenutniRb();
                    s.setRb(rb);
                    EvidencijaTermina et = vratiETpoPK(id);
                    et.getListaStavkiET().add(s);
                }
                
            }
        });

        kef.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);

            }

            private void obrisi(ActionEvent e) {
                int red = kef.getjTableStavkaEvidencijeTermina().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(kef, "Sistem ne moze da obriše stavku evidencije", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    if(kef.getjTextFieldIdEvidencijeTermina().getText()==null||kef.getjTextFieldIdEvidencijeTermina().getText().isEmpty()){
                        ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
                        StavkaEvidencijeTermina s = mtse.getLista().get(red);
                        mtse.obrisiStavku(s); 
                    }else{
                        int id = Integer.parseInt(kef.getjTextFieldIdEvidencijeTermina().getText());
                        
                        try{
                            ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
                            StavkaEvidencijeTermina s = mtse.getLista().get(red);
                            List<EvidencijaTermina> lista = Komunikacija.getInstance().vratiListuSvihEvidencijaTermina();
                            for(EvidencijaTermina et: lista){
                                if(id==et.getIdEvidencijaTermina()){
                                    s.setEvidencijaTermina(et);
                                }
                            }
                            String porukaOBrisanju = Komunikacija.getInstance().obrisiStavkuEvidencijeTermina(s);
                            JOptionPane.showMessageDialog(kef, porukaOBrisanju, "USPEH", JOptionPane.INFORMATION_MESSAGE);
                            Komunikacija.getInstance().ucitajStavkeZaET(id);
                        }catch(Exception e1){
                            JOptionPane.showMessageDialog(kef, "Sistem ne može da obriše evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                    
                }

            }
        });
        
        kef.addBtnKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datum = kef.getjTextFieldDatum().getText();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date datumUtil = new Date();
                try {
                    datumUtil = sdf.parse(datum);
                    System.out.println("Parsiran datum: " + datumUtil);
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da kreira evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    pe.printStackTrace();
                }
                Klijent kl = (Klijent) kef.getjComboBoxKlijent().getSelectedItem();
                Kozmeticar ko = (Kozmeticar) kef.getjComboBoxKozmeticar().getSelectedItem();
                Radnik r = kordinator.Kordinator.getInstance().getUlogovani();
                
                EvidencijaTermina et = new EvidencijaTermina();
                et.setDatumTermina(datumUtil);
                et.setIdEvidencijaTermina(-1);
                et.setKlijent(kl);
                et.setKozmeticar(ko);
                et.setRadnik(r);
                
                ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
                List<StavkaEvidencijeTermina> stavke = new ArrayList<>();
                stavke = mtse.getLista();
                
                if(stavke==null || stavke.isEmpty()){
                    JOptionPane.showMessageDialog(kef, "nema stavki");
                    return;
                }
                
                et.setListaStavkiET(stavke);
                int c = 0;
                for(StavkaEvidencijeTermina s: stavke){
                    c+=s.getCena();
                }
                et.setUkupnaCena(c);
                
                boolean flag = validacijaET(et);
                
                if(flag){
                    try {
                        String poruka = Komunikacija.getInstance().kreirajEvidencijuTermina(et);
                        JOptionPane.showMessageDialog(kef, "Sistem je kreirao evidenciju termina.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(kef, "Sistem je zapamtio evidenciju termina.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        kef.dispose();
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(kef, "Sistem ne može da zapamti evidenciju termina", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                        kef.dispose();
                    }
                }
            }
        });
        
        kef.addBtnIzmeniStavkuActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int rb = Integer.parseInt(kef.getjTextFieldRB().getText());
                int id = Integer.parseInt(kef.getjTextFieldIdEvidencijeTermina().getText());
                String naziv = kef.getjTextFieldNazivStavke().getText();
                
                int c = 0, t = 0;
                
                try{
                    c = Integer.parseInt(kef.getjTextFieldCenaStavke().getText());
                    t = Integer.parseInt(kef.getjTextFieldTrajanje().getText());
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da izmeni stavku evidencije", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    kef.dispose();
                }
                
                EvidencijaTermina et = vratiETpoPK(id);
                
                if(et==null){
                     JOptionPane.showMessageDialog(kef, "Sistem ne može da izmeni evidenciju termina, et", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                     kef.dispose();
                }
                
                StavkaEvidencijeTermina s = new StavkaEvidencijeTermina();
                s.setRb(rb);
                s.setCena(c);
                s.setTrajanjeMinuti(t);
                s.setNazivStavkeET(naziv);
                s.setEvidencijaTermina(et);
                try {
                    String poruka = Komunikacija.getInstance().izmeniStavkuET(s);
                    JOptionPane.showMessageDialog(kef, poruka, "USPEH", JOptionPane.INFORMATION_MESSAGE);
                   
                    resetujTabeluStavki(id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da zapamti evidenciju termina");
                }
            }

            
        });

        kef.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(kef.getjTextFieldIdEvidencijeTermina().getText());
                Kozmeticar ko = vratiKozmeticara(id);
                Klijent kl = vratiKlijenta(id);
                List<StavkaEvidencijeTermina> listaStavki = new ArrayList<>();
                ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
                listaStavki = mtse.getLista();
                EvidencijaTermina et = new EvidencijaTermina();
                String datumString = kef.getjTextFieldDatum().getText();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                Date datum = new Date();
                try {
                    datum = sdf.parse(datumString);
                    System.out.println("Parsiran datum: " + datum);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da zapamti evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int cena = vratiUkupnuCenu(listaStavki);
                et.setIdEvidencijaTermina(id);
                et.setKozmeticar(ko);
                et.setKlijent(kl);
                et.setListaStavkiET(listaStavki);
                et.setDatumTermina(datum);
                et.setUkupnaCena(cena);
                try {
                    String poruka = Komunikacija.getInstance().izmeniEvidencijuTermina(et);
                    JOptionPane.showMessageDialog(kef, "Sistem je zapamtio evidenciju termina", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    kef.dispose();
                    Kordinator.getInstance().otvoriPrikazEvidencijaTerminaFormu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(kef, "Sistem ne može da zapamti evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }

            private int vratiUkupnuCenu(List<StavkaEvidencijeTermina> listaStavki) {
                int c = 0;
                for(StavkaEvidencijeTermina s: listaStavki){
                    c+=s.getCena();
                }
                return c;
            }

            private Kozmeticar vratiKozmeticara(int id) {
                List<EvidencijaTermina> lista = new ArrayList<>();
                lista = Komunikacija.getInstance().vratiListuSvihEvidencijaTermina();
                for(EvidencijaTermina e: lista){
                    if(e.getIdEvidencijaTermina()==id){
                        return e.getKozmeticar();
                    }
                }
                return null;
            }
            
            private Klijent vratiKlijenta(int id) {
                List<EvidencijaTermina> lista = new ArrayList<>();
                lista = Komunikacija.getInstance().vratiListuSvihEvidencijaTermina();
                for(EvidencijaTermina e: lista){
                    if(e.getIdEvidencijaTermina()==id){
                        return e.getKlijent();
                    }
                }
                return null;
            }
        });
        
    }

    private void kreirajFormu(FormaMod fm) {
        switch(fm){
            case KREIRANJE:
                List<StavkaEvidencijeTermina> l =new ArrayList<>();
                ModelTabeleStavkaET mtse =new ModelTabeleStavkaET(l);
                kef.getjTableStavkaEvidencijeTermina().setModel(mtse);
                kef.getjTextFieldIdEvidencijeTermina().setEnabled(false);
                kef.getjButtonIzmeni().setVisible(false);
                kef.getjButtonKreiraj().setVisible(true);
                kef.getjButtonKreiraj().setEnabled(true);
                kef.getjButtonIzmeniStavku().setVisible(false);
                kef.getjButtonIzmeniStavku().setEnabled(false);
                break;
            case IZMENA:
                popuniComboBoxeve();
                kef.getjTextFieldIdEvidencijeTermina().setEnabled(false);
                kef.getjButtonKreiraj().setVisible(false);
                kef.getjButtonIzmeni().setVisible(true);
                kef.getjButtonIzmeni().setEnabled(true);
                kef.getjButtonIzmeniStavku().setVisible(true);
                kef.getjButtonIzmeniStavku().setEnabled(true);
                EvidencijaTermina ev = (EvidencijaTermina) Kordinator.getInstance().vratiParametar("Evidencija termina");
                
                comboboxevi(ev);
                
                kef.getjTextFieldIdEvidencijeTermina().setText(ev.getIdEvidencijaTermina()+"");
                Date datum = ev.getDatumTermina();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                String datumString = sdf.format(datum);
                kef.getjTextFieldDatum().setText(datumString);
                
                int idEvidencijeTermina = ev.getIdEvidencijaTermina();
                List<StavkaEvidencijeTermina> stavke = Komunikacija.getInstance().ucitajStavkeZaET(idEvidencijeTermina);
                ModelTabeleStavkaET mtse1 = new ModelTabeleStavkaET(stavke);
                kef.getjTableStavkaEvidencijeTermina().setModel(mtse1);
                
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private boolean validacijaET(EvidencijaTermina et) {
        
        System.out.println("Validacija pokrenuta");

        if (et == null) {
            System.out.println("ET objekat je null!");
            return false;
        }
        if (et.getDatumTermina() == null) {
            System.out.println("Datum termina je null!");
            return false;
        }
        if (et.getKozmeticar() == null) {
            System.out.println("Kozmeticar je null!");
            return false;
        }
        if (et.getKlijent() == null) {
            System.out.println("Klijent je null!");
            return false;
        }
        if (et.getRadnik() == null) {
            System.out.println("Radnik je null!");
            return false;
        }

        System.out.println("Validacija prosla");
        return true;
     }

    private void popuniComboBoxeve() {
        kozmeticariCB = Komunikacija.getInstance().vratiListuSvihKozmeticara();
        kef.getjComboBoxKozmeticar().removeAllItems();
        for(Kozmeticar k: kozmeticariCB){
            kef.getjComboBoxKozmeticar().addItem(k);
        }
        
        klijentiCB = Komunikacija.getInstance().vratiListuSvihKlijenata();
        kef.getjComboBoxKlijent().removeAllItems();
        for(Klijent kl: klijentiCB){
            kef.getjComboBoxKlijent().addItem(kl);
        }
        
        radniciCB = Komunikacija.getInstance().vratiListuSvihRadnika();
        kef.getjComboBoxRadnik().removeAllItems();
        for(Radnik r: radniciCB){
            kef.getjComboBoxRadnik().addItem(r);
        }
    }
    
    private void addMouseListeners() {
        kef.getjTableStavkaEvidencijeTermina().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = kef.getjTableStavkaEvidencijeTermina().getSelectedRow();
                if(red!=-1){
                    ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
                    StavkaEvidencijeTermina stavka = mtse.getLista().get(red);
                    int rb = stavka.getRb();
                    
                    int idEv = Integer.parseInt(kef.getjTextFieldIdEvidencijeTermina().getText());
                    
                    String naziv = stavka.getNazivStavkeET();
                    int trajanje = stavka.getTrajanjeMinuti();
                    int cena = stavka.getCena();
                    
                    kef.getjTextFieldRB().setText(rb+"");
                    kef.getjTextFieldNazivStavke().setText(naziv);
                    kef.getjTextFieldTrajanje().setText(trajanje+"");
                    kef.getjTextFieldCenaStavke().setText(cena+"");
                }else{
                    JOptionPane.showMessageDialog(kef, "Odaberite red za stavku koju želite da izmenite", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            
            }
        });
    }
    
    private EvidencijaTermina vratiETpoPK(int id){
        List<EvidencijaTermina> lista = new ArrayList<>();
        lista = Komunikacija.getInstance().vratiListuSvihEvidencijaTermina();
        for(EvidencijaTermina et: lista){
            if(et.getIdEvidencijaTermina()==id){
                return et;
            }
        }return null;
    }
    
    public void resetujTabeluStavki(int idET) {
        List<StavkaEvidencijeTermina> stavke = Komunikacija.getInstance().ucitajStavkeZaET(idET);
        ModelTabeleStavkaET mtse = (ModelTabeleStavkaET) kef.getjTableStavkaEvidencijeTermina().getModel();
        mtse.setLista(stavke);
        mtse.fireTableDataChanged();
    }
    
    public Radnik vratiRadnika(EvidencijaTermina et){
        int id = et.getRadnik().getIdRadnik();
        List<Radnik> radnici = new ArrayList<>();
        for(Radnik r: radnici){
            if(id==r.getIdRadnik()){
                return r;
            }
        }
        return null;
    }
    
    private void comboboxevi(EvidencijaTermina ev) {
        String ime = ev.getKlijent().getIme();
        String prezime = ev.getKlijent().getPrezime();
        
        for(Klijent kl: klijentiCB){
            if(kl.getIme().equals(ime) && kl.getPrezime().equals(prezime)){
                System.out.println("klijent kog cu da selektujem "+kl);
                kef.getjComboBoxKlijent().setSelectedItem(kl);
            }
        }
        
        String ime1 = ev.getKozmeticar().getIme();
        String prezime1 = ev.getKozmeticar().getPrezime();
        for(Kozmeticar ko: kozmeticariCB){
            if(ko.getIme().equals(ime1) && ko.getPrezime().equals(prezime1)){
                System.out.println("kozmeticar kog cu da selektujem "+ko);
                kef.getjComboBoxKozmeticar().setSelectedItem(ko);
            }
        }
    }
}
