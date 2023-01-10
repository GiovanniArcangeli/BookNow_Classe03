package BookNow.Entity;

public class Post {
    private int ID_Post;
    private String testo, tags;
    private Utente utente;

    public Post(int ID_Post, String testo, String tags, Utente utente) {
        this.ID_Post = ID_Post;
        this.testo = testo;
        this.tags = tags;
        this.utente = utente;
    }

    public int getID_Post() {
        return ID_Post;
    }

    public void setID_Post(int ID_Post) {
        this.ID_Post = ID_Post;
    }
    public String getTesto(){return testo;}
    public void setTesto(String testo){this.testo = testo;}

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
