package BookNow.Entity;
/**
 Un oggetto Post rappresenta una pubblicazione sul forum, effettuata da un Cliente
 e ha un ID univoco, un titolo, un testo, dei tag ed ovviamente l'autore,
 ovvero il Cliente che l'ha pubblicato
 */
public class Post {
    private int ID_Post;
    private String titolo, testo, tags;
    private Cliente autore;

    public Post(){}
    public Post(int ID_Post, String titolo, String testo, String tags, Cliente autore) {
        this.ID_Post = ID_Post;
        this.titolo = titolo;
        this.testo = testo;
        this.tags = tags;
        this.autore = autore;
    }
    /**
     * @return l'Id
     */
    public int getID_Post() {
        return ID_Post;
    }

    /**
     * @param ID_Post l'id da settare
     */
    public void setID_Post(int ID_Post) {
        this.ID_Post = ID_Post;
    }
    /**
     * @return il titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo il titolo da settare
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    /**
     * @return il testo
     */
    public String getTesto(){return testo;}

    /**
     * @param testo il test da settare
     */
    public void setTesto(String testo){this.testo = testo;}
    /**
     * @return i tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags i tag da settare
     */
    public void setTags(String tags) {
        this.tags = tags;
    }
    /**
     * @return il Cliente che lo ha pubblicato
     */
    public Cliente getAutore() {
        return autore;
    }

    /**
     * @param autore l'autore da settare
     */
    public void setAutore(Cliente autore) {
        this.autore = autore;
    }
}
