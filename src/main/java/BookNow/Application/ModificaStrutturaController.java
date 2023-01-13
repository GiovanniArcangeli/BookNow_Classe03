package BookNow.Application;

import BookNow.Entity.Albergatore;
import BookNow.Entity.Struttura;
import BookNow.Entity.Utente;
import BookNow.Storage.StorageFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifica-struttura")
public class ModificaStrutturaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idStruttura = Integer.parseInt(req.getParameter("id"));
        Struttura struttura = StorageFacade.getInstance().getDatiStruttura(idStruttura);
        req.setAttribute("struttura", struttura);
        req.getRequestDispatcher("ModificaStrutturaGUI/FormModificaStruttura.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Il parametro ID_Struttura viene passato come campo nascosto nel form
            int idStruttura = Integer.parseInt(request.getParameter("ID_Struttura"));

            String nomeStruttura = request.getParameter("nomeStruct");
            String indirizzo = request.getParameter("indirizzo");

            StorageFacade.getInstance().modificaStruttura(idStruttura, indirizzo, nomeStruttura);

            //Per aggiornare la lista delle strutture
            Albergatore albergatore = StorageFacade.getInstance().getDatiAlbergatore((Albergatore) request.getSession().getAttribute("utente"));
            request.getSession().setAttribute("utente", albergatore);

            //Ritorna alla lista delle strutture
            request.getRequestDispatcher("ModificaStrutturaGUI/ListaStrutture.jsp").forward(request, response);
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}