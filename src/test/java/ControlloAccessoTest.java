import BookNow.Entity.Utente;
import BookNow.Storage.AutenticazioneDAO;
import BookNow.Storage.StorageFacade;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.lang.reflect.Field;
import java.sql.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ControlloAccessoTest {

    @Spy
    StorageFacade mock = StorageFacade.getInstance();

    @Test
    public void credenzialiErrate() throws NoSuchFieldException, IllegalAccessException {
        AutenticazioneDAO ad= Mockito.mock(AutenticazioneDAO.class);
        Mockito.when(ad.autenticazione(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

        Field field = StorageFacade.class.getDeclaredField("autenticazioneDAO");
        field.setAccessible(true);
        field.set(mock, ad);
        Utente utente = mock.controlloAccesso(Mockito.anyString(), Mockito.anyString());
        assertNull(utente);
    }

    @Test
    public void accessoConsentito() throws NoSuchFieldException, IllegalAccessException {
        AutenticazioneDAO ad= Mockito.mock(AutenticazioneDAO.class);
        Mockito.when(ad.autenticazione(Mockito.anyString(), Mockito.anyString())).thenReturn(new Utente());

        Field field = StorageFacade.class.getDeclaredField("autenticazioneDAO");
        field.setAccessible(true);
        field.set(mock, ad);
        Utente utente = mock.controlloAccesso(Mockito.anyString(), Mockito.anyString());
        assertNotNull(utente);
    }
}
