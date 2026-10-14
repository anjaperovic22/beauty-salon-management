package operacija;

import repozitorijum.Repozitorijum;
import repozitorijum.bp.BPRepozitorijum;
import repozitorijum.bp.impl.BrokerBP;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * @author Admin
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repozitorijum broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new BrokerBP();
    }
    
    public final void izvrsi(Object objekat,Object kljuc) throws Exception{
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat,kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((BPRepozitorijum)broker).uspostaviKonekcijuSaBazom();
    }

    protected abstract void izvrsiOperaciju(Object param, Object kljuc) throws Exception;

    private void potvrdiTransakciju() throws Exception {
        ((BPRepozitorijum)broker).potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws Exception {
        ((BPRepozitorijum)broker).ponistiTransakciju();
    }

    private void ugasiKonekciju() throws Exception {
        ((BPRepozitorijum)broker).prekiniKonekcijuSaBazom();
    }
    
    
    
}
