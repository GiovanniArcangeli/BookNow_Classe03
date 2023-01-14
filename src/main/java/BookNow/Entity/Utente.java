package BookNow.Entity;

import java.sql.Date;

public class Utente {

    private String cf, nome, cognome, recapitoTelefonico, password, username, email;
    private Date dataNascita;
    private boolean isAlbergatore;

    public Utente(){}

    public Utente(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, boolean isAlbergatore) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.recapitoTelefonico = recapitoTelefonico;
        this.password = password;
        this.username = username;
        this.email = email;
        this.dataNascita = dataNascita;
        this.isAlbergatore = isAlbergatore;
    }

    public boolean isAlbergatore() {
        return isAlbergatore;
    }

    public void setAlbergatore(boolean albergatore) {
        isAlbergatore = albergatore;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }

    public void setRecapitoTelefonico(String recapitoTelefonico) {
        this.recapitoTelefonico = recapitoTelefonico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
}
