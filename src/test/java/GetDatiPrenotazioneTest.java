import BookNow.Entity.Prenotazione;
import BookNow.Entity.Utente;
import BookNow.Storage.AutenticazioneDAO;
import BookNow.Storage.PrenotazioneDAO;
import BookNow.Storage.StorageFacade;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class GetDatiPrenotazioneTest {
    @Spy
    StorageFacade mock = StorageFacade.getInstance();

    private int testId = 1;

    @Test
    public void prenotazioneEsistente() throws NoSuchFieldException, IllegalAccessException {
        PrenotazioneDAO pD = Mockito.mock(PrenotazioneDAO.class);
        Mockito.when(pD.doRetrieveById(testId)).thenReturn(new Prenotazione());

        Field field = StorageFacade.class.getDeclaredField("prenotazioneDAO");
        field.setAccessible(true);
        field.set(mock, pD);

        Prenotazione p = mock.getDatiPrenotazione(testId);
        assertNotNull(p);
    }

    @Test
    public void prenotazioneNonEsistente() throws NoSuchFieldException, IllegalAccessException {
        PrenotazioneDAO pD = Mockito.mock(PrenotazioneDAO.class);
        Mockito.when(pD.doRetrieveById(testId)).thenReturn(null);

        Field field = StorageFacade.class.getDeclaredField("prenotazioneDAO");
        field.setAccessible(true);
        field.set(mock, pD);

        Prenotazione p = mock.getDatiPrenotazione(testId);
        assertNull(p);
    }
}
