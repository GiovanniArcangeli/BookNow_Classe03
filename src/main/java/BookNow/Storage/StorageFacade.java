package BookNow.Storage;

import BookNow.Entity.Stanza;
import java.util.GregorianCalendar;

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

    public void modificaPrenotazione(int id, GregorianCalendar dataIn, GregorianCalendar dataOut, int numOspiti, Stanza stanza){

    }

    public void prenotazioneStanza(int id, GregorianCalendar dataIn, GregorianCalendar dataOut, int numOspiti, Stanza stanza){

    }
}