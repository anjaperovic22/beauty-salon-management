/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.PrikazKozmeticaraForma;
import forme.model.ModelTabeleKozmeticar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kategorija;
import model.Kozmeticar;
import kordinator.Kordinator;

/**
 *
 * @author Admin
 */
public class PrikazKozmeticaraKontroler {
    private final PrikazKozmeticaraForma pkf;
    
    public PrikazKozmeticaraKontroler(PrikazKozmeticaraForma pkf){
        this.pkf=pkf;
        vratiListuSvihKozmeticara();
        vratiListuSvihKategorija();
        addActionListeners();
        addMouseListeners();
    }

    public void otvoriFormu() {
        pkf.setVisible(true);
    }

    private void addActionListeners() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKozmeticar().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da pronađe kozmetičara.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKozmeticar mtk = (ModelTabeleKozmeticar) pkf.getjTableKozmeticar().getModel();
                    Kozmeticar k = mtk.getLista().get(red);
                    try{
                        String porukaOBrisanju = Komunikacija.getInstance().obrisiKozmeticara(k);
                        JOptionPane.showMessageDialog(pkf, porukaOBrisanju, "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        vratiListuSvihKozmeticara();
                    }catch(Exception e1){
                        JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše kozmetičara.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        });
        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKozmeticar().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da pronađe kozmetičara.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKozmeticar mtk = (ModelTabeleKozmeticar) pkf.getjTableKozmeticar().getModel();
                    Kozmeticar k = mtk.getLista().get(red);
                    Kordinator.getInstance().dodajParametar("Kozmeticar", k);
                    Kordinator.getInstance().otvoriIzmenaKozmeticaraFormu();
                    pkf.dispose();
                }
            }
        }); 
        
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime="", prezime="";
                Kategorija k = new Kategorija();
                if(pkf.getjTextFieldIme()!=null && !pkf.getjTextFieldIme().getText().isEmpty()){
                    ime = pkf.getjTextFieldIme().getText().trim();
                }
                if(pkf.getjTextFieldPrezime()!=null && !pkf.getjTextFieldPrezime().getText().isEmpty()){
                    prezime = pkf.getjTextFieldPrezime().getText().trim();
                }
                k = (Kategorija) pkf.getjComboBoxKategorija().getSelectedItem();
                System.out.println("Ime: "+ime + " Prezime: "+prezime + " Kategorija " + k.getNazivKategorija());
                
                ModelTabeleKozmeticar mtk=(ModelTabeleKozmeticar) pkf.getjTableKozmeticar().getModel();
                mtk.pretrazi(ime, prezime, k.getNazivKategorija());
                
                if(mtk.getLista().isEmpty()){
                    JOptionPane.showMessageDialog(pkf, "Sistem ne moze da pronađe kozmetičara po zadatim kriterijumima.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        pkf.addBtnResetActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vratiListuSvihKozmeticara();
                pkf.getjTextFieldIme().setText("");
                pkf.getjTextFieldPrezime().setText("");
                pkf.getjComboBoxKategorija().setSelectedIndex(0);
            }
        });
    }
    
    public void vratiListuSvihKozmeticara(){
        List<Kozmeticar> lista = Komunikacija.getInstance().vratiListuSvihKozmeticara();
        ModelTabeleKozmeticar mtk = new ModelTabeleKozmeticar(lista);
        pkf.getjTableKozmeticar().setModel(mtk);
    }

    private void vratiListuSvihKategorija() {
        List<Kategorija> lista = Komunikacija.getInstance().vratiListuSvihKategorija();
        for(Kategorija k: lista){
            pkf.getjComboBoxKategorija().addItem(k);
        }
    }
    
    private void addMouseListeners() {
        pkf.getjTableKozmeticar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pkf.getjTableKozmeticar().getSelectedRow();
                if(red!=-1){
                    System.out.println("Sistem je pronašao kozmetičara.");
                    ModelTabeleKozmeticar mte = (ModelTabeleKozmeticar) pkf.getjTableKozmeticar().getModel();
                    Kozmeticar k = mte.getLista().get(red);
                    int idK = k.getIdKozmeticar();
                    
                    JOptionPane.showMessageDialog(pkf, "Sistem je našao kozmetičara", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    String podaci = k.getIme()+" "+k.getPrezime()+"\n"+k.getKategorija();
                    JOptionPane.showMessageDialog(pkf, podaci, "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    
}
