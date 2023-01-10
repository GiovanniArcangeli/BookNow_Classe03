package BookNow.Storage;

import BookNow.Entity.Prenotazione;

import java.sql.*;
import java.util.*;

public class PrenotazioneDAO {

    public List<Prenotazione> doRetrieveByCF(String CF){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select idPrenotazione, DataIn, DataOut, NumOspiti from prenotazione where CF = ?");

            ResultSet rs = ps.executeQuery();
            List<Prenotazione> prenotazioni = new ArrayList<>();

            while(rs.next()){
               int idPrenotazione = rs.getInt(1);
               java.util.Date dataIn = new java.util.Date(rs.getDate(2).getTime());
               java.util.Date dataOut = new java.util.Date(rs.getDate(3).getTime());
               int numOspiti = rs.getInt(4);

               prenotazioni.add(new Prenotazione(idPrenotazione, dataIn, dataOut, numOspiti));
            }
            return prenotazioni;
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doSave(Prenotazione p, String CF, int numeroStanza, int idStruttura){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into prenotazione values (?,?,?,?,?,?)");
            ps.setDate(1, new java.sql.Date(p.getDataIn().getTime()));
            ps.setDate(2, new java.sql.Date(p.getDataOut().getTime()));
            ps.setInt(3, p.getNumOspiti());
            ps.setString(4, CF);
            ps.setInt(5, numeroStanza);
            ps.setInt(6, idStruttura);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("INSERT ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doUpdate(Prenotazione p) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update prenotazione set DatIn = ?, DataOut = ?, NumOspiti = ? where idPrenotazione = ?");
            ps.setDate(1, new java.sql.Date(p.getDataIn().getTime()));
            ps.setDate(2, new java.sql.Date(p.getDataOut().getTime()));
            ps.setInt(3, p.getNumOspiti());
            ps.setInt(4, p.getID_Prenotazione());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doDelete(int idPrenotazione){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("delete from prenotazione where idPrenotazione = ?");
            ps.setInt(1, idPrenotazione);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

}
