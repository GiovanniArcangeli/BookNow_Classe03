package Model;

public class Struttura {
    private int ID_Struttura;
    private String indirizzo, nome;

    public Struttura(int ID_Struttura, String indirizzo, String nome) {
        this.ID_Struttura = ID_Struttura;
        this.indirizzo = indirizzo;
        this.nome = nome;
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
}
