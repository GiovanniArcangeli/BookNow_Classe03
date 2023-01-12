package BookNow.Application;

import BookNow.Storage.StorageFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/modifica-struttura")
public class ModificaStrutturaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Il parametro ID_Struttura viene passato come campo nascosto nel form
            int idStruttura = Integer.parseInt(request.getParameter("ID_Struttura"));

            String nomeStruttura = request.getParameter("nomeStruct");
            String indirizzo = request.getParameter("indirizzo");

            StorageFacade.getInstance().modificaStruttura(idStruttura, indirizzo, nomeStruttura);

            //Da decidere
            request.getRequestDispatcher("").forward(request, response);
        } catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}