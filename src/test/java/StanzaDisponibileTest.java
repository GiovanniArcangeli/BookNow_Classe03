import BookNow.Entity.Cliente;
import BookNow.Entity.Prenotazione;
import BookNow.Entity.Stanza;
import BookNow.Entity.Struttura;
import BookNow.Storage.PrenotazioneDAO;
import BookNow.Storage.StorageFacade;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import java.sql.Date;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StanzaDisponibileTest {

    private static PrenotazioneDAO dao;
    private static Stanza stanza;

    @BeforeClass
    public static void before(){
        dao = Mockito.mock(PrenotazioneDAO.class);
        stanza = new Stanza(1, 2, "", "", 100, new Struttura());
        ArrayList<Prenotazione> list = new ArrayList<>();
        list.add(new Prenotazione(1, new Date(2023, 10, 10), new Date(2023, 10, 15), 2, stanza, new Cliente()));
        list.add(new Prenotazione(2, new Date(2023, 3, 17), new Date(2023, 3, 18), 1, stanza, new Cliente()));
        Mockito.when(dao.doRetrieveByStanza(stanza)).thenReturn(list);
    }

    @Test
    public void tooManyPeople(){
        Date dataIn = new Date(2023, 4, 4);
        Date dataOut = new Date(2023, 4, 5);
        int numOspiti = 3;
        boolean disp = StorageFacade.getInstance().isStanzaDisponibile(stanza, dataIn, dataOut, numOspiti, dao);
        assertFalse(disp);
    }

    @Test
    public void checkInOverlap(){
        Date dataIn = new Date(2023, 10, 12);
        Date dataOut = new Date(2023, 10, 16);
        int numOspiti = 2;
        boolean disp = StorageFacade.getInstance().isStanzaDisponibile(stanza, dataIn, dataOut, numOspiti, dao);
        assertFalse(disp);
    }

    @Test
    public void checkOutOverlap(){
        Date dataIn = new Date(2023, 3, 15);
        Date dataOut = new Date(2023, 3, 18);
        int numOspiti = 2;
        boolean disp = StorageFacade.getInstance().isStanzaDisponibile(stanza, dataIn, dataOut, numOspiti, dao);
        assertFalse(disp);
    }

    @Test
    public void availableRoom(){
        Date dataIn = new Date(2023, 3, 15);
        Date dataOut = new Date(2023, 3, 17);
        int numOspiti = 2;
        boolean disp = StorageFacade.getInstance().isStanzaDisponibile(stanza, dataIn, dataOut, numOspiti, dao);
        assertTrue(disp);
    }
}
