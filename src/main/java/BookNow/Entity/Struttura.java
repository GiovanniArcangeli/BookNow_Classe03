package BookNow.Entity;

import BookNow.Storage.StanzaDAO;

import java.util.ArrayList;
import java.util.List;

public class Struttura {
    private int ID_Struttura;
    private String indirizzo, nome;
    private Albergatore albergatore;
    private List<Stanza> stanze;

    public Struttura(int ID_Struttura, String indirizzo, String nome, Albergatore albergatore) {
        this.ID_Struttura = ID_Struttura;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.albergatore = albergatore;
        this.stanze = new ArrayList<>();
        StanzaDAO service = new StanzaDAO();
        this.stanze = service.doRetrieveByStruttura(this);
    }

    public void addStanza(Stanza s) {
        this.stanze.add(s);
    }

    public void deleteStanza(Stanza s){
        for(Stanza stanza: this.stanze)
            if(stanza.getStruttura().getID_Struttura() == s.getStruttura().getID_Struttura()
            && stanza.getNumeroStanza() == s.getNumeroStanza())
                this.stanze.remove(s);
    }

    public void aggiornaStanza(Stanza s){
        for(Stanza stanza: this.stanze)
            if(stanza.getStruttura().getID_Struttura() == s.getStruttura().getID_Struttura()
                    && stanza.getNumeroStanza() == s.getNumeroStanza()) {
                this.stanze.remove(s);
                this.stanze.add(s);
            }
    }
    public int getID_Struttura() {
        return ID_Struttura;
    }

    public void setID_Struttura(int ID_Struttura) {
        this.ID_Struttura = ID_Struttura;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Albergatore getAlbergatore() {
        return albergatore;
    }

    public void setAlbergatore(Albergatore albergatore) {
        this.albergatore = albergatore;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }
}
