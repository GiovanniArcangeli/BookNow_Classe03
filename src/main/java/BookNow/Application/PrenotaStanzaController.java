package BookNow.Application;

import BookNow.Entity.*;
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
import java.util.ArrayList;
import java.util.Stack;

@WebServlet("/prenota")
public class PrenotaStanzaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/vedi-stanze" : {
                int idStruttura = Integer.parseInt(request.getParameter("id"));
                ArrayList<Struttura> strutture = (ArrayList<Struttura>) request.getSession().getAttribute("struttureDisponbili");
                ArrayList<Stanza> stanze = new ArrayList<>();
                for(Struttura s : strutture){
                    if(s.getID_Struttura() == idStruttura)
                        stanze = (ArrayList<Stanza>) s.getStanze();
                }
                request.setAttribute("stanzeDisponibili", stanze);
                request.getRequestDispatcher("").forward(request, response);
                break;
            }

            case "/prenota-stanza" : {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cliente utente = (Cliente) request.getSession().getAttribute("utente");
            if(utente == null) {
                //Utente non loggato
                //Viene mostrata la pagina di login
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
            } else {

                String path = request.getPathInfo();
                switch (path) {

                    case "/cerca" : {

                        //I parametri ID_Struttura e numeroStanza verranno passati come campi nascosti del form
                        //int ID_Stuttura = Integer.parseInt(request.getParameter("ID_Struttura"));
                        //int numeroStanza = Integer.parseInt(request.getParameter("numeroStanza"));

                        String sDataIn = request.getParameter("dataIn");
                        Date dataIn = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataIn).getTime());
                        String sDataOut = request.getParameter("dataOut");
                        Date dataOut = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataOut).getTime());
                        int numOspiti = Integer.parseInt(request.getParameter("numOspiti"));

                        ArrayList<Struttura> disponibili = StorageFacade.getInstance().getStruttureDisponibili(dataIn, dataOut, numOspiti);
                        request.getSession().setAttribute("struttureDisponibili", disponibili);
                        request.getRequestDispatcher("PrenotaStanzaGUI/ListaStrutture.jsp").forward(request, response);

                        // Cliente cliente = (Cliente) utente;

                        //Prenotazione prenotazione = StorageFacade.getInstance().prenotazioneStanza(cliente, ID_Stuttura, numeroStanza, dataIn, dataOut, numOspiti);

                       // if (prenotazione.getID_Prenotazione() < 0)
                           // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                        //Se la prenotazione va a buon fine, viene mostrata la pagina per gestirla
                        //Bisogna SETTARE GLI ATTRIBUTI che servono alla view

                        break;
                    }

                    case "" : {

                    }
                }
            }
        } catch (RuntimeException | ParseException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}