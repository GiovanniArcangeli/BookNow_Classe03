package BookNow.Storage;

import BookNow.Entity.Utente;

import java.util.ArrayList;

public class AutenticazioneDAO {

    public Utente autenticazione(String username, String password){
        UtenteDAO u=new UtenteDAO();
        ArrayList<Utente> utenti=u.getAllUsers();
        for(Utente ut: utenti){
            if(ut.getPassword().equals(password) && ut.getUsername().equals(username))
                if(ut.isAlbergatore()==true){
                    AlbergatoreDAO a=new AlbergatoreDAO();
                   /* ArrayList<Albergatore> albergatori=a.getAllAlbergatori();
                    for(Albergatore al: albergatori){
                        if(al.getCf().equals(ut.getCf())) return al;
                    }*/
                    return a.getAlbergatoreByUsername(ut.getCf());
                }else{
                    ClienteDAO c=new ClienteDAO();
                   /* ArrayList<Cliente> clienti=c.getAllClienti();
                    for(Cliente cl: clienti){
                        if(cl.getCf().equals(ut.getCf())) return cl;
                    }*/
                    return c.getClienteByUsername(ut.getCf());
                }
        }
        return null;
    }

}
