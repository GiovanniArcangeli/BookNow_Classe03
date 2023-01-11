package BookNow.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente extends Utente{

    private String numero_carta;
    private Date scadenza_carta;
    private int cvv;
    private ArrayList<Prenotazione> prenotazioni;

    public Cliente(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, String numero_carta, Date scadenza_carta, int cvv) {
        super(cf, nome, cognome, recapitoTelefonico, password, username, email, dataNascita, 0);
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

    public ArrayList<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void addPrenotazioni(Prenotazione p) {
        prenotazioni.add(p);
    }

    public void deletePrenotazioni(Prenotazione p){
        for(Prenotazione pr: prenotazioni){
            if(p.getID_Prenotazione()==pr.getID_Prenotazione()) prenotazioni.remove(pr);
        }
    }

    public void aggiornaPrenotazione(Prenotazione p, int id){
        for(Prenotazione pr: prenotazioni){
            if(pr.getID_Prenotazione()==id){
                prenotazioni.remove(pr);
                prenotazioni.add(p);
            }
        }
    }
}
