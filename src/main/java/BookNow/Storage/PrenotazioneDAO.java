package BookNow.Storage;

import BookNow.Entity.Prenotazione;
import BookNow.Entity.Stanza;
import BookNow.Entity.Cliente;
import BookNow.Entity.Struttura;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PrenotazioneDAO {

    public List<Prenotazione> doRetrieveByCF(Cliente c){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select idPrenotazione, DataIn, DataOut, NumOspiti, ID_Struttura, NumeroStanza" +
                    " from prenotazione where CF = ?");

            ResultSet rs = ps.executeQuery();
            List<Prenotazione> prenotazioni = new ArrayList<>();

            while(rs.next()){
               int idPrenotazione = rs.getInt(1);
               Date dataIn = rs.getDate(2);
               Date dataOut = rs.getDate(3);
               int numOspiti = rs.getInt(4);
               int idStruttura = rs.getInt(5);
               int numeroStanza = rs.getInt(6);

               Stanza stanza = null;
               StanzaDAO service = new StanzaDAO();
               List<Stanza> stanze = service.doRetrieveById(new Struttura(idStruttura));
               for (Stanza s: stanze){
                   if (s.getNumeroStanza() == numeroStanza) {
                       stanza = s;
                       break;
                   }
               }
               prenotazioni.add(new Prenotazione(idPrenotazione, dataIn, dataOut, numOspiti, stanza, c));
            }
            return prenotazioni;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doSave(Prenotazione p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into prenotazione values (?,?,?,?,?,?,?)");
            ps.setInt(1, p.getID_Prenotazione());
            ps.setDate(2, p.getDataIn());
            ps.setDate(3, p.getDataOut());
            ps.setInt(4, p.getNumOspiti());
            ps.setString(5, p.getCliente().getCf());
            ps.setInt(6, p.getStanza().getStruttura().getID_Struttura());
            ps.setInt(7, p.getStanza().getNumeroStanza());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            ClienteDAO service = new ClienteDAO();
            service.addPrenotazione(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doUpdate(Prenotazione p) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update prenotazione set DatIn = ?, DataOut = ?, NumOspiti = ?, NumeroStanza" +
                    " where idPrenotazione = ?");
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setInt(4, p.getStanza().getNumeroStanza());
            ps.setInt(5, p.getID_Prenotazione());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");

            ClienteDAO service = new ClienteDAO();
            service.updatePrenotazione(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doDelete(Prenotazione p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("delete from prenotazione where idPrenotazione = ?");
            ps.setInt(1, p.getID_Prenotazione());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");

            ClienteDAO service = new ClienteDAO();
            service.removePrenotazione(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

}