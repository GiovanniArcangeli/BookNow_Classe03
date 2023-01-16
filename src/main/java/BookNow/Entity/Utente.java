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
    /**
     * @return il cf
     */
    public String getCf() {
        return cf;
    }
    public void setCf(String cf) {
        this.cf = cf;
    }
    /**
     * @return il nome
     */
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return il cognome
     */
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * @return il recapito telefonico
     */
    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }
    public void setRecapitoTelefonico(String recapitoTelefonico) {
        this.recapitoTelefonico = recapitoTelefonico;
    }
    /**
     * @return la password
     */
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return l'username
     */
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return l'email
     */
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return la data di nascita
     */
    public Date getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }
    /**
     * @return se è un albergatore
     */
    public boolean getIsAlbergatore() {
        return isAlbergatore;
    }
    public void setIsAlbergatore(boolean albergatore) {
        isAlbergatore = albergatore;
    }
}
