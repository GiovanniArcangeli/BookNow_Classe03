package BookNow.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Albergatore extends Utente {

    private String p_iva;
    private List<Struttura> strutture;

    public Albergatore(String cf, String nome, String cognome, String recapitoTelefonico, String password, String username, String email, Date dataNascita, String p_iva) {
        super(cf, nome, cognome, recapitoTelefonico, password, username, email, dataNascita, true);
        this.p_iva=p_iva;
        strutture=new ArrayList<Struttura>();
    }
    /**
     * @return la partita iva
     */
    public String getP_iva() {
        return p_iva;
    }

    /**
     * @param p_iva la p_iva da settare
     */
    public void setP_iva(String p_iva) {
        this.p_iva = p_iva;
    }
    /**
     * @return La lista delle strutture dell'albergatore
     */
    public List<Struttura> getStrutture() {
        return strutture;
    }

    /**
     * @param strutture la lista delle strutture da settare
     */
    public void setStrutture(List<Struttura> strutture) {
        this.strutture = strutture;
    }

    /**
     * Aggiorna una prenotazione presente nel campo Prenotazioni del cliente.
     * @param s la struttura da aggiornare
     * @pre s != null
     * @pre strutture.contains(s)
     */
    public void aggiornaStrutture(Struttura s){
        if(s == null) {
            throw new IllegalArgumentException("La struttura è nulla");
        }
        for(Struttura st: strutture){
            if(s.getID_Struttura()==st.getID_Struttura()){
                strutture.remove(st);
                strutture.add(s);
            }
        }
    }
}
