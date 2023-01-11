package BookNow.Entity;

public class Struttura {
    private int ID_Struttura;
    private String indirizzo, nome;
    private Albergatore albergatore;

    public Struttura(int ID_Struttura, String indirizzo, String nome, Albergatore albergatore) {
        this.ID_Struttura = ID_Struttura;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.albergatore = albergatore;
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
}
