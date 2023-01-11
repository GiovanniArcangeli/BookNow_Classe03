package BookNow.Entity;

import java.sql.Date;

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

    public int getID_Prenotazione() {
        return ID_Prenotazione;
    }

    public void setID_Prenotazione(int ID_Prenotazione) {
        this.ID_Prenotazione = ID_Prenotazione;
    }

    public int getNumOspiti() {
        return numOspiti;
    }

    public void setNumOspiti(int numOspiti) {
        this.numOspiti = numOspiti;
    }

    public Date getDataIn() {
        return dataIn;
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    public Date getDataOut() {
        return dataOut;
    }

    public void setDataOut(Date dataOut) {
        this.dataOut = dataOut;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
