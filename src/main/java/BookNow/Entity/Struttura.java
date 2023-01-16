package BookNow.Entity;

import java.util.ArrayList;
import java.util.List;
/**
 Una Struttura è una classe contenente tutte le informazioni
 inerenti ad una Struttura e ha un ID, un indirizzo, un nome,
 un Albergatore di riferimento ed una lista delle stanze
 presenti in struttura
 */
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

    /**
     * @param ID_Struttura l'id da settare
     */
    public void setID_Struttura(int ID_Struttura) {
        this.ID_Struttura = ID_Struttura;
    }
    /**
     * @return l'indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * @param indirizzo l'indirizzo da settare
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    /**
     * @return il nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome il nome da settare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return l'albergatore
     */
    public Albergatore getAlbergatore() {
        return albergatore;
    }

    /**
     * @param albergatore l'albergatore da settare
     */
    public void setAlbergatore(Albergatore albergatore) {
        this.albergatore = albergatore;
    }
    /**
     * @return le stanze
     */
    public List<Stanza> getStanze() {
        return stanze;
    }

    /**
     * @param stanze la lista delle stanze da settare
     */
    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }
}
