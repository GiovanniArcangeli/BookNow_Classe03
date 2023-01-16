package BookNow.Entity;

import java.util.ArrayList;
import java.util.List;

public class Struttura {
    private int ID_Struttura;
    private String indirizzo, nome;
    private Albergatore albergatore;
    private List<Stanza> stanze;

    public Struttura(){}
    public Struttura(int ID_Struttura, String indirizzo, String nome, Albergatore albergatore) {
        this.ID_Struttura = ID_Struttura;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.albergatore = albergatore;
        this.stanze = new ArrayList<>();
    }

    /**
     * @return l'id
     */
    public int getID_Struttura() {
        return ID_Struttura;
    }
    public void setID_Struttura(int ID_Struttura) {
        this.ID_Struttura = ID_Struttura;
    }
    /**
     * @return l'indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    /**
     * @return il nome
     */
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return l'albergatore
     */
    public Albergatore getAlbergatore() {
        return albergatore;
    }
    public void setAlbergatore(Albergatore albergatore) {
        this.albergatore = albergatore;
    }
    /**
     * @return le stanze
     */
    public List<Stanza> getStanze() {
        return stanze;
    }
    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }
}
