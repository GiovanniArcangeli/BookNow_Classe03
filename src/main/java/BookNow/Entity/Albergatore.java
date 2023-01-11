package BookNow.Entity;

import java.sql.Date;
import java.util.ArrayList;

public class Albergatore extends Utente {

    private String p_iva;
    private ArrayList<Struttura> strutture;

    public Albergatore(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, String p_iva) {
        super(cf, nome, cognome, recapitoTelefonico, password, username, email, dataNascita, 1);
        this.p_iva=p_iva;
        strutture=new ArrayList<Struttura>();
    }

    public String getP_iva() {
        return p_iva;
    }

    public void setP_iva(String p_iva) {
        this.p_iva = p_iva;
    }

    public ArrayList<Struttura> getStrutture() {
        return strutture;
    }

    public void addStrutture(Struttura s) {
        strutture.add(s);
    }

    public void deleteStruttura(Struttura s){
        for(Struttura st: strutture){
            if(s.getID_Struttura()==st.getID_Struttura()) strutture.remove(st);
        }
    }

    public void aggiornaStrutture(Struttura s){
        for(Struttura st: strutture){
            if(s.getID_Struttura()==st.getID_Struttura()){
                strutture.remove(st);
                strutture.add(s);
            }
        }
    }
}
