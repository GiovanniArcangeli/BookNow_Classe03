package BookNow.Storage;

import BookNow.Entity.Struttura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StrutturaDAO {

    public List<Struttura> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome from struttura");

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int idStruttura = rs.getInt(1);
                String indirizzo = rs.getString(2);
                String nome = rs.getString(3);

                strutture.add(new Struttura(idStruttura, indirizzo, nome));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Struttura> doRetrieveByCF(String CF){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome from struttura where CF = ?");
            ps.setString(1,CF);

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int idStruttura = rs.getInt(1);
                String indirizzo = rs.getString(2);
                String nome = rs.getString(3);

                strutture.add(new Struttura(idStruttura, indirizzo, nome));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    public void doUpdate(Struttura s, String CF){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update struttura set indirizzo = ?, Nome = ? where CF = ?");
            ps.setString(1, s.getIndirizzo());
            ps.setString(2, s.getNome());
            ps.setString(3, CF);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
