package BookNow.Application;

import BookNow.Entity.Cliente;
import BookNow.Entity.Prenotazione;
import BookNow.Entity.Utente;
import BookNow.Storage.StorageFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@WebServlet("/prenota")
public class PrenotaStanzaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utente utente = (Utente) request.getSession().getAttribute("utente");
            if(utente == null) {
                //Utente non loggato
                //Viene mostrata la pagina di login
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
            } else {

                //I parametri ID_Struttura e numeroStanza verranno passati come campi nascosti del form
                int ID_Stuttura = Integer.parseInt(request.getParameter("ID_Struttura"));
                int numeroStanza = Integer.parseInt(request.getParameter("numeroStanza"));

                String sDataIn = request.getParameter("dataIn");
                Date dataIn = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataIn).getTime());
                String sDataOut = request.getParameter("dataOut");
                Date dataOut = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataOut).getTime());
                int numOspiti = Integer.parseInt(request.getParameter("numOspiti"));
                Cliente cliente = (Cliente) utente;

                Prenotazione prenotazione = StorageFacade.getInstance().prenotazioneStanza(cliente, ID_Stuttura, numeroStanza, dataIn, dataOut, numOspiti);

                if(prenotazione.getID_Prenotazione() < 0)
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                //Se la prenotazione va a buon fine, viene mostrata la pagina per gestirla
                //Bisogna SETTARE GLI ATTRIBUTI che servono alla view

                request.getRequestDispatcher("/WEB-INF/GestisciPrenotazioneGUI.jsp").forward(request, response);
            }
        } catch (RuntimeException | ParseException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}