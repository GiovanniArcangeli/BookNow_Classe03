import BookNow.Entity.Utente;
import BookNow.Storage.StorageFacade;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ControlloAccessoTest {
    @Test
    public void soloUsernameErrato(){
        String username=null; String password=("12345678");
        boolean check=StorageFacade.getInstance().controlloAccesso(username, password) instanceof Utente;
        assertFalse(check);
    }

    @Test
    public void soloPasswordErrata(){
        String username="CarmineRen"; String password=null;
        boolean check=StorageFacade.getInstance().controlloAccesso(username, password) instanceof Utente;
        assertFalse(check);
    }

    @Test
    public void passEUsErrate(){
        String username=null; String password=null;
        boolean check=StorageFacade.getInstance().controlloAccesso(username, password) instanceof Utente;
        assertFalse(check);
    }

    @Test
    public void autenticato(){
        String username="CarmineRen"; String password=("Carmine4");
        boolean check=StorageFacade.getInstance().controlloAccesso(username, password) instanceof Utente;
        assertTrue(check);
    }

}
