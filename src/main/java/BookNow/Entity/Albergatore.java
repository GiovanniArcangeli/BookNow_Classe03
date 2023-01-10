package BookNow.Entity;

import java.sql.Date;

public class Albergatore extends Utente {

    private String p_iva;

    public Albergatore(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, String p_iva) {
        super(cf, nome, cognome, recapitoTelefonico, password, username, email, dataNascita, 1);
        this.p_iva=p_iva;
    }

    public String getP_iva() {
        return p_iva;
    }

    public void setP_iva(String p_iva) {
        this.p_iva = p_iva;
    }
}
