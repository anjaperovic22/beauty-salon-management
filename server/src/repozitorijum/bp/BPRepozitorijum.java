/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repozitorijum.bp;

import repozitorijum.Repozitorijum;

/**
 *
 * @author Admin
 */
public interface BPRepozitorijum<T> extends Repozitorijum<T> {
    //sve metode nad bp
    
    default public void uspostaviKonekcijuSaBazom() throws Exception{
       BPKonekcijaFactory.getInstance().getConnection();
    }
    
    default public void prekiniKonekcijuSaBazom() throws Exception{
       BPKonekcijaFactory.getInstance().getConnection().close();
    }
    
    default public void potvrdiTransakciju() throws Exception{
       BPKonekcijaFactory.getInstance().getConnection().commit();
    }
    
    default public void ponistiTransakciju() throws Exception{
       BPKonekcijaFactory.getInstance().getConnection().rollback();
    }
}
