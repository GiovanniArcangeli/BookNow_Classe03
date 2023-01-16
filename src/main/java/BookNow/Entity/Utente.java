package BookNow.Entity;

import java.sql.Date;
/**
 Un oggetto Utente rappresenta una persona fisica, che sia
 Albergatore o Cliente. Come proprietà ha un CF, un nome,
 un cognome, un recapito telefonico, una password, un'username,
 un'email, una data di nascita e un campo che ci fà capire se è
 Albergatore o Cliente.
 */
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

    /**
     * @param cf il cf da settare
     */
    public void setCf(String cf) {
        this.cf = cf;
    }
    /**
     * @return il nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome il nome da settare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * @return il cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome il cognome da settare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * @return il recapito telefonico
     */
    public String getRecapitoTelefonico() {
        return recapitoTelefonico;
    }

    /**
     * @param recapitoTelefonico il recapitoTelefonico da settare
     */
    public void setRecapitoTelefonico(String recapitoTelefonico) {
        this.recapitoTelefonico = recapitoTelefonico;
    }
    /**
     * @return la password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password la password da settare
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return l'username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username l'username da settare
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return l'email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email l'email da settare
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return la data di nascita
     */
    public Date getDataNascita() {
        return dataNascita;
    }

    /**
     * @param dataNascita la dataNascita da settare
     */
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
