/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.PretragaKlijenataForma;
import forme.model.ModelTabeleKlijent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Klijent;
import kordinator.Kordinator;

/**
 *
 * @author Admin
 */
public class PretragaKlijenataKontroler {
    private final PretragaKlijenataForma pkf;
    
    public PretragaKlijenataKontroler(PretragaKlijenataForma pkf){
        this.pkf=pkf;
        addActionListeners();
    }

    private void addActionListeners() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijent().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da pronađe klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijent().getModel();
                    Klijent k = mtk.getLista().get(red);
                    try{
                        String porukaOBrisanju = Komunikacija.getInstance().obrisiKlijenta(k);
                        JOptionPane.showMessageDialog(pkf, porukaOBrisanju, "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        vratiListuSvihKlijenata();
                    }catch(Exception e1){
                        JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKlijent().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da pronađe klijenta.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKlijent mtk = (ModelTabeleKlijent) pkf.getjTableKlijent().getModel();
                    Klijent k = mtk.getLista().get(red);
                    Kordinator.getInstance().dodajParametar("Klijent", k);
                    Kordinator.getInstance().otvoriIzmenaKlijentaFormu();
                    pkf.dispose();
                }
            }
        });
    }
    

    public void otvoriFormu() {
        vratiListuSvihKlijenata();
        pkf.setVisible(true);
        
    }

    public void vratiListuSvihKlijenata() {
        List<Klijent> lista = Komunikacija.getInstance().vratiListuSvihKlijenata();
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(lista);
        pkf.getjTableKlijent().setModel(mtk);
    }
    
    public void osveziTabeluKlijent(){
        List<Klijent> lista = Komunikacija.getInstance().vratiListuSvihKlijenata();
        ModelTabeleKlijent mtk = new ModelTabeleKlijent(lista);
        pkf.getjTableKlijent().setModel(mtk);
    }
    
}
