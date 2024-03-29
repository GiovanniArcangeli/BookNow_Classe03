package BookNow.Storage;

import BookNow.Entity.Prenotazione;
import BookNow.Entity.Stanza;
import BookNow.Entity.Cliente;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PrenotazioneDAO {
    /**
     * Restituisce la Prenotazione, ricercandola per id
     * @param id l'id della prenotazione da ricercare
     * @return la Prenotazione se la trova, altrimenti null
     * @pre id>0
     */
    public Prenotazione doRetrieveById(int id){
        if(id<=0) throw new IllegalArgumentException("id minore di 0");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select username, DataIn, DataOut, NumOspiti, ID_Struttura, NumeroStanza" +
                    " from prenotazione where idPrenotazione = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                String username = rs.getString(1);
                Date dataIn = rs.getDate(2);
                Date dataOut = rs.getDate(3);
                int numOspiti = rs.getInt(4);
                int idStruttura = rs.getInt(5);
                int numeroStanza = rs.getInt(6);

                Stanza stanza = new StanzaDAO().doRetrieveById(numeroStanza, idStruttura);
                Cliente cliente = (Cliente) new ClienteDAO().getClienteByUsername(username);

                return new Prenotazione(id, dataIn, dataOut, numOspiti, stanza, cliente);
            }
            return null;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    /**
     * Restituisce le prenotazioni effettuate da un Cliente specifico
     * @param c il Cliente
     * @return la lista delle prenotazioni effettuate dal Cliente
     * @pre c!=null
     * @pre c.getIsAlbergatore()==false
     */
    public List<Prenotazione> doRetrieveByCliente(Cliente c){
        if(c==null || c.getIsAlbergatore()) throw new IllegalArgumentException("Cliente non valido");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select idPrenotazione, DataIn, DataOut, NumOspiti, ID_Struttura, NumeroStanza" +
                    " from prenotazione where username = ?");
            ps.setString(1, c.getUsername());

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
    /**
     * Restituisce una lista di tutte le prenotazioni effettuate su una stanza specifica
     * @param s la Stanza
     * @return la lista delle prenotazioni effettuate su quella stanza
     * @pre s!=null
     */
    public List<Prenotazione> doRetrieveByStanza(Stanza s){
        if(s==null) throw new IllegalArgumentException("Stanza non valida");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select p.idPrenotazione, p.DataIn, p.DataOut, p.NumOspiti, p.username, p.ID_Struttura, s.NumeroStanza" +
                    " from prenotazione p, stanza s, struttura st" +
                    " where s.ID_Struttura = st.ID_Struttura and p.ID_Struttura = st.ID_Struttura and s.NumeroStanza = ?");
            ps.setInt(1, s.getNumeroStanza());

            ResultSet rs = ps.executeQuery();
            List<Prenotazione> prenotazioni = new ArrayList<>();

            while(rs.next()){
                int idPrenotazione = rs.getInt(1);
                Date dataIn = rs.getDate(2);
                Date dataOut = rs.getDate(3);
                int numOspiti = rs.getInt(4);
                String username = rs.getString(5);
                int idStruttura = rs.getInt(6);
                int numeroStanza = rs.getInt(7);

                ClienteDAO service = new ClienteDAO();
                Cliente cliente = (Cliente) service.getClienteByUsername(username);
                prenotazioni.add(new Prenotazione(idPrenotazione, dataIn, dataOut, numOspiti, s, cliente));
            }
            return prenotazioni;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    /**
     * Aggiunge una nuova Prenotazione ritornandone l'id
     * @param p la Prenotazione
     * @return l'id della nuova Prenotazione
     * @pre p!=null
     */
    public int doSave(Prenotazione p){
        if(p==null) throw new IllegalArgumentException("Prenotazione non valida");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into prenotazione (DataIn, DataOut, NumOspiti, username, ID_Struttura, NumeroStanza) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, p.getDataIn());
            ps.setDate(2, p.getDataOut());
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, p.getCliente().getUsername());
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
    /**
     * Aggiorna le info di una Prenotazione già registrata
     * @param p la prenotazione aggiornata
     * @pre p!=null
     */
    public void doUpdate(Prenotazione p) {
        if(p==null) throw new IllegalArgumentException("Prenotazione non valida");
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
}
