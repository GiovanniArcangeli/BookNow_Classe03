package BookNow.Storage;

import BookNow.Entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StrutturaDAO {

    public Struttura doRetrieveById(int id){
        if(id<=0) throw new IllegalArgumentException("id minore di 0");
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select indirizzo, Nome, username from struttura where ID_Struttura = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String indirizzo = rs.getString(1);
                String nome = rs.getString(2);
                String username = rs.getString(3);

                AlbergatoreDAO service = new AlbergatoreDAO();
                Albergatore albergatore = service.getAlbergatoreByUsername(username);

                return new Struttura(id, indirizzo, nome, albergatore);
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Struttura> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome from struttura");

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int idStruttura = rs.getInt(1);
                String indirizzo = rs.getString(2);
                String nome = rs.getString(3);

                Struttura s = new Struttura();
                s.setID_Struttura(idStruttura);
                s.setNome(nome);
                s.setIndirizzo(indirizzo);
                strutture.add(s);
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Struttura> doRetrieveByAlbergatore(Albergatore a){
        if(a==null || a.getIsAlbergatore()!=true) throw new IllegalArgumentException("Albergatore non valido");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome from struttura where username = ?");
            ps.setString(1, a.getUsername());

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int idStruttura = rs.getInt(1);
                String indirizzo = rs.getString(2);
                String nome = rs.getString(3);

                strutture.add(new Struttura(idStruttura, indirizzo, nome, a));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doUpdate(Struttura s){
        if(!doRetrieveAll().contains(s)) throw new IllegalArgumentException("Struttura non presente");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update struttura set indirizzo = ?, Nome = ? where ID_Struttura = ?");
            ps.setString(1, s.getIndirizzo());
            ps.setString(2, s.getNome());
            ps.setInt(3, s.getID_Struttura());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            AlbergatoreDAO service = new AlbergatoreDAO();
            service.updateStruttura(s);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
