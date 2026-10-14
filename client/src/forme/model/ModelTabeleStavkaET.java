/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaEvidencijeTermina;

/**
 *
 * @author Admin
 */
public class ModelTabeleStavkaET extends AbstractTableModel {
    List<StavkaEvidencijeTermina> lista = new ArrayList<>();
    String[] kolone = {"Redni broj", "Naziv", "Trajanje (minuti)", "Cena"};
    private int trenutniRb = -1;
    
    public ModelTabeleStavkaET(){
        
    }
    
    public ModelTabeleStavkaET(List<StavkaEvidencijeTermina> lista){
        this.lista=lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaEvidencijeTermina s = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return s.getRb();
            case 1:
                return s.getNazivStavkeET();
            case 2:
                return s.getTrajanjeMinuti();
            case 3:
                return s.getCena();
            default:
                return "Nepoznata vrednost!";
        }
    }

    public List<StavkaEvidencijeTermina> getLista() {
        return lista;
    }

    public void setLista(List<StavkaEvidencijeTermina> lista) {
        this.lista = lista;
    }

    public void dodajStavku(StavkaEvidencijeTermina s) {
        int rb = lista.size()+1;
        s.setRb(rb);
        trenutniRb=rb;
        lista.add(s);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaEvidencijeTermina s) {
        lista.remove(s);
        fireTableDataChanged();

    }

    public int getTrenutniRb() {
        return trenutniRb;
    }

    public void setTrenutniRb(int trenutniRb) {
        this.trenutniRb = trenutniRb;
    }
    
    

}
