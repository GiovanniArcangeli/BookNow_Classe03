package BookNow.Storage;

import BookNow.Entity.Prenotazione;
import BookNow.Entity.Stanza;
import BookNow.Entity.Cliente;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PrenotazioneDAO {

    public Prenotazione doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select CF, DataIn, DataOut, NumOspiti, ID_Struttura, NumeroStanza" +
                    " from prenotazione where idPrenotazione = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                String CF = rs.getString(1);
                Date dataIn = rs.getDate(2);
                Date dataOut = rs.getDate(3);
                int numOspiti = rs.getInt(4);
                int idStruttura = rs.getInt(5);
                int numeroStanza = rs.getInt(6);

                Stanza stanza = new StanzaDAO().doRetrieveById(numeroStanza, idStruttura);
                Cliente cliente = (Cliente) new ClienteDAO().getClienteByUsername(CF);

                return new Prenotazione(id, dataIn, dataOut, numOspiti, stanza, cliente);
            }
            return null;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public List<Prenotazione> doRetrieveByCliente(Cliente c){
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

    public List<Prenotazione> doRetrieveByStanza(Stanza s){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select idPrenotazione, DataIn, DataOut, NumOspiti, CF, ID_Struttura, NumeroStanza" +
                    " from prenotazione p, stanza s, struttura S" +
                    " where s.ID_Struttura = S.ID_Struttura and p.ID_Struttura = S.ID_Struttura and s.NumeroStanza = ?");
            ps.setInt(1, s.getNumeroStanza());

            ResultSet rs = ps.executeQuery();
            List<Prenotazione> prenotazioni = new ArrayList<>();

            while(rs.next()){
                int idPrenotazione = rs.getInt(1);
                Date dataIn = rs.getDate(2);
                Date dataOut = rs.getDate(3);
                int numOspiti = rs.getInt(4);
                String CF = rs.getString(5);
                int idStruttura = rs.getInt(6);
                int numeroStanza = rs.getInt(7);

                ClienteDAO service = new ClienteDAO();
                Cliente cliente = (Cliente) service.getClienteByUsername(CF);
                prenotazioni.add(new Prenotazione(idPrenotazione, dataIn, dataOut, numOspiti, s, cliente));
            }
            return prenotazioni;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public int doSave(Prenotazione p){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into prenotazione values (?,?,?,?,?,?)");
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, p.getCliente().getCf());
            ps.setInt(5, p.getStanza().getStruttura().getID_Struttura());
            ps.setInt(6, p.getStanza().getNumeroStanza());

            int idPrenotazione = -1;   //Serve per fare i controlli nei metodi chiamanti

            if (ps.executeUpdate() == 1) {

                //Restituisce l'ID auto-generato della prenotazione salvata
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    idPrenotazione = rs.getInt(1);
                    p.setID_Prenotazione(idPrenotazione);
                    ClienteDAO sc = new ClienteDAO();
                    sc.addPrenotazione(p);
                    StanzaDAO ss = new StanzaDAO();
                    ss.addPrenotazione(p);
                }
            }
            return idPrenotazione;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doUpdate(Prenotazione p) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update prenotazione set DataIn = ?, DataOut = ?, NumOspiti = ?" +
                    " where idPrenotazione = ?");
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setInt(4, p.getID_Prenotazione());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");

            ClienteDAO service = new ClienteDAO();
            service.updatePrenotazione(p);
            StanzaDAO ss = new StanzaDAO();
            ss.updatePrenotazione(p);
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
            StanzaDAO ss = new StanzaDAO();
            ss.removePrenotazione(p);
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

}
