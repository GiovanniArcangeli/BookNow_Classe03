package BookNow.Storage;

import BookNow.Entity.*;
import java.sql.Date;

public final class StorageFacade {
    private static StorageFacade instance;

    private StorageFacade(){

    }

    public static StorageFacade getInstance(){
        if(instance == null)
            instance = new StorageFacade();
        return instance;
    }

    public Utente controlloAccesso(String username, String password){
        return new AutenticazioneDAO().autenticazione(username, password);
    }

    public void modificaStruttura(int id, String indirizzo, String nome){

    }

    public void pubblicazionePost(int id, String testo, String tags){

    }

    public void modificaPrenotazione(int id, Date dataIn, Date dataOut, int numOspiti){
        Prenotazione oldOne = new PrenotazioneDAO().doRetrieveById(id);
        //Il controllo sulla disponibilità delle date è stato già fatto
        oldOne.setDataIn(dataIn);
        oldOne.setDataOut(dataOut);
        oldOne.setNumOspiti(numOspiti);

        new PrenotazioneDAO().doUpdate(oldOne);
    }

    public Prenotazione prenotazioneStanza(Cliente cliente, int ID_Struttura, int numeroStanza, Date dataIn, Date dataOut, int numOspiti){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setCliente(cliente);
        prenotazione.setDataIn(dataIn);
        prenotazione.setDataOut(dataOut);
        prenotazione.setNumOspiti(numOspiti);
        prenotazione.setStanza(new StanzaDAO().doRetrieveById(numeroStanza, ID_Struttura));

        int idPrenotazione = new PrenotazioneDAO().doSave(prenotazione);
        prenotazione.setID_Prenotazione(idPrenotazione);
        return prenotazione;
    }
}