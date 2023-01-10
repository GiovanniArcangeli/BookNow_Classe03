package BookNow.Storage;

import BookNow.Entity.Prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrenotazioneDAO {

    public List<Prenotazione> doRetrieveByCF(String CF){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("select idPrenotazione, DataIn, DataOut, NumOspiti from prenotazione where CF = ?");

            ResultSet rs = ps.executeQuery();
            List<Prenotazione> prenotazioni = new ArrayList<>();

            while(rs.next()){
               int idPrenotazione = rs.getInt(1);
               String dataInString = rs.getString(2);
               String dataOutString = rs.getString(3);
               int numOspiti = rs.getInt(4);

                DateFormat df = new SimpleDateFormat("yyyy MM dd");
                Date date = df.parse(dataInString);
                Calendar dataIn = Calendar.getInstance();
                dataIn.setTime(date);

                date = df.parse(dataOutString);
                Calendar dataOut = Calendar.getInstance();
                dataOut.setTime(date);

                prenotazioni.add(new Prenotazione(idPrenotazione, dataIn, dataOut, numOspiti));
            }
            return prenotazioni;
        }
        catch(SQLException e){
            throw new RunTimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

    public void doSave(Prenotazione p, String CF){

    }

    public void doUpdate(Prenotazione p, String CF) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("update prenotazione set DatIn = ?, DataOut = ?, NumOspiti = ? where CF = ?");
            ps.setString(p.getDataIn());
            ps.setString(p.getDataOut());
            ps.setString(CF);

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
            ps.setInt(idPrenotazione);

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("UPDATE ERROR");
        }
        catch(SQLException e){
            throw new RuntimeException("UNABLE TO CONNECT TO DATABASE");
        }
    }

}
