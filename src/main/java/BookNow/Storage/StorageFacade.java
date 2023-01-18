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

    /**
     * Modifica i dati di una prenotazione effettuata
     * @param username username dell'utente
     * @param password password dell'utente
     * @return l'utente con le credenziali corrispondenti, null se non esiste
     * @pre username != null
     * @pre password!= null
     */
    public Utente controlloAccesso(String username, String password){
        if(username == null || password == null){
            throw new IllegalArgumentException("Parametri errati");
        }
        return new AutenticazioneDAO().autenticazione(username, password);
    }

    /**
     * Modifica i dati di una struttura nel sistema
     * @param id id della struttura da modificare
     * @param indirizzo nuovo indirizzo della struttura
     * @param nome nuovo nome della struttura
     * @pre id > 0
     * @pre StrutturaDAO.doRetrieveById(id) != null
     */
    public void modificaStruttura(int id, String indirizzo, String nome){
        if(id<= 0 || new StrutturaDAO().doRetrieveById(id) == null){
            throw new IllegalArgumentException("ID Negativo");
        }
        Struttura oldOne = new StrutturaDAO().doRetrieveById(id);
        oldOne.setIndirizzo(indirizzo);
        oldOne.setNome(nome);

        new StrutturaDAO().doUpdate(oldOne);
    }

    /**
     * Pubblica un post sul forum
     * @param autore l'autore del post
     * @param titolo il titolo del post
     * @param testo il testo del post
     * @param tags i tag del post
     */
    public void pubblicazionePost(Cliente autore, String titolo, String testo, String tags){
        Post post = new Post();
        post.setAutore(autore);
        post.setTitolo(titolo);
        post.setTesto(testo);
        post.setTags(tags);
        new PostDAO().doSave(post);
    }

    /**
     * Modifica i dati di una prenotazione effettuata
     * @param id identificativo della prenotazione
     * @param dataIn nuova data di check-in
     * @param dataOut nuova data di check-out
     * @param numOspiti numero aggiornato degli ospiti
     * @return la prenotazione con i dati aggiornati (id, cliente e stanza invariati)
     * @pre id > 0
     * @pre PrenotazioneDAO.doRetrieveById(id) != null
     */
    public Prenotazione modificaPrenotazione(int id, Date dataIn, Date dataOut, int numOspiti){
        if(id <= 0 || new PrenotazioneDAO().doRetrieveById(id) == null){
            throw new IllegalArgumentException("Parametri errati");
        }
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

    /**
     * Effettua una nuova prenotazione
     * @param cliente il Cliente che effettua la prenotazione
     * @param ID_Struttura identificativo della struttura
     * @param numeroStanza identificativo della stanza
     * @param dataIn data di check-in
     * @param dataOut data di check-out
     * @param numOspiti numero degli ospiti
     * @return la prenotazione appena effettuata, null se non è stata effettuata.
     * @pre id > 0
     * @pre dataIn > Date(System.currentTimeMillis())
     * @pre dataOut > dataIn
     * @pre numOspiti > 0
     */
    public Prenotazione prenotazioneStanza(Cliente cliente, int ID_Struttura, int numeroStanza, Date dataIn, Date dataOut, int numOspiti){
        if(dataIn.before(new Date(System.currentTimeMillis())) ||dataIn.after(dataOut) || numOspiti <= 0){
            throw new IllegalArgumentException("Parametri errati");
        }
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

    /**
     * Cerca tutti i post pubblicati nel sistema
     * @return la lista di tutti i post
     */
    public ArrayList<Post> getAllPosts(){
        return (ArrayList<Post>) new PostDAO().doRetrieveAll();
    }

    /**
     * Trova l'Albergatore corrispondente all'utente
     * @param utente Utente da trasformare in Albergatore
     * @return l'Albergatore corrispondente
     * @pre utente != null
     * @pre utente.getIsAlbergatore()
     */
    public Albergatore getDatiAlbergatore(Utente utente){
        if(utente == null || !utente.getIsAlbergatore()){
            throw new IllegalArgumentException("Utente non è un albergatore");
        }
        Albergatore alb = new AlbergatoreDAO().getAlbergatoreByUsername(utente.getUsername());
        ArrayList<Struttura> strutture = (ArrayList<Struttura>) new StrutturaDAO().doRetrieveByAlbergatore(alb);
        alb.setStrutture(strutture);
        return alb;
    }

    /**
     * Trova il Cliente corrispondente all'utente
     * @param utente Utente da trasformare in Cliente
     * @return il Cliente corrispondente
     * @pre utente != null
     * @pre !utente.getIsAlbergatore()
     */
    public Cliente getDatiCliente(Utente utente){
        if(utente == null || utente.getIsAlbergatore()){
            throw new IllegalArgumentException("Utente non è un cliente");
        }
        Cliente cliente = new ClienteDAO().getClienteByUsername(utente.getUsername());
        ArrayList<Prenotazione> prenotazioni = (ArrayList<Prenotazione>) new PrenotazioneDAO().doRetrieveByCliente(cliente);
        ArrayList<Post> posts = (ArrayList<Post>) new PostDAO().doRetrieveByCliente(cliente);
        cliente.setPosts(posts);
        cliente.setPrenotazioni(prenotazioni);
        return cliente;
    }

    /**
     * Trova i dati di una Prenotazione partendo dall'id
     * @param id id della prenotazione da cercare
     * @return la Prenotazione corrispondante
     * @pre id > 0
     * @pre PrenotazioneDAO.doRetrieveById(id) != null
     */
    public Prenotazione getDatiPrenotazione(int id){
        if(id<= 0 || new PrenotazioneDAO().doRetrieveById(id) == null){
            throw new IllegalArgumentException("ID Negativo");
        }
        return  new PrenotazioneDAO().doRetrieveById(id);
    }

    /**
     * Trova i dati di una Struttura partendo dall'id
     * @param id id della struttura da cercare
     * @return la Struttura corrispondante
     * @pre id > 0
     * @pre StrutturaDAO.doRetrieveById(id) != null
     */
    public Struttura getDatiStruttura(int id){
        if(id<= 0 || new StrutturaDAO().doRetrieveById(id) == null){
            throw new IllegalArgumentException("ID Negativo");
        }
        return new StrutturaDAO().doRetrieveById(id);
    }

    /**
     * Verifica quali sono le strutture disponibili nei giorni specificati
     * @param dataIn data del check-in
     * @param dataOut data del check-out
     * @param numOspiti numero di ospiti
     * @return la lista di strutture disponibili
     * @pre dataIn > Date(System.currentTimeMillis()
     * @pre dataOut > dataIn
     * @pre numOspiti > 0
     */
    public ArrayList<Struttura> getStruttureDisponibili(Date dataIn, Date dataOut, int numOspiti){
        if(dataIn.before(new Date(System.currentTimeMillis())) || dataIn.after(dataOut) || numOspiti <= 0){
            throw new IllegalArgumentException("Parametri errati");
        }
        ArrayList<Struttura> all = (ArrayList<Struttura>) new StrutturaDAO().doRetrieveAll();
        for (Iterator<Struttura> iterator = all.iterator(); iterator.hasNext(); ) {
            Struttura struttura = iterator.next();
            struttura.setStanze((getStanzeDisponibili(struttura, dataIn, dataOut, numOspiti)));
            if (struttura.getStanze().size() == 0)
                iterator.remove();
        }
        return all;
    }
    /**
     * Verifica quali sono le stanza disponibili nei giorni specificati
     * @param s struttura dove cercare le stanze
     * @param dataIn data del check-in
     * @param dataOut data del check-out
     * @param numOspiti numero di ospiti
     * @return la lista delle stanze disponibili
     * @pre dataIn > Date(System.currentTimeMillis())
     * @pre dataOut > dataIn
     * @pre numOspiti > 0
     * @pre s!=null
     */
    public ArrayList<Stanza> getStanzeDisponibili(Struttura s, Date dataIn, Date dataOut, int numOspiti){
        if(dataIn.before(new Date(System.currentTimeMillis())) ||dataIn.after(dataOut) || numOspiti <= 0 || s==null){
            throw new IllegalArgumentException("Parametri errati");
        }
        ArrayList<Stanza> all = (ArrayList<Stanza>) new StanzaDAO().doRetrieveByStruttura(s);

        for (Iterator<Stanza> iterator = all.iterator(); iterator.hasNext(); ) {
            Stanza stanza = iterator.next();
            if (!isStanzaDisponibile(stanza, dataIn, dataOut, numOspiti))
                iterator.remove();
        }
        return all;
    }

    /**
     * Verifica se la stanza è disponibile nei giorni specificati
     * @param s stanza
     * @param dataIn data del check-in
     * @param dataOut data del check-out
     * @param numOspiti numero di ospiti
     * @return true se disponibile, false altrimenti
     * @pre dataIn > Date(System.currentTimeMillis())
     * @pre dataOut > dataIn
     * @pre numOspiti > 0
     * @pre s!=null
     */
    public boolean isStanzaDisponibile(Stanza s, Date dataIn, Date dataOut, int numOspiti){
        if(dataIn.before(new Date(System.currentTimeMillis())) ||dataIn.after(dataOut) || numOspiti <= 0 || s==null){
            throw new IllegalArgumentException("Parametri errati");
        }

        return isStanzaDisponibile(s, dataIn, dataOut, numOspiti, new PrenotazioneDAO());
    }

    public boolean isStanzaDisponibile(Stanza s, Date dataIn, Date dataOut, int numOspiti, PrenotazioneDAO dao){

        if(numOspiti > s.getCapienza())
            return false;

        java.util.Date DataIn = new java.util.Date(dataIn.getTime());
        java.util.Date DataOut = new java.util.Date(dataOut.getTime());

        ArrayList<Prenotazione> all = (ArrayList<Prenotazione>) dao.doRetrieveByStanza(s);
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