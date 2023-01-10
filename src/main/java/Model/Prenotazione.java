package Model;

import java.util.GregorianCalendar;

public class Prenotazione {

    private int ID_Prenotazione, numOspiti, ID_Struttura, numeroStanza;
    private String dataIn, dataOut, CF;

    public int getID_Prenotazione() {
        return ID_Prenotazione;
    }

    public int getID_Struttura() {
        return ID_Struttura;
    }

    public void setID_Struttura(int ID_Struttura) {
        this.ID_Struttura = ID_Struttura;
    }

    public int getNumeroStanza() {
        return numeroStanza;
    }

    public void setNumeroStanza(int numeroStanza) {
        this.numeroStanza = numeroStanza;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
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

    public String getDataIn() {
        return dataIn;
    }

    public void setDataIn(String dataIn) {
        this.dataIn = dataIn;
    }

    public String getDataOut() {
        return dataOut;
    }

    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }
}
