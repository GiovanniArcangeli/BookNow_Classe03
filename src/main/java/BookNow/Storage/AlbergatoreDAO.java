package BookNow.Storage;

import BookNow.Entity.Albergatore;
import BookNow.Entity.Struttura;
import java.sql.*;


public class AlbergatoreDAO{

    public Albergatore getAlbergatoreByUsername(String username) {
        if(username == null) {
            throw new IllegalArgumentException("L'username è nullo");
        }
        Statement st;
        ResultSet rs;
        Albergatore alb=null;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utente u, albergatore a where(u.albergatore=1 AND u.username=a.username AND a.username='"+ username +"');");
            while (rs.next()) {
                alb = new Albergatore(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), Date.valueOf(rs.getString(4)), rs.getString(11));
            }
            con.close();
            return alb;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStruttura(Struttura s){
        if(s == null) {
            throw new IllegalArgumentException("La struttura è nulla");
        }
        s.getAlbergatore().aggiornaStrutture(s);
    }
}
