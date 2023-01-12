package BookNow.Storage;

import BookNow.Entity.Utente;

import java.util.ArrayList;

public class AutenticazioneDAO {

    public Utente autenticazione(String username, String password){
        UtenteDAO u=new UtenteDAO();
        ArrayList<Utente> utenti=u.getAllUsers();
        for(Utente ut: utenti){
            if(ut.getPassword().equals(password) && ut.getUsername().equals(username))
                    return ut;
                }
        return null;
    }

}
