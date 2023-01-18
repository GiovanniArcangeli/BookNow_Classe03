import BookNow.Entity.Struttura;
import BookNow.Storage.StorageFacade;
import BookNow.Storage.StrutturaDAO;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
public class getDatiStruttureTest {
    @Spy
    StorageFacade mock = StorageFacade.getInstance();

    private int testId = 1;
    @Test
    public void strutturaEsistente() throws NoSuchFieldException, IllegalAccessException {
        StrutturaDAO sD = Mockito.mock(StrutturaDAO.class);
        Mockito.when(sD.doRetrieveById(testId)).thenReturn(new Struttura());
        Field field = StorageFacade.class.getDeclaredField("strutturaDAO");
        field.setAccessible(true);
        field.set(mock, sD);

        Struttura s = mock.getDatiStruttura(testId);
        assertNotNull(s);
    }

    @Test
    public void strutturaNonEsistente() throws NoSuchFieldException, IllegalAccessException {
        StrutturaDAO sD = Mockito.mock(StrutturaDAO.class);
        Mockito.when(sD.doRetrieveById(testId)).thenReturn(null);

        Field field = StorageFacade.class.getDeclaredField("strutturaDAO");
        field.setAccessible(true);
        field.set(mock, sD);

        Struttura s = mock.getDatiStruttura(testId);
        assertNull(s);
    }
}
