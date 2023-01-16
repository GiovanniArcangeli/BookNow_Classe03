package BookNow.Entity;

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
    public void setID_Post(int ID_Post) {
        this.ID_Post = ID_Post;
    }
    /**
     * @return il titolo
     */
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    /**
     * @return il testo
     */
    public String getTesto(){return testo;}
    public void setTesto(String testo){this.testo = testo;}
    /**
     * @return i tags
     */
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    /**
     * @return il Cliente che lo ha pubblicato
     */
    public Cliente getAutore() {
        return autore;
    }
    public void setAutore(Cliente autore) {
        this.autore = autore;
    }
}
