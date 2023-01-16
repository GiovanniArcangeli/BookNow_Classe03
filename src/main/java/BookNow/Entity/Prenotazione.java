package BookNow.Entity;

import java.sql.Date;
/**
 Un oggetto Prenotazione rappresenta una prenotazione di una Stanza
 da parte di un Utente. In quanto tale, presenta un ID univoco, il
 numero di ospiti, le date di Check-in e Check-out, la Stanza prenotata
 ed il Cliente che ha effettuato la prenotazione
 */
public class Prenotazione {

    private int ID_Prenotazione, numOspiti;
    private Date dataIn, dataOut;
    private Stanza stanza;
    private Cliente cliente;

    public Prenotazione(){}

    public Prenotazione(int idPrenotazione, Date dataIn, Date dataOut, int numOspiti, Stanza stanza, Cliente cliente){
        this.ID_Prenotazione = idPrenotazione;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
        this.numOspiti = numOspiti;
        this.stanza = stanza;
        this.cliente = cliente;
    }
    /**
     * @return l'id
     */
    public int getID_Prenotazione() {
        return ID_Prenotazione;
    }

    /**
     * @param ID_Prenotazione l'id da settare
     */
    public void setID_Prenotazione(int ID_Prenotazione) {
        this.ID_Prenotazione = ID_Prenotazione;
    }
    /**
     * @return il numero di ospiti
     */
    public int getNumOspiti() {
        return numOspiti;
    }

    /**
     * @param numOspiti il numOspiti da settare
     */
    public void setNumOspiti(int numOspiti) {
        this.numOspiti = numOspiti;
    }
    /**
     * @return la data di check-in
     */
    public Date getDataIn() {
        return dataIn;
    }

    /**
     * @param dataIn la dataIn da settare
     */
    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }
    /**
     * @return la data di check-out
     */
    public Date getDataOut() {
        return dataOut;
    }

    /**
     * @param dataOut la dataOut da settare
     */
    public void setDataOut(Date dataOut) {
        this.dataOut = dataOut;
    }
    /**
     * @return la stanza prenotata
     */
    public Stanza getStanza() {
        return stanza;
    }

    /**
     * @param stanza la stanza da settare
     */
    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }
    /**
     * @return il Cliente che ha effettuato la prenotazione
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente il cliente da settare
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
