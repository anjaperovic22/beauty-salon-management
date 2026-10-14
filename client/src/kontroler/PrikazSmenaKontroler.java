/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.PrikazSmenaForma;
import forme.model.ModelTabeleSmena;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.Smena;
import komunikacija.Komunikacija;

/**
 *
 * @author Admin
 */
public class PrikazSmenaKontroler {
    private final PrikazSmenaForma psf;
    
    public PrikazSmenaKontroler(PrikazSmenaForma psf){
        this.psf=psf;
        addActionListeners();
        vratiListuSvihSmena();
    }

    public void otvoriFormu() {
        psf.setVisible(true);
    }

    private void addActionListeners() {
        psf.addBtnUbaciActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazivSmene = psf.getjTextFieldNazivSmene().getText().trim();
                int brojSati = -1, satnica = -1;
                try{
                    brojSati = Integer.parseInt(psf.getjTextFieldBrojSati().getText());
                    satnica = Integer.parseInt(psf.getjTextFieldSatnica().getText());
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da kreira smenu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
                Smena s = new Smena(-1, nazivSmene, brojSati, satnica);
                boolean flag = validacijaSmene(s);
                if(flag){
                    try {
                        String poruka = Komunikacija.getInstance().ubaciSmenu(s);
                        JOptionPane.showMessageDialog(psf, "Sistem je zapamtio smenu.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        vratiListuSvihSmena();
                    } catch (Exception ex) {
                       JOptionPane.showMessageDialog(psf, "Sistem ne može da zapamti smenu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(!flag){
                    JOptionPane.showMessageDialog(psf, "Sistem ne može da zapamti smenu.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                }
            }

            private boolean validacijaSmene(Smena s) {
                if(s.getNazivSmena()==null || s.getNazivSmena().isEmpty() || s.getBrojSati()<=0 || s.getSatnica()<=0)
                    return false;
                return true;
            }
        });
    }
    
    public void vratiListuSvihSmena(){
        List<Smena> lista = Komunikacija.getInstance().vratiListuSvihSmena();
        ModelTabeleSmena mts = new ModelTabeleSmena(lista);
        psf.getjTableSmena().setModel(mts);
    }
    
}
