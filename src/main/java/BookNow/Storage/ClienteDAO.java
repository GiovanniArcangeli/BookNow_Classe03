package BookNow.Storage;

import BookNow.Entity.Albergatore;
import BookNow.Entity.Cliente;
import BookNow.Entity.Utente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO extends UtenteDAO{
    public ArrayList<Cliente> getAllClienti() { //Restituisce un ArrayList di tutti i clienti
        ArrayList<Cliente> c = new ArrayList<>();
        Statement st;
        ResultSet rs;
        Cliente cli;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utente u, cliente c where(u.albergatore=0 AND u.CF=c.CF);");
            while (rs.next()) {
                cli = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), Date.valueOf(rs.getString(4)), rs.getString(11), Date.valueOf(rs.getString(12)), Integer.parseInt(rs.getString(13)));
                /*user.setUsername(rs.getString(7));
                user.setPassword(rs.getString(6));
                user.setNome(rs.getString(2));
                user.setCognome(rs.getString(3));
                user.setEmail(rs.getString(8));
                user.setDataNascita(Date.valueOf(rs.getString(4)));
                user.setRecapitoTelefonico(rs.getString(5));
                user.setCf(rs.getString(1));
                user.setAlbergatore(Boolean.getBoolean(rs.getString(9)));*/
                c.add(cli);
            }
            con.close();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente getCliente(String user) {
        ArrayList<Cliente> clienti = (ArrayList<Cliente>) getAllClienti();
        for (Cliente u : clienti) {
            if (user.equals(u.getUsername()))
                return u;
        }
        return null;
    }

    public Utente getClienteByCf(String CF) {
        /*ArrayList<Cliente> clienti = (ArrayList<Cliente>) getAllClienti();
        for (Cliente u : clienti) {
            if (CF.equals(u.getCf()))
                return u;
        }
        return null;*/
        Statement st;
        ResultSet rs;
        Cliente cli=null;
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM utente u, cliente c where(u.albergatore=0 AND u.CF=c.CF AND a.CF='"+CF+"');");
            while (rs.next()) {
                cli = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), Date.valueOf(rs.getString(4)), rs.getString(11), Date.valueOf(rs.getString(12)), Integer.parseInt(rs.getString(13)));
            }
            con.close();
            return cli;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void InsertCliente(Cliente cli) throws RuntimeException {
        Statement st;
        Utente u=new Utente(cli.getCf(), cli.getNome(), cli.getCognome(), cli.getRecapitoTelefonico(), cli.getPassword(), cli.getUsername(), cli.getEmail(), cli.getDataNascita(), cli.isAlbergatore());
        InsertUtente(u);
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "Insert into Cliente values('" + cli.getCf() + "', '" + cli.getNumero_carta() + "', '"+cli.getScadenza_carta()+"', "+cli.getCvv()+")";
            st.executeUpdate(q);
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void UpdateCliente(String cf, String nome, String cognome, String dataDiNascita, String recapitoTelefonico, String password, String username, String email, String numeroCarta, Date scadenzaCarta, int cvv){
        Statement st;
        UpdateUtente(cf, nome, cognome, dataDiNascita, recapitoTelefonico, password, username, email);
        try (Connection con = ConPool.getConnection()) {
            st = con.createStatement();
            String q = "Update Cliente set NumeroCarta='"+numeroCarta+"', ScadenzaCarta='"+scadenzaCarta+"', CVV="+cvv+" where(CF='"+cf+ "')";
            PreparedStatement preparedStmt = con.prepareStatement(q);
            preparedStmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
