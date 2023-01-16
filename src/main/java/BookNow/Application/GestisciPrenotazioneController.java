package BookNow.Application;

import BookNow.Entity.Cliente;
import BookNow.Entity.Prenotazione;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPrenotazione = Integer.parseInt(request.getParameter("id"));
        Prenotazione prenotazione = StorageFacade.getInstance().getDatiPrenotazione(idPrenotazione);
        request.setAttribute("prenotazione", prenotazione);
        request.getRequestDispatcher("GestisciPrenotazioneGUI/FormModificaPrenotazione.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //Il parametro idPrenotazione viene passato come campo nascosto nel form
            int idPrenotazione = Integer.parseInt(request.getParameter("id"));

            String sDataIn = request.getParameter("dataIn");
            Date dataIn = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataIn).getTime());
            String sDataOut = request.getParameter("dataOut");
            Date dataOut = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataOut).getTime());
            int numOspiti = Integer.parseInt(request.getParameter("numOspiti"));

            Prenotazione newOne = StorageFacade.getInstance().modificaPrenotazione(idPrenotazione, dataIn, dataOut, numOspiti);
            if(newOne != null) {
                //La modifica è avvenuta correttamente
                Cliente cliente = (Cliente) request.getSession().getAttribute("utente");
                cliente.aggiornaPrenotazione(newOne);
                request.getSession().setAttribute("utente", cliente);
                request.getRequestDispatcher("GestisciPrenotazioneGUI/Prenotazioni.jsp").forward(request, response);
            }  else{
                //La stanza non è disponibile, si torna al form per cambiare i parametri
                request.setAttribute("retry", true);
                this.doGet(request, response);
            }

        } catch (RuntimeException | ParseException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}