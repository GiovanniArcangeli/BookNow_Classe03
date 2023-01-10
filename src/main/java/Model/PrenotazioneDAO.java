package Model;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PrenotazioneDAO {

    public ArrayList<Prenotazione> getAllPrenotazioni() { //Restituisce un ArrayList di tutte le prenotazioni
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        Statement st;
        ResultSet rs;
        Prenotazione pr;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM prenotazione WHERE 1=1");
            while (rs.next()) {
                pr = new Prenotazione();
                pr.setID_Prenotazione(Integer.parseInt(rs.getString(1)));
                pr.setDataIn(rs.getString(2));
                pr.setDataOut(rs.getString(3));
                pr.setNumOspiti(Integer.parseInt(rs.getString(4)));
                pr.setCF(rs.getString(5));
                pr.setID_Struttura(Integer.parseInt(rs.getString(6)));
                pr.setNumeroStanza(Integer.parseInt(rs.getString(7)));
                prenotazioni.add(pr);
            }
            con.close();
            return prenotazioni;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Prenotazione> getPrenotazioniByCF(String CF){
        ArrayList<Prenotazione> prenotazioni =new ArrayList<Prenotazione>();
        Statement st;
        ResultSet rs;
        Prenotazione pr;
        try (Connection con=ConPool.getConnection()){
            st=con.createStatement();
            rs=st.executeQuery("SELECT * FROM ordine WHERE CF='"+CF+"'");
            while(rs.next()){
                pr = new Prenotazione();
                pr.setID_Prenotazione(Integer.parseInt(rs.getString(1)));
                pr.setDataIn(rs.getString(2));
                pr.setDataOut(rs.getString(3));
                pr.setNumOspiti(Integer.parseInt(rs.getString(4)));
                pr.setCF(rs.getString(5));
                pr.setID_Struttura(Integer.parseInt(rs.getString(6)));
                pr.setNumeroStanza(Integer.parseInt(rs.getString(7)));
                prenotazioni.add(pr);
            }
            con.close();
            return prenotazioni;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void insertPrenotazione(Prenotazione pr) throws RuntimeException{
        Statement st;
        try (Connection con=ConPool.getConnection()){
            st=con.createStatement();
            String q="Insert into prenotazione values(default , '"+pr.getDataIn()+"', '"+pr.getDataOut()+"',"+pr.getNumOspiti()+",'"+pr.getCF()+"',"+pr.getID_Struttura()+", "+pr.getNumeroStanza()+" );";
            st.executeUpdate(q);
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Prenotazione getPrenotazioniAttive(String CF) throws RuntimeException{
        Statement st;
        ResultSet rs;
        GregorianCalendar data=new GregorianCalendar();
        Date dataOdierna = data.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Prenotazione pr=new Prenotazione();
        try (Connection con=ConPool.getConnection()){
            st=con.createStatement();
            rs=st.executeQuery("SELECT * FROM prenotazione WHERE (CF='"+CF+"'AND DataIn>'"+sdf.format(dataOdierna)+"')");
            while(rs.next()){
                pr.setID_Prenotazione(Integer.parseInt(rs.getString(1)));
                pr.setDataIn(rs.getString(2));
                pr.setDataOut(rs.getString(3));
                pr.setNumOspiti(Integer.parseInt(rs.getString(4)));
                pr.setCF(rs.getString(5));
                pr.setID_Struttura(Integer.parseInt(rs.getString(6)));
                pr.setNumeroStanza(Integer.parseInt(rs.getString(7)));
            }
            con.close();
            return pr;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void RemovePrenotazione(int idPrenotazione){
        Statement st;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "DELETE FROM prenotazione WHERE (idPrenotazione='"+idPrenotazione+"');";
            PreparedStatement preparedStmt = con.prepareStatement(q);
            preparedStmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
