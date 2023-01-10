package BookNow.Storage;

import BookNow.Entity.Utente;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {
    public ArrayList<Utente> getAllUsers() { //Restituisce un ArrayList di tutti gli Utenti
        ArrayList<Utente> a = new ArrayList<>();
        Statement st;
        ResultSet rs;
        Utente user;
        //prova
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Utente WHERE 1=1");
            while (rs.next()) {
                user = new Utente();
                user.setUsername(rs.getString(7));
                user.setPassword(rs.getString(6));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setEmail(rs.getString(8));
                user.setDataNascita(rs.getString(4));
                user.setRecapitoTelefonico(rs.getString(5));
                user.setCf(rs.getString(1));
                a.add(user);
            }
            con.close();
            return a;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente getUtente(String user) {
        ArrayList<Utente> utenti = (ArrayList<Utente>) getAllUsers();
        for (Utente u : utenti) {
            if (user.equalsIgnoreCase(u.getUsername()))
                return u;
        }
        return null;
    }

    public void InsertUtente(Utente user) throws RuntimeException {
        Statement st;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "Insert into Utente values('" + user.getCf() + "', '" + user.getNome() + "', '" + user.getCognome() + "', '" + user.getCognome() + "', '" + user.getDataNascita() + "', '" + user.getRecapitoTelefonico() + "', '" + user.getPassword() + "', '" + user.getUsername() + "','" + user.getEmail() + "')";
            st.executeUpdate(q);
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void UpdateUtente(String cf, String nome, String cognome, String dataDiNascita, String recapitoTelefonico, String password, String username, String email){
        Statement st;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "Update Utente set cf='"+cf+"', nome='"+nome+"', cognome='"+cognome+"', DataDiNascita='"+dataDiNascita+"', recapitoTelefonico='"+recapitoTelefonico+"', password='"+password+"', email='"+email+"' where(username='"+username+ "')";
            PreparedStatement preparedStmt = con.prepareStatement(q);
            preparedStmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
