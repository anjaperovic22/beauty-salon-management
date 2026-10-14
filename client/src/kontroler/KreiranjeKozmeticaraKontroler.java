/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.FormaMod;
import forme.KreiranjeKozmeticaraForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;
import model.Kategorija;
import model.Kozmeticar;

/**
 *
 * @author Admin
 */
public class KreiranjeKozmeticaraKontroler {
    private final KreiranjeKozmeticaraForma kkf;
    private List<Kategorija> listaKategorijaZaCB = new ArrayList<>();
    
    public KreiranjeKozmeticaraKontroler(KreiranjeKozmeticaraForma kkf){
        this.kkf=kkf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod fm){
        
        vratiListuSvihKategorija();
        kreirajFormu(fm);
        kkf.setVisible(true);
    }
    
    private void addActionListeners() {
        kkf.addBtnKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                Kategorija kategorija = (Kategorija) kkf.getjComboBoxKategorija().getSelectedItem();
                
                Kozmeticar k = new Kozmeticar(-1, ime, prezime, kategorija);
                boolean flag = validacijaKozmeticara(k);
                if(flag){
                    try {
                        String poruka = Komunikacija.getInstance().kreirajKozmeticara(k);
                        JOptionPane.showMessageDialog(kkf, "Sistem je kreirao kozmetičara.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        kkf.dispose();
                    } catch (Exception ex) {
                       JOptionPane.showMessageDialog(kkf, "Sistem ne može da kreira kozmetičara.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(!flag){
                    JOptionPane.showMessageDialog(kkf, "Sistem ne može da kreira kozmetičara.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        kkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                Kategorija kategorija = (Kategorija) kkf.getjComboBoxKategorija().getSelectedItem();
                
                int id = Integer.parseInt(kkf.getjTextFieldID().getText());
                
                Kozmeticar k = new Kozmeticar(id, ime, prezime, kategorija);
                boolean flag = validacijaKozmeticara(k);
                if(flag){
                    try {
                        String poruka = Komunikacija.getInstance().izmeniKozmeticara(k);
                        JOptionPane.showMessageDialog(kkf, "Sistem je izmenio kozmetičara.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        kkf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(kkf, "Sistem ne može da izmeni kozmetičara.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
    }

    private void kreirajFormu(FormaMod fm) {
        switch(fm){
            case KREIRANJE:
                kkf.getjTextFieldID().setEnabled(false);
                kkf.getjButtonIzmeni().setVisible(false);
                kkf.getjButtonKreiraj().setVisible(true);
                kkf.getjButtonKreiraj().setEnabled(true);
                break;
            case IZMENA:
                kkf.getjTextFieldID().setEnabled(false);
                kkf.getjButtonKreiraj().setVisible(false);
                kkf.getjButtonIzmeni().setVisible(true);
                kkf.getjButtonIzmeni().setEnabled(true);
                Kozmeticar k = (Kozmeticar) Kordinator.getInstance().vratiParametar("Kozmeticar");
                kkf.getjTextFieldID().setText(k.getIdKozmeticar()+"");
                kkf.getjTextFieldIme().setText(k.getIme());
                kkf.getjTextFieldPrezime().setText(k.getPrezime());
                for(Kategorija kat: listaKategorijaZaCB){
                    if(kat.getNazivKategorija().equals(k.getKategorija().getNazivKategorija())){
                        kkf.getjComboBoxKategorija().setSelectedItem(kat);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private boolean validacijaKozmeticara(Kozmeticar k) {
        if(k==null){
            return false;
        }else if(k.getIme()==null || k.getPrezime()==null || k.getIme().equals("") || k.getPrezime().equals("") || k.getKategorija()==null){
            return false;
        }else
            return true;
    }

    private void vratiListuSvihKategorija() {
        List<Kategorija> lista = Komunikacija.getInstance().vratiListuSvihKategorija();
        for(Kategorija k: lista){
            kkf.getjComboBoxKategorija().addItem(k);
            listaKategorijaZaCB.add(k);
        }
    }
    
}
