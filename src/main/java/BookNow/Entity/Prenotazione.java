package BookNow.Entity;

import java.util.Date;

public class Prenotazione {

    private int ID_Prenotazione, numOspiti;
    private Date dataIn, dataOut;

    public Prenotazione(int idPrenotazione, Date dataIn, Date dataOut, int numOspiti){
        this.ID_Prenotazione = idPrenotazione;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
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
}
