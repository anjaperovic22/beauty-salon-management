package kontroler;

import forme.GlavnaForma;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;
import model.Radnik;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class LoginKontroler {
    
    private final LoginForma lf;
    
    public LoginKontroler(LoginForma lf){
        this.lf=lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String korisnickoIme = lf.getjTextFieldKorisnickoIme().getText().trim();
                String lozinka = String.valueOf(lf.getjPasswordField1().getPassword());
                try {
                    Komunikacija.getInstance().konekcija();
                } catch (Exception ex) {
                    Logger.getLogger(LoginKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
                Komunikacija.getInstance().login(korisnickoIme,lozinka);
                Radnik r = Komunikacija.getInstance().login(korisnickoIme, lozinka);
                if(r==null){
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i šifra nisu ispravni", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    Kordinator.getInstance().setUlogovani(r);
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i šifra su ispravni", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
    
}

