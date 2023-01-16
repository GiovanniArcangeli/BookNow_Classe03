package BookNow.Storage;

import BookNow.Entity.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

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

    public void pubblicazionePost(Cliente autore, String titolo, String testo, String tags){
        Post post = new Post();
        post.setAutore(autore);
        post.setTitolo(titolo);
        post.setTesto(testo);
        post.setTags(tags);
        new PostDAO().doSave(post);
    }

    public Prenotazione modificaPrenotazione(int id, Date dataIn, Date dataOut, int numOspiti){
        PrenotazioneDAO service = new PrenotazioneDAO();
        Prenotazione oldOne = service.doRetrieveById(id);

        //Controllo sulla disponibilità
        if(!isStanzaDisponibile(oldOne.getStanza(), dataIn, dataOut, numOspiti))
            return null;

        oldOne.setDataIn(dataIn);
        oldOne.setDataOut(dataOut);
        oldOne.setNumOspiti(numOspiti);
        service.doUpdate(oldOne);
        return service.doRetrieveById(id);
    }

    public Prenotazione prenotazioneStanza(Cliente cliente, int ID_Struttura, int numeroStanza, Date dataIn, Date dataOut, int numOspiti){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setCliente(cliente);
        prenotazione.setDataIn(dataIn);
        prenotazione.setDataOut(dataOut);
        prenotazione.setNumOspiti(numOspiti);
        prenotazione.setStanza(new StanzaDAO().doRetrieveById(numeroStanza, ID_Struttura));

        if(!isStanzaDisponibile(prenotazione.getStanza(), prenotazione.getDataIn(), prenotazione.getDataOut(), prenotazione.getNumOspiti()))
            return null;

        int idPrenotazione = new PrenotazioneDAO().doSave(prenotazione);
        prenotazione.setID_Prenotazione(idPrenotazione);
        return prenotazione;
    }

    public ArrayList<Post> getAllPosts(){
        return (ArrayList<Post>) new PostDAO().doRetrieveAll();
    }

    public Albergatore getDatiAlbergatore(Utente utente){
        Albergatore alb = new AlbergatoreDAO().getAlbergatoreByUsername(utente.getUsername());
        ArrayList<Struttura> strutture = (ArrayList<Struttura>) new StrutturaDAO().doRetrieveByAlbergatore(alb);
        alb.setStrutture(strutture);
        return alb;
    }

    public Cliente getDatiCliente(Utente utente){
        Cliente cliente = new ClienteDAO().getClienteByUsername(utente.getUsername());
        ArrayList<Prenotazione> prenotazioni = (ArrayList<Prenotazione>) new PrenotazioneDAO().doRetrieveByCliente(cliente);
        ArrayList<Post> posts = (ArrayList<Post>) new PostDAO().doRetrieveByCliente(cliente);
        cliente.setPosts(posts);
        cliente.setPrenotazioni(prenotazioni);
        return cliente;
    }

    public Prenotazione getDatiPrenotazione(int id){ return  new PrenotazioneDAO().doRetrieveById(id); }

    public Struttura getDatiStruttura(int id){
        return new StrutturaDAO().doRetrieveById(id);
    }

    public ArrayList<Struttura> getStruttureDisponibili(Date dataIn, Date dataOut, int numOspiti){
        ArrayList<Struttura> all = (ArrayList<Struttura>) new StrutturaDAO().doRetrieveAll();
        for (Iterator<Struttura> iterator = all.iterator(); iterator.hasNext(); ) {
            Struttura struttura = iterator.next();
            struttura.setStanze((getStanzeDisponibili(struttura, dataIn, dataOut, numOspiti)));
            if (struttura.getStanze().size() == 0)
                iterator.remove();
        }
        return all;
    }

    public ArrayList<Stanza> getStanzeDisponibili(Struttura s, Date dataIn, Date dataOut, int numOspiti){
        ArrayList<Stanza> all = (ArrayList<Stanza>) new StanzaDAO().doRetrieveByStruttura(s);

        for (Iterator<Stanza> iterator = all.iterator(); iterator.hasNext(); ) {
            Stanza stanza = iterator.next();
            if (!isStanzaDisponibile(stanza, dataIn, dataOut, numOspiti))
                iterator.remove();
        }
        return all;
    }

    public boolean isStanzaDisponibile(Stanza s, Date dataIn, Date dataOut, int numOspiti){

        if(numOspiti > s.getCapienza())
            return false;

        java.util.Date DataIn = new java.util.Date(dataIn.getTime());
        java.util.Date DataOut = new java.util.Date(dataOut.getTime());

        ArrayList<Prenotazione> all = (ArrayList<Prenotazione>) new PrenotazioneDAO().doRetrieveByStanza(s);
        for(Prenotazione p : all){
            java.util.Date DI = new java.util.Date(p.getDataIn().getTime());
            java.util.Date DO = new java.util.Date(p.getDataOut().getTime());
            if(DataIn.equals(DI) || DataIn.after(DI) && DataIn.before(DO))
                return false;
            if(DataOut.equals(DO) || DataOut.after(DI) && DataOut.before(DO))
                return false;
        }
        return true;
    }
}