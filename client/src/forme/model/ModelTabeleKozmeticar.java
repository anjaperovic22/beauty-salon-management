/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import forme.PrikazKozmeticaraForma;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kozmeticar;

/**
 *
 * @author Admin
 */
public class ModelTabeleKozmeticar extends AbstractTableModel {

    List<Kozmeticar> lista = new ArrayList<>();
    List<Kozmeticar> filtrirani = new ArrayList<>();
    List<Kozmeticar> svi = new ArrayList<>();
    String[] kolone = {"ID", "Ime", "Prezime", "Naziv kategorije", "Opis kategorije"};
    
    public ModelTabeleKozmeticar(){
        
    }
    
    public ModelTabeleKozmeticar(List<Kozmeticar> lista){
        this.lista=lista;
        this.svi=lista;
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
        Kozmeticar k = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return k.getIdKozmeticar();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getKategorija().getNazivKategorija();
            case 4:
                return k.getKategorija().getOpis().substring(0, 20) +"...";
            default:
                return "Nepoznata vrednost!";
        }
    }

    public List<Kozmeticar> getLista() {
        return lista;
    }

    public void setLista(List<Kozmeticar> lista) {
        this.lista = lista;
    }
    
    public void pretrazi(String ime, String prezime, String naziv){
        filtrirani.clear();
        boolean praznoIme = (ime == null || ime.isEmpty());
        boolean praznoPrezime = (prezime == null || prezime.isEmpty());
        boolean prazanNaziv = (naziv == null || naziv.isEmpty());

        if (praznoIme && praznoPrezime && prazanNaziv) {
            filtrirani.addAll(svi);
            prikaziSamoFiltrirane();
            return;
        }

        for (Kozmeticar k : svi) {
            boolean match = true;

            if (!praznoIme && (k.getIme() == null ||
                !k.getIme().toLowerCase().contains(ime.toLowerCase()))) {
                match = false;
            }
            if (!praznoPrezime && (k.getPrezime() == null ||
                !k.getPrezime().toLowerCase().contains(prezime.toLowerCase()))) {
                match = false;
            }
            if (!prazanNaziv && (k.getKategorija() == null ||
                k.getKategorija().getNazivKategorija() == null ||
                !k.getKategorija().getNazivKategorija().equalsIgnoreCase(naziv))) {
                match = false;
            }

            if (match) filtrirani.add(k);
        }
        prikaziSamoFiltrirane();
    }

    public void prikaziSamoFiltrirane() {
        this.lista=filtrirani;
        fireTableDataChanged();
    }
    
    
    
}
