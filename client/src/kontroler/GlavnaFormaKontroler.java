/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.GlavnaForma;
import kordinator.Kordinator;
import model.Radnik;

/**
 *
 * @author Admin
 */
public class GlavnaFormaKontroler {
    private final GlavnaForma gf;
    
    public GlavnaFormaKontroler(GlavnaForma gf){
        this.gf=gf;
        addActionListeners();
    }

    private void addActionListeners() {
        //todo
    }

    public void otvoriFormu() {
        Radnik ulogovani = Kordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme()+" "+ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getjLabelUlogovani().setText(imePrezime);
    }
}
