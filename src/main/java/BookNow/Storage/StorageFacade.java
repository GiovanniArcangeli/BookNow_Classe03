package BookNow.Storage;

import BookNow.Entity.*;
import java.sql.Date;
import java.util.ArrayList;

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
        Struttura oldOne = new StrutturaDAO().doRetrieveById(id);
        oldOne.setIndirizzo(indirizzo);
        oldOne.setNome(nome);

        new StrutturaDAO().doUpdate(oldOne);
    }

    public Post pubblicazionePost(Cliente autore, String titolo, String testo, String tags){
        Post post = new Post();
        post.setCliente(autore);
        post.setTitolo(titolo);
        post.setTesto(testo);
        post.setTags(tags);

        int idPost = new PostDAO().doSave(post);
        post.setID_Post(idPost);
        return post;
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

    public ArrayList<Post> getAllPosts(){
        ArrayList<Post> postList = new ArrayList<>();

    }
}