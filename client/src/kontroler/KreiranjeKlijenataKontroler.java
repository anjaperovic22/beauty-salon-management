/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.FormaMod;
import forme.KreiranjeKlijenataForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;
import model.Klijent;

/**
 *
 * @author Admin
 */
public class KreiranjeKlijenataKontroler {
    
    private final KreiranjeKlijenataForma kkf;
    
    public KreiranjeKlijenataKontroler(KreiranjeKlijenataForma kkf){
        this.kkf=kkf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod fm){
        kreirajFormu(fm);
        kkf.setVisible(true);
    }
    
    private void addActionListeners() {
        kkf.addBtnKreirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                String brojTelefona = kkf.getjTextFieldTelefon().getText().trim();
                
                Klijent k = new Klijent(-1, ime, prezime, brojTelefona);
                boolean flag = validacijaKlijenta(k);
                if(flag){
                    try {
                    String poruka = Komunikacija.getInstance().kreirajKlijenta(k);
                    JOptionPane.showMessageDialog(kkf, "Sistem je kreirao klijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    kkf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(kkf, "Sistem ne može da kreira klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(kkf, "Sistem ne može da kreira klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        kkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = kkf.getjTextFieldIme().getText().trim();
                String prezime = kkf.getjTextFieldPrezime().getText().trim();
                String brojTelefona = kkf.getjTextFieldTelefon().getText().trim();
                
                int id = Integer.parseInt(kkf.getjTextFieldID().getText());
                
                Klijent k = new Klijent(id, ime, prezime, brojTelefona);
                boolean flag = validacijaKlijenta(k);
                if(flag){
                    try {
                    String poruka = Komunikacija.getInstance().izmeniKlijenta(k);
                    JOptionPane.showMessageDialog(kkf, "Sistem je izmenio klijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    kkf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(kkf, "Sistem ne može da izmeni klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(kkf, "Sistem ne može da izmeni klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
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
                Klijent k = (Klijent) Kordinator.getInstance().vratiParametar("Klijent");
                kkf.getjTextFieldID().setText(k.getIdKlijent()+"");
                kkf.getjTextFieldIme().setText(k.getIme());
                kkf.getjTextFieldPrezime().setText(k.getPrezime());
                kkf.getjTextFieldTelefon().setText(k.getBrojTelefona());
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private boolean validacijaKlijenta(Klijent k) {
        if(k==null || k.getBrojTelefona().length()<12 || k.getBrojTelefona().length()>14){
            return false;
        }else if(k.getIme()==null || k.getPrezime()==null || k.getIme().equals("") || k.getPrezime().equals("")){
            return false;
        }else
            return true;
    }
    
}
