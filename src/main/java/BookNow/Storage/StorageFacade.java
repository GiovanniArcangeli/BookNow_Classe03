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

    public boolean controlloAccesso(String username, String password){
        return false;
    }

    public void modificaStruttura(int id, String indirizzo, String nome){

    }

    public void pubblicazionePost(int id, String testo, String tags){

    }

    public void modificaPrenotazione(int id, Date dataIn, Date dataOut, int numOspiti, Stanza stanza){

    }

    public void prenotazioneStanza(Cliente cliente, int ID_Struttura, int numeroStanza, Date dataIn, Date dataOut, int numOspiti){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setCliente(cliente);
        prenotazione.setDataIn(dataIn);
        prenotazione.setDataOut(dataOut);
        prenotazione.setNumOspiti(numOspiti);
        prenotazione.setStanza(new StanzaDAO().doRetrieveById(numeroStanza, ID_Struttura));

        new PrenotazioneDAO().doSave(prenotazione);

    }
}