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
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select indirizzo, Nome, username from struttura where ID_Struttura = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String indirizzo = rs.getString(1);
                String nome = rs.getString(2);
                String username = rs.getString(3);

                AlbergatoreDAO service = new AlbergatoreDAO();
                Albergatore albergatore = (Albergatore) service.getAlbergatoreByUsername(username);

                return new Struttura(id, indirizzo, nome, albergatore);
            }
            return null;
        }catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Struttura> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select ID_Struttura, indirizzo, Nome, username from struttura");

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int idStruttura = rs.getInt(1);
                String indirizzo = rs.getString(2);
                String nome = rs.getString(3);
                String username = rs.getString(4);

                Albergatore albergatore = null;
                AlbergatoreDAO service = new AlbergatoreDAO();
                List<Utente> utenti = service.getAllUsers();
                for(Utente utente: utenti){
                    if(utente.getUsername().equals(username)){
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

    public List<Struttura> doRetrieveByAlbergatore(Albergatore a){
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

    public int doSave(Struttura s){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into struttura values (?,?,?)");
            ps.setString(1, s.getIndirizzo());
            ps.setString(2, s.getNome());
            ps.setString(3, s.getAlbergatore().getUsername());

            int idStruttura = -1;

            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    idStruttura = rs.getInt(1);
                    s.setID_Struttura(idStruttura);
                    AlbergatoreDAO service = new AlbergatoreDAO();
                    service.addStruttura(s);
                }
            }
            return idStruttura;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doUpdate(Struttura s){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update struttura set indirizzo = ?, Nome = ? where ID_Struttura = ?");
            ps.setString(1, s.getIndirizzo());
            ps.setString(2, s.getNome());
            ps.setInt(3, s.getID_Struttura());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            AlbergatoreDAO service = new AlbergatoreDAO();
            service.updateStruttura(s);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doDelete(Struttura s){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("delete from struttura where ID_Struttura = ?");
            ps.setInt(1, s.getID_Struttura());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("DELETE ERROR");

            AlbergatoreDAO service = new AlbergatoreDAO();
            service.removeStruttura(s);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void addStanza(Stanza s){
        s.getStruttura().addStanza(s);
    }

    public void removeStanza(Stanza s){
        s.getStruttura().deleteStanza(s);
    }

    public void updateStanza(Stanza s){
        s.getStruttura().aggiornaStanza(s);
    }
}
