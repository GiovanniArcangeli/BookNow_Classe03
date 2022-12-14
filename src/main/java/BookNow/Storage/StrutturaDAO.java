package BookNow.Storage;

import BookNow.Entity.Albergatore;
import BookNow.Entity.Struttura;
import BookNow.Entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StrutturaDAO {

    public List<Struttura> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome, CF from struttura");

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int idStruttura = rs.getInt(1);
                String indirizzo = rs.getString(2);
                String nome = rs.getString(3);
                String CF = rs.getString(4);

                Albergatore albergatore = null;
                AlbergatoreDAO service = new AlbergatoreDAO();
                List<Utente> utenti = service.getAllUsers();
                for(Utente utente: utenti){
                    if(utente.getCf().equals(CF)){
                        albergatore = (Albergatore) utente;
                        break;
                    }
                }
                strutture.add(new Struttura(idStruttura, indirizzo, nome, albergatore));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Struttura> doRetrieveByCF(Albergatore a){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome from struttura where CF = ?");
            ps.setString(1, a.getCf());

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
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update struttura set indirizzo = ?, Nome = ? where CF = ?");
            ps.setString(1, s.getIndirizzo());
            ps.setString(2, s.getNome());
            ps.setString(3, s.getAlbergatore().getCf());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
