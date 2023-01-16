package BookNow.Storage;

import BookNow.Entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticazioneDAO {

    public Utente autenticazione(String username, String password){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from utente where username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String pass = rs.getString("Password");
                if(pass.equals(password)){
                    Utente u = new Utente();
                    u.setCf(rs.getString("CF"));
                    u.setNome(rs.getString("Nome"));
                    u.setCognome(rs.getString("Cognome"));
                    u.setDataNascita(rs.getDate("DataDiNascita"));
                    u.setRecapitoTelefonico(rs.getString("RecapitoTelefonico"));
                    u.setPassword(pass);
                    u.setUsername(username);
                    u.setEmail(rs.getString("Email"));
                    u.setIsAlbergatore(rs.getInt("Albergatore")==1);
                    return u;
                }
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
