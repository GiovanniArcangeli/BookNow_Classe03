package BookNow.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;

public class Cliente extends Utente{

    private String numero_carta;
    private Date scadenza_carta;
    private int cvv;
    private List<Prenotazione> prenotazioni;
    private List<Post> posts;

    public Cliente(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, String numero_carta, Date scadenza_carta, int cvv) {
        super(cf, nome, cognome, recapitoTelefonico, password, username, email, dataNascita, false);
        this.cvv=cvv;
        this.scadenza_carta=scadenza_carta;
        this.numero_carta=numero_carta;
        prenotazioni=new ArrayList<Prenotazione>();
    }

    public String getNumero_carta() {
        return numero_carta;
    }
    public void setNumero_carta(String numero_carta) {
        this.numero_carta = numero_carta;
    }

    public Date getScadenza_carta() {
        return scadenza_carta;
    }
    public void setScadenza_carta(Date scadenza_carta) {
        this.scadenza_carta = scadenza_carta;
    }

    public int getCvv() {
        return cvv;
    }
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPrenotazioni(Prenotazione p) {
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        prenotazioni.add(p);
    }
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
