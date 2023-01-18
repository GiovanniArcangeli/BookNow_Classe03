import BookNow.Entity.Utente;
import BookNow.Storage.AutenticazioneDAO;
import BookNow.Storage.StorageFacade;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ControlloAccessoTest {
    @Test
    public void usernameErrato(){
        String username="errato", password="Carmine4";
        AutenticazioneDAO ad= Mockito.mock(AutenticazioneDAO.class);
        Mockito.when(ad.autenticazione(username, password)).thenReturn(null);

        StorageFacade.getInstance();
        Utente utente=StorageFacade.getInstance().controlloAccesso(username, password);
        assertEquals("Errore!", ad.autenticazione(username, password), utente);
    }

    @Test
    public void passwordErrata(){
        String username="CarmineRen", password="errata";
        AutenticazioneDAO ad= Mockito.mock(AutenticazioneDAO.class);
        Mockito.when(ad.autenticazione(username, password)).thenReturn(null);

        StorageFacade.getInstance();
        Utente utente=StorageFacade.getInstance().controlloAccesso(username, password);
        assertEquals("Errore!", ad.autenticazione(username, password), utente);
    }

    @Test
    public void usernameePasswordErrati(){
        String username="errato", password="errato";
        AutenticazioneDAO ad= Mockito.mock(AutenticazioneDAO.class);
        Mockito.when(ad.autenticazione(username, password)).thenReturn(null);

        StorageFacade.getInstance();
        Utente utente=StorageFacade.getInstance().controlloAccesso(username, password);
        assertEquals("Errore!", ad.autenticazione(username, password), utente);
    }

    @Test
    public void accessoConsentito(){
        String username="CarmineRen", password="Carmine4";
        AutenticazioneDAO ad= Mockito.mock(AutenticazioneDAO.class);
        Mockito.when(ad.autenticazione(username, password)).thenReturn(new Utente("", "", "", "", password, username, "", new Date(2000, 1, 1), false));

        StorageFacade.getInstance();
        Utente utente=StorageFacade.getInstance().controlloAccesso(username, password);
        assertEquals("Errore!", ad.autenticazione(username, password).getUsername(), utente.getUsername());
    }
}
