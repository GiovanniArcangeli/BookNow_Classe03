package BookNow.Storage;

import BookNow.Entity.*;

import java.sql.*;

public class ClienteDAO{
    /**
     * Restituisce il Cliente, ricercandolo per Username
     * @param username l'username
     * @return Il Cliente se lo trova, null altrimenti
     * @pre username!=null
     */
    public Cliente getClienteByUsername(String username) {
        if(username == null) {
            throw new IllegalArgumentException("L'username è nullo");
        }
        Statement st;
        ResultSet rs;
        Cliente cli=null;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utente u, cliente c where(u.albergatore=0 AND u.username=c.username AND c.username='"+ username +"');");
            while (rs.next()) {
                cli = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), Date.valueOf(rs.getString(4)), rs.getString(11), Date.valueOf(rs.getString(12)), Integer.parseInt(rs.getString(13)));
            }
            con.close();
            return cli;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Aggiunge una nuova prenotazione
     * @param p la nuova prenotazione
     * @pre p!=null
     */
    public void addPrenotazione(Prenotazione p){
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        p.getCliente().addPrenotazioni(p);
    }
    /**
     * Aggiorna i dati di una prenotazione già effettuata
     * @param p la Prenotazione aggiornata
     * @pre p!=null
     */
    public void updatePrenotazione(Prenotazione p){
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        p.getCliente().aggiornaPrenotazione(p);
    }


}
