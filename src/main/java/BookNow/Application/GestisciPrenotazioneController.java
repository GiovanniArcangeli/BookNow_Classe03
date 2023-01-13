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

@WebServlet("/gestisci-prenotazione")
public class GestisciPrenotazioneController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Il parametro idPrenotazione viene passato come campo nascosto nel form
            int idPrenotazione = Integer.parseInt(request.getParameter("idPrenotazione"));

            String sDataIn = request.getParameter("dataIn");
            Date dataIn = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataIn).getTime());
            String sDataOut = request.getParameter("dataOut");
            Date dataOut = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataOut).getTime());
            int numOspiti = Integer.parseInt(request.getParameter("numOspiti"));

            if(StorageFacade.getInstance().modificaPrenotazione(idPrenotazione, dataIn, dataOut, numOspiti))
                request.getRequestDispatcher("").forward(request, response);

                //Decidere cosa fare se la modifica non va a buon fine

        } catch (RuntimeException | ParseException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}