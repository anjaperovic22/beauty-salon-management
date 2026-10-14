package repozitorijum;

import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Admin
 */
public interface Repozitorijum<T> {
    List<T> ucitajSve(T param,String uslov) throws Exception;
    int dodaj(T param) throws Exception;
    void izmeni(T param) throws Exception;    
    void obrisi(T param) throws Exception;
    List<T> ucitajSve();
    int vratiKljuc(T param) throws Exception;
}
