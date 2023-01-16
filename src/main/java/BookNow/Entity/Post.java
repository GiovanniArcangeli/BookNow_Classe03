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

    public int getID_Post() {
        return ID_Post;
    }
    public void setID_Post(int ID_Post) {
        this.ID_Post = ID_Post;
    }

    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto(){return testo;}
    public void setTesto(String testo){this.testo = testo;}

    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

    public Cliente getAutore() {
        return autore;
    }
    public void setAutore(Cliente autore) {
        this.autore = autore;
    }
}
