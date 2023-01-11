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

               StanzaDAO service = new StanzaDAO();
               Stanza stanza = service.doRetrieveById(numeroStanza, idStruttura);
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
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, p.getCliente().getCf());
            ps.setInt(5, p.getStanza().getStruttura().getID_Struttura());
            ps.setInt(6, p.getStanza().getNumeroStanza());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");

            ps = con.prepareStatement("select idPrenotazione from prenotazione" +
                    " where DataIn = ?, DataOut = ?, NumOspiti = ?, CF = ?, ID_Struttura = ?, NumeroStanza = ?");
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, p.getCliente().getCf());
            ps.setInt(5, p.getStanza().getStruttura().getID_Struttura());
            ps.setInt(6, p.getStanza().getNumeroStanza());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idPrenotazione = rs.getInt(1);

            p.setID_Prenotazione(idPrenotazione);
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

            ps = con.prepareStatement("select idPrenotazione from prenotazione" +
                    " where DataIn = ?, DataOut = ?, NumOspiti = ?, CF = ?, ID_Struttura = ?, NumeroStanza = ?");
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, p.getCliente().getCf());
            ps.setInt(5, p.getStanza().getStruttura().getID_Struttura());
            ps.setInt(6, p.getStanza().getNumeroStanza());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idPrenotazione = rs.getInt(1);

            p.setID_Prenotazione(idPrenotazione);
            ClienteDAO service = new ClienteDAO();
            service.updatePrenotazione(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doDelete(Prenotazione p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select idPrenotazione from prenotazione" +
                    " where DataIn = ?, DataOut = ?, NumOspiti = ?, CF = ?, ID_Struttura = ?, NumeroStanza = ?");
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, p.getCliente().getCf());
            ps.setInt(5, p.getStanza().getStruttura().getID_Struttura());
            ps.setInt(6, p.getStanza().getNumeroStanza());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int idPrenotazione = rs.getInt(1);

            p.setID_Prenotazione(idPrenotazione);
            ClienteDAO service = new ClienteDAO();
            service.addPrenotazione(p);

            ps = con.prepareStatement("delete from prenotazione where idPrenotazione = ?");
            ps.setInt(1, p.getID_Prenotazione());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

}
