/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.bp.impl;

import java.util.ArrayList;
import java.util.List;
import model.ApstraktniDomenskiObjekat;
import repozitorijum.bp.BPRepozitorijum;
import repozitorijum.bp.BPKonekcijaFactory;
import java.sql.*;


/**
 *
 * @author Admin
 */
public class BrokerBP implements BPRepozitorijum<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> ucitajSve(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM "+param.vratiNazivTabele();
        if(uslov!=null && !uslov.equals("")){
            upit+=uslov;
        }
        System.out.println(upit);
        Statement st = BPKonekcijaFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public int dodaj(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele()
                + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES ("
                + param.vratiVrednostiZaUbacivanje() + ")";

        System.out.println(upit);

        int pk = -1;

        try ( PreparedStatement pst = BPKonekcijaFactory.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS)) {

            pst.executeUpdate();
            try ( ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    pk = rs.getInt(1);
                }
            }
        }

        return pk;
    }

    @Override
    public void izmeni(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " +  param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = BPKonekcijaFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();    
    }

    @Override
    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() +" WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = BPKonekcijaFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> ucitajSve() {
            List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        try {
            @SuppressWarnings("unchecked")
            ApstraktniDomenskiObjekat param = 
                (ApstraktniDomenskiObjekat) this.getClass()
                    .getGenericSuperclass()
                    .getClass()
                    .getDeclaredConstructor()
                    .newInstance();

            String upit = "SELECT * FROM " + param.vratiNazivTabele();
            Statement st = BPKonekcijaFactory.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            lista = param.vratiListu(rs);

            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    @Override
    public int vratiKljuc(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() +
                      " (" + param.vratiKoloneZaUbacivanje() + ") VALUES (" +
                      param.vratiVrednostiZaUbacivanje() + ")";
        System.out.println(upit);
        PreparedStatement ps = BPKonekcijaFactory.getInstance()
                .getConnection()
                .prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int generatedId = -1;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

        rs.close();
        ps.close();

        return generatedId;
    }


    
   
    
}
