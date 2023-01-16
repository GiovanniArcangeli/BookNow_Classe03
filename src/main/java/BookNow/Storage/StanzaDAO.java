package BookNow.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import BookNow.Entity.Prenotazione;
import BookNow.Entity.Stanza;
import BookNow.Entity.Struttura;

public class StanzaDAO {

    public Stanza doRetrieveById(int numeroStanza, int idStruttura){
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

    public List<Stanza> doRetrieveByStruttura(Struttura s){
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

    public void addPrenotazione(Prenotazione p){
        p.getStanza().addPrenotazioni(p);
    }
    public void updatePrenotazione(Prenotazione p){
        p.getStanza().aggiornaPrenotazione(p);
    }
}
