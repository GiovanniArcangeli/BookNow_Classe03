package Model;

import java.util.GregorianCalendar;

public class Prenotazione {

    private int ID_Prenotazione, numOspiti;
    private GregorianCalendar dataIn, dataOut;


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

    public GregorianCalendar getDataIn() {
        return dataIn;
    }

    public void setDataIn(GregorianCalendar dataIn) {
        this.dataIn = dataIn;
    }

    public GregorianCalendar getDataOut() {
        return dataOut;
    }

    public void setDataOut(GregorianCalendar dataOut) {
        this.dataOut = dataOut;
    }
}
