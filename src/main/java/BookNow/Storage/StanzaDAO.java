package BookNow.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BookNow.Entity.Prenotazione;
import BookNow.Entity.Stanza;
import BookNow.Entity.Struttura;

public class StanzaDAO {
    /**
     * Restituisce la Stanza, ricercandola per id della Struttura e numero della stanza
     * @param idStruttura l'id della struttura a cui appartiene la stanza
     * @param numeroStanza il numero della stanza da ricercare
     * @return la Stanza se la trova, altrimenti null
     * @pre idStruttura>0
     * @pre numeroStanza>0
     */
    public Stanza doRetrieveById(int numeroStanza, int idStruttura){
        if(idStruttura<=0 || numeroStanza<=0) throw new IllegalArgumentException("idStruttura e/o numero di stanza minore di 0");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select Capienza, costo, serviziOfferti, Descrizione" +
                    " from stanza where ID_Struttura = ? and NumeroStanza = ?");
            ps.setInt(1, idStruttura);
            ps.setInt(2, numeroStanza);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                int capienza = rs.getInt(1);
                float costo = rs.getFloat(2);
                String serviziOfferti = rs.getString(3);
                String descrizione = rs.getString(4);
                Struttura struttura = new StrutturaDAO().doRetrieveById(idStruttura);

                return new Stanza(numeroStanza, capienza, descrizione, serviziOfferti, costo, struttura);
            }
            return null;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    /**
     * Restituisce le stanze di una struttura
     * @param s La struttura di cui si vogliono vedere le stanze
     * @return la lista delle stanze della Struttura
     * @pre s!=null
     */
    public List<Stanza> doRetrieveByStruttura(Struttura s){
        if(s==null) throw new IllegalArgumentException("Struttura non valida");
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select NumeroStanza, Capienza, costo, serviziOfferti, Descrizione" +
                    " from stanza where ID_Struttura = ?");
            ps.setInt(1, s.getID_Struttura());

            ResultSet rs = ps.executeQuery();
            List<Stanza> strutture = new ArrayList<>();

            while(rs.next()){
                int numeroStanza = rs.getInt(1);
                int capienza = rs.getInt(2);
                float costo = rs.getFloat(3);
                String serviziOfferti = rs.getString(4);
                String descrizione = rs.getString(5);

                strutture.add(new Stanza(numeroStanza, capienza, descrizione, serviziOfferti, costo, s));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
    /**
     * Aggiunge una nuova Prenotazione alla stanza
     * @param p la Prenotazione
     * @pre p!=null
     */
    public void addPrenotazione(Prenotazione p){
        if(p==null) throw new IllegalArgumentException("Prenotazione non valida");
        p.getStanza().addPrenotazioni(p);
    }

    /**
     * Aggiorna le info di una Prenotazione associata alla Stanza
     * @param p la prenotazione aggiornata
     * @pre p!=null
     */
    public void updatePrenotazione(Prenotazione p){
        if(p==null) throw new IllegalArgumentException("Prenotazione non valida");
        p.getStanza().aggiornaPrenotazione(p);
    }
}
