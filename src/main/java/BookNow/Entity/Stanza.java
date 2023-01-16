package BookNow.Entity;

import java.util.ArrayList;
import java.util.List;

public class Stanza {
    private int numeroStanza, capienza;
    private String descrizione, serviziOfferti;
    private float costo;
    private Struttura struttura;
    private List<Prenotazione> prenotazioni;

    public Stanza(){}

    public Stanza(int numeroStanza, int capienza, String descrizione, String serviziOfferti, float costo, Struttura struttura) {
        this.numeroStanza = numeroStanza;
        this.capienza = capienza;
        this.descrizione = descrizione;
        this.serviziOfferti = serviziOfferti;
        this.costo = costo;
        this.struttura = struttura;
        this.prenotazioni = new ArrayList<>();
    }
    /**
     * @return Il numero
     */
    public int getNumeroStanza() {
        return numeroStanza;
    }
    public void setNumeroStanza(int numeroStanza) {
        this.numeroStanza = numeroStanza;
    }
    /**
     * @return la capienza
     */
    public int getCapienza() {
        return capienza;
    }
    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
    /**
     * @return la descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    /**
     * @return i servizi offerti
     */
    public String getServiziOfferti() {
        return serviziOfferti;
    }
    public void setServiziOfferti(String serviziOfferti) {
        this.serviziOfferti = serviziOfferti;
    }
    /**
     * @return il costo
     */
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    /**
     * @return la struttura a cui appartiene la stanza
     */
    public Struttura getStruttura() {
        return struttura;
    }
    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }
    /**
     * @return la lista di prenotazioni effettuate sulla stanza
     */
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void addPrenotazioni(Prenotazione p) {
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        prenotazioni.add(p);
    }
    public void aggiornaPrenotazione(Prenotazione p){
        if(p == null) {
            throw new IllegalArgumentException("La prenotazione è nulla");
        }
        for(Prenotazione pr: prenotazioni){
            if(pr.getID_Prenotazione()==p.getID_Prenotazione()){
                prenotazioni.remove(pr);
                prenotazioni.add(p);
            }
        }
    }
}
