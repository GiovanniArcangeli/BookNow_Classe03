package Model;

import java.util.GregorianCalendar;

public class Cliente extends Utente{

    private String numero_carta;
    private GregorianCalendar scadenza_carta;
    private int cvv;

    public String getNumero_carta() {
        return numero_carta;
    }

    public void setNumero_carta(String numero_carta) {
        this.numero_carta = numero_carta;
    }

    public GregorianCalendar getScadenza_carta() {
        return scadenza_carta;
    }

    public void setScadenza_carta(GregorianCalendar scadenza_carta) {
        this.scadenza_carta = scadenza_carta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
