package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StanzaDAO {

    public List<Stanza> doRetrieveById(int idStruttura){
        try(Connection con = ConPool.getConncection()){
            PreparedStatement ps = con.preparedStatement("select NumeroStanza, Capienza, costo, serviziOfferti, Descrizione" +
                    " from stanza where ID_Struttura = ?");
            ps.setString(idStruttura);

            ResultSet rs = ps.executeQuery();
            List<Struttura> strutture = new ArrayList<>();

            while(rs.next()){
                int numeroStanza = rs.getInt(1);
                int capienza = rs.getInt(2);
                float costo = rs.getFloat(3);
                String serviziOfferti = rs.getString(4);
                Strint descrizione = rs.getString(5);

                strutture.add(new Stanza(numeroStanza, capienza, costo, serviziOfferti, descrizione));
            }
            return strutture;
        }
        catch(SQLException e){
            throw new RunTimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }
}
