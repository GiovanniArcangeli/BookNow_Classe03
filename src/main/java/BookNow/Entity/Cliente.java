package BookNow.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;
/**
 Un oggetto Cliente rappresenta un Utente di tipo Cliente
 e ha tutte le proprietà dell'oggetto Utente e in più
 le info relative al metodo di pagamento(numero, scadenza e cvv della carta di credito).
 Inoltre presenta anche una lista per le prenotazioni a lui associate
 e una per i post da lui pubblicati
 */
public class Cliente extends Utente{

    private String numero_carta;
    private Date scadenza_carta;
    private int cvv;
    private List<Prenotazione> prenotazioni;
    private List<Post> posts;

    public Cliente(){}

    public Cliente(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, String numero_carta, Date scadenza_carta, int cvv) {
        super(cf, nome, cognome, recapitoTelefonico, password, username, email, dataNascita, false);
        this.cvv=cvv;
        this.scadenza_carta=scadenza_carta;
        this.numero_carta=numero_carta;
        prenotazioni=new ArrayList<Prenotazione>();
    }
    /**
     * @return il numero di carta
     */
    public String getNumero_carta() {
        return numero_carta;
    }

    /**
     * @param numero_carta il numero_carta da settare
     */
    public void setNumero_carta(String numero_carta) {
        this.numero_carta = numero_carta;
    }
    /**
     * @return la data di scadenza della carta
     */
    public Date getScadenza_carta() {
        return scadenza_carta;
    }

    /**
     * @param scadenza_carta la scandenza_carta da settare
     */
    public void setScadenza_carta(Date scadenza_carta) {
        this.scadenza_carta = scadenza_carta;
    }
    /**
     * @return il cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * @param cvv il cc da settare
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    /**
     * @return la lista di prenotazioni associate al Cliente
     */
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * @param prenotazioni la lista delle prenotazioni da settare
     */
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
    /**
     * @return i post pubblicati dal Cliente
     */
    public List<Post> getPosts() {
        return posts;
    }
    /**
     * @param posts la lista dei post da settare
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * Aggiunge una prenotazione nel campo Prenotazioni del cliente.
     * @param p la prenotazione da aggiungere
     * @pre p != null
     */
    public void addPrenotazioni(Prenotazione p) {
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        prenotazioni.add(p);
    }

    /**
     * Aggiorna una prenotazione presente nel campo Prenotazioni del cliente.
     * @param p la prenotazione da aggiornare
     * @pre p != null
     */
    public void aggiornaPrenotazione(Prenotazione p) {
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        for (ListIterator<Prenotazione> iterator = prenotazioni.listIterator(); iterator.hasNext(); ) {
            Prenotazione prenotazione = iterator.next();
            if (prenotazione.getID_Prenotazione() == p.getID_Prenotazione()) {
                iterator.remove();
                iterator.add(p);
            }
        }
    }
}
