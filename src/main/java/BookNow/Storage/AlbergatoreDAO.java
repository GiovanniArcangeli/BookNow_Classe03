package BookNow.Storage;

import BookNow.Entity.Albergatore;
import BookNow.Entity.Prenotazione;
import BookNow.Entity.Struttura;
import BookNow.Entity.Utente;

import java.sql.*;
import java.util.ArrayList;

public class AlbergatoreDAO extends UtenteDAO{
    public ArrayList<Albergatore> getAllAlbergatori() { //Restituisce un ArrayList di tutti gli Albergatori
        ArrayList<Albergatore> a = new ArrayList<>();
        Statement st;
        ResultSet rs;
        Albergatore alb;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utente u, albergatore a where(u.albergatore=1 AND u.CF=a.CF);");
            while (rs.next()) {
                alb = new Albergatore(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), Date.valueOf(rs.getString(4)), rs.getString(11));
                /*user.setUsername(rs.getString(7));
                user.setPassword(rs.getString(6));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setEmail(rs.getString(8));
                user.setDataNascita(Date.valueOf(rs.getString(4)));
                user.setRecapitoTelefonico(rs.getString(5));
                user.setCf(rs.getString(1));
                user.setAlbergatore(Boolean.getBoolean(rs.getString(9)));*/
                a.add(alb);
            }
            con.close();
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente getAlbergatore(String user) {
        ArrayList<Albergatore> albergatori = (ArrayList<Albergatore>) getAllAlbergatori();
        for (Albergatore u : albergatori) {
            if (user.equals(u.getUsername()))
                return u;
        }
        return null;
    }

    public Utente getAlbergatoreByCF(String CF) {
        /*ArrayList<Albergatore> albergatori = (ArrayList<Albergatore>) getAllAlbergatori();
        for (Albergatore u : albergatori) {
            if (CF.equals(u.getCf()))
                return u;
        }
        return null;*/
        Statement st;
        ResultSet rs;
        Albergatore alb=null;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utente u, albergatore a where(u.albergatore=1 AND u.CF=a.CF AND a.CF='"+CF+"');");
            while (rs.next()) {
                alb = new Albergatore(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), Date.valueOf(rs.getString(4)), rs.getString(11));
            }
            con.close();
            return alb;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void InsertAlbergatore(Albergatore alb) throws RuntimeException {
        Statement st;
        Utente u=new Utente(alb.getCf(), alb.getNome(), alb.getCognome(), alb.getRecapitoTelefonico(), alb.getPassword(), alb.getUsername(), alb.getEmail(), alb.getDataNascita(), alb.isAlbergatore());
        InsertUtente(u);
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "Insert into Albergatore values('" + alb.getCf() + "', '" + alb.getP_iva() + "')";
            st.executeUpdate(q);
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void UpdateAlbergatore(String cf, String nome, String cognome, String dataDiNascita, String recapitoTelefonico, String password, String username, String email, String p_iva){
        Statement st;
        UpdateUtente(cf, nome, cognome, dataDiNascita, recapitoTelefonico, password, username, email);
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "Update Albergatore set P_IVA='"+p_iva+"' where(CF='"+cf+ "')";
            PreparedStatement preparedStmt = con.prepareStatement(q);
            preparedStmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addStruttura(Struttura s){
        s.getAlbergatore().addStrutture(s);
    }

    public void removeStruttura(Struttura s){
       s.getAlbergatore().deleteStruttura(s);
    }

    public void updateStruttura(Struttura s){
        s.getAlbergatore().aggiornaStrutture(s);
    }
}
