/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.EvidencijaTermina;
import model.StavkaEvidencijeTermina;
import komunikacija.Komunikacija;

/**
 *
 * @author Admin
 */
public class ModelTabeleEvidencijaTermina extends AbstractTableModel {
    List<EvidencijaTermina> lista = new ArrayList<>();
    String[] kolone = {"ID", "Datum termina", "Ukupna cena", "Radnik", "Kozmeticar", "Klijent"};
    
    public ModelTabeleEvidencijaTermina(){
        
    }
    
    public ModelTabeleEvidencijaTermina(List<EvidencijaTermina> lista){
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
        EvidencijaTermina e = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return e.getIdEvidencijaTermina();
            case 1:
                return formatiraj(e.getDatumTermina());
            case 2:
                return e.getUkupnaCena();
            case 3:
                return e.getRadnik();
            case 4:
                return e.getKozmeticar();
            case 5:
                return e.getKlijent();
            default:
                return "Nepoznata vrednost!";
        }
    }

    public List<EvidencijaTermina> getLista() {
        return lista;
    }

    public void setLista(List<EvidencijaTermina> lista) {
        this.lista = lista;
    }

    private Object formatiraj(Date datumTermina) {
         if (datumTermina == null) return "";
    
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return sdf.format(datumTermina);
    }
    
}
