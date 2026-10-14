/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.PrikazEvidencijaTerminaForma;
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
import model.EvidencijaTermina;
import komunikacija.Komunikacija;
import model.StavkaEvidencijeTermina;
import kordinator.Kordinator;
import model.Klijent;
import model.Kozmeticar;
import model.Radnik;

/**
 *
 * @author Admin
 */
public class PrikazEvidencijaTerminaKontroler {
    private final PrikazEvidencijaTerminaForma pef;
    private List<EvidencijaTermina> listaET = new ArrayList<>();
    
    public PrikazEvidencijaTerminaKontroler(PrikazEvidencijaTerminaForma pef){
        this.pef=pef;
        popuniComboBoxeve();
        vratiListuSvihEvidencijaTermina();
        addMouseListeners();
        addActionListeners();
    }

    public void otvoriFormu() {
        pef.setVisible(true);
    }

    private void addActionListeners() {
        pef.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pef.getjTableEvidencijaTermina().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pef, "Sistem ne moze da nađe evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleEvidencijaTermina mte = (ModelTabeleEvidencijaTermina) pef.getjTableEvidencijaTermina().getModel();
                    EvidencijaTermina et = mte.getLista().get(red);
                    List<StavkaEvidencijeTermina> stavke = Komunikacija.getInstance().ucitajStavkeZaET(et.getIdEvidencijaTermina());
                    et.setListaStavkiET(stavke);
                    try{
                        String porukaOBrisanju = Komunikacija.getInstance().obrisiEvidencijuTermina(et);
                        JOptionPane.showMessageDialog(pef, porukaOBrisanju, "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        vratiListuSvihEvidencijaTermina();
                        resetujTabeluStavkiET();
                    }catch(Exception e1){
                        JOptionPane.showMessageDialog(pef, "Sistem ne može da obriše evidenciju termina.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
                
        pef.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pef.getjTableEvidencijaTermina().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pef, "Sistem ne moze da pronađe evidenciju termina po zadatom kriterijumu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleEvidencijaTermina mte = (ModelTabeleEvidencijaTermina) pef.getjTableEvidencijaTermina().getModel();
                    EvidencijaTermina ev = mte.getLista().get(red);
                    Kordinator.getInstance().dodajParametar("Evidencija termina", ev);
                    Kordinator.getInstance().otvoriAzuriranjeEvidencijeTerminaFormu();
                    pef.dispose();
                }
            }
        }); 
        
        pef.addBtnPretrazi(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Radnik r = (Radnik) pef.getjComboBoxRadnik().getSelectedItem();
                Klijent k = (Klijent) pef.getjComboBoxKlijent().getSelectedItem();
                Kozmeticar ko=(Kozmeticar) pef.getjComboBoxKozmeticar().getSelectedItem();
                String datumStr = pef.getjTextFieldDatum().getText();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                EvidencijaTermina et = new EvidencijaTermina();
                Date datum;
                
                if(datumStr==null || datumStr.isEmpty()){
                    datum = null;
                }else{
                    try {
                        datum = sdf.parse(datumStr);
                       
                    } catch (ParseException pe) {
                        JOptionPane.showMessageDialog(pef, "Sistem ne može da pronađe evidencije termina po zadatom kriterijumu", "GREŠKA", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                
                et.setDatumTermina(datum);
                et.setRadnik(r);
                et.setKozmeticar(ko);
                et.setKlijent(k);
                
                listaET = Komunikacija.getInstance().filtrirajEvidencijuTermina(et);
                
                if(listaET!=null && !listaET.isEmpty()){
                    JOptionPane.showMessageDialog(pef, "Sistem je našao  evidencije termina po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    ModelTabeleEvidencijaTermina mte = new ModelTabeleEvidencijaTermina(listaET);
                    pef.getjTableEvidencijaTermina().setModel(mte);
                }else{
                    JOptionPane.showMessageDialog(pef, "Sistem ne može da nađe evidencije termina po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void vratiListuSvihEvidencijaTermina() {
        List<EvidencijaTermina> lista = Komunikacija.getInstance().vratiListuSvihEvidencijaTermina();
        ModelTabeleEvidencijaTermina mte = new ModelTabeleEvidencijaTermina(lista);
        pef.getjTableEvidencijaTermina().setModel(mte);
        
    }
    
    private void resetujTabeluStavkiET(){
        ModelTabeleStavkaET mtse = new ModelTabeleStavkaET();
        pef.getjTableStavkaET().setModel(mtse);
    }
    
    private void addMouseListeners() {
        pef.getjTableEvidencijaTermina().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pef.getjTableEvidencijaTermina().getSelectedRow();
                if(red!=-1){
                    ModelTabeleEvidencijaTermina mte = (ModelTabeleEvidencijaTermina) pef.getjTableEvidencijaTermina().getModel();
                    EvidencijaTermina et = mte.getLista().get(red);
                    int idEvidencijeTermina = et.getIdEvidencijaTermina();
                    List<StavkaEvidencijeTermina> stavke = Komunikacija.getInstance().ucitajStavkeZaET(idEvidencijeTermina);
                    ModelTabeleStavkaET mtse = new ModelTabeleStavkaET(stavke);
                    pef.getjTableStavkaET().setModel(mtse);
                    
                    JOptionPane.showMessageDialog(pef, "Sistem je našao  evidenciju termina.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().dodajParametar("Evidencija termina", et);
                    Kordinator.getInstance().otvoriAzuriranjeEvidencijeTerminaFormu();
                }
            
            }
        });
    }

    private void popuniComboBoxeve() {
        List<Klijent> klijenti=komunikacija.Komunikacija.getInstance().vratiListuSvihKlijenata();
        List<Kozmeticar> kozmeticari=komunikacija.Komunikacija.getInstance().vratiListuSvihKozmeticara();
        List<Radnik> radnici=komunikacija.Komunikacija.getInstance().vratiListuSvihRadnika();
        pef.getjComboBoxRadnik().addItem(null);
        pef.getjComboBoxKozmeticar().addItem(null);
        pef.getjComboBoxKlijent().addItem(null);
        for (int i = 0; i < klijenti.size(); i++) {
            pef.getjComboBoxKlijent().addItem(klijenti.get(i));
        }
        for (int i = 0; i < kozmeticari.size(); i++) {
            pef.getjComboBoxKozmeticar().addItem(kozmeticari.get(i));
        }
        for (int i = 0; i < radnici.size(); i++) {
            pef.getjComboBoxRadnik().addItem(radnici.get(i));
        }
    }
}