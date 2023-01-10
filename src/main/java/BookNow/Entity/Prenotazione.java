package BookNow.Entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Prenotazione {

    private int ID_Prenotazione, numOspiti;
    private GregorianCalendar dataIn, dataOut;

    public Prenotazione(int idPrenotazione, Calendar dataIn, Calendar dataOut, int numOspiti){
        this.ID_Prenotazione = idPrenotazione;
        this.dataIn = (GregorianCalendar) dataIn;
        this.dataOut = (GregorianCalendar) dataOut;
        this.numOspiti = numOspiti;
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
