/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Klijent;

/**
 *
 * @author Admin
 */
public class ModelTabeleKlijent extends AbstractTableModel {
    List<Klijent> lista;
    String[] kolone = {"ID", "Ime", "Prezime", "Kontakt broj telefona"};
    
    public ModelTabeleKlijent(){
        
    }
    
    public ModelTabeleKlijent(List<Klijent> lista){
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
        Klijent k = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return k.getIdKlijent();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getBrojTelefona();
            default:
                return "Nepoznata vrednost!";
        }
    }

    public List<Klijent> getLista() {
        return lista;
    }

    public void setLista(List<Klijent> lista) {
        this.lista = lista;
    }
    
    
    
}
