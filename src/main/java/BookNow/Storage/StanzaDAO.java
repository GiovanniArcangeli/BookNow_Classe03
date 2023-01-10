package BookNow.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import BookNow.Entity.Stanza;

public class StanzaDAO {

    public List<Stanza> doRetrieveById(int idStruttura){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select NumeroStanza, Capienza, costo, serviziOfferti, Descrizione" +
                    " from stanza where ID_Struttura = ?");
            ps.setInt(1, idStruttura);

            ResultSet rs = ps.executeQuery();
            List<Stanza> strutture = new ArrayList<>();

            while(rs.next()){
                int numeroStanza = rs.getInt(1);
                int capienza = rs.getInt(2);
                float costo = rs.getFloat(3);
                String serviziOfferti = rs.getString(4);
                String descrizione = rs.getString(5);

                strutture.add(new Stanza(numeroStanza, capienza, descrizione, serviziOfferti, costo));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
