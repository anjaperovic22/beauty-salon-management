/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Smena;

/**
 *
 * @author Admin
 */
public class ModelTabeleSmena extends AbstractTableModel {
    List<Smena> lista = new ArrayList<>();
    String[] kolone = {"ID", "Naziv smene", "Broj sati", "Satnica"};
    
    public ModelTabeleSmena(){
        
    }
    
    public ModelTabeleSmena(List<Smena> lista){
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
        Smena s = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return s.getIdSmena();
            case 1:
                return s.getNazivSmena();
            case 2:
                return s.getBrojSati();
            case 3:
                return s.getSatnica();
            default:
                return "Nepoznata vrednost!";
        }
    }

    public List<Smena> getLista() {
        return lista;
    }

    public void setLista(List<Smena> lista) {
        this.lista = lista;
    }
    
}
