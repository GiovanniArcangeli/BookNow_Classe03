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

@WebServlet("/prenota")
public class PrenotaStanzaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        int idStruttura = Integer.parseInt(request.getParameter("id"));
        ArrayList<Struttura> strutture = (ArrayList<Struttura>) request.getSession().getAttribute("struttureDisponbili");
        //Trova la struttura scelta tra quelle disponibili
        Struttura struttura = null;
        for(Struttura s : strutture){
            if (s.getID_Struttura() == idStruttura)
                struttura = s;
        }
        if(struttura != null) {
            switch (path) {
                case "/vedi-stanze": {
                    ArrayList<Stanza> stanze = (ArrayList<Stanza>) struttura.getStanze();
                    request.setAttribute("stanzeDisponibili", stanze);
                    request.getRequestDispatcher("PrenotaStanzaGUI/StanzeDisponibili.jsp").forward(request, response);
                    break;
                }

                case "/prenota-stanza": {
                    int numeroStanza = Integer.parseInt(request.getParameter("num"));
                    Prenotazione filtri = (Prenotazione) request.getSession().getAttribute("prenotazione");

                    //Tra gli attributi della request, solo l'oggetto struttura è completo
                    //La prenotazione contiene solo i filtri della ricerca
                    request.setAttribute("struttura", struttura);
                    request.setAttribute("prenotazione", filtri);
                    request.setAttribute("numeroStanza", numeroStanza);
                    request.getRequestDispatcher("PrenotaStanzaGUI/RiepilogoEConferma.jsp").forward(request, response);
                    break;
                }

                case "/conferma": {
                    int numeroStanza = Integer.parseInt(request.getParameter("num"));
                    Cliente utente = (Cliente) request.getSession().getAttribute("utente");
                    if(utente == null) {
                        //Utente non loggato
                        //Viene mostrata la pagina di login
                        request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
                    } else {
                        Prenotazione filtri = (Prenotazione) request.getSession().getAttribute("prenotazione");
                        Prenotazione prenotazione = StorageFacade.getInstance().prenotazioneStanza(utente, idStruttura, numeroStanza, filtri.getDataIn(), filtri.getDataOut(), filtri.getNumOspiti());

                        if(prenotazione == null){
                            //La stanza non è più disponibile, la prenotazione non è avvenuta
                            //Si torna al form di ricerca e la view mostra un messaggio di fallimento
                            request.setAttribute("retry", true);
                            request.getRequestDispatcher("PrenotaStanzaGUI/FormCarcaStruttura.jsp").forward(request, response);
                        }
                        else if (prenotazione.getID_Prenotazione() < 0) {
                            //Si è verificato un errore durante l'inserimento della prenotazione nel DB
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        } else {
                            //La prenotazione è stata salvata con successo, viene mostrata la pagina delle prenotazioni
                            request.removeAttribute("retry");
                            utente.addPrenotazioni(prenotazione);
                            request.getSession().setAttribute("utente", utente);
                            request.getRequestDispatcher("GestisciPrenotazioneGUI/Prenotazioni.jsp").forward(request, response);
                        }
                    }
                    break;
                }
            }
        } else
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
                        String sDataIn = request.getParameter("dataIn");
                        Date dataIn = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataIn).getTime());
                        String sDataOut = request.getParameter("dataOut");
                        Date dataOut = new Date(new SimpleDateFormat("yyyy/MM/dd").parse(sDataOut).getTime());
                        int numOspiti = Integer.parseInt(request.getParameter("numOspiti"));

                        ArrayList<Struttura> disponibili = StorageFacade.getInstance().getStruttureDisponibili(dataIn, dataOut, numOspiti);
                        request.getSession().setAttribute("struttureDisponibili", disponibili);
                        Prenotazione filtri = new Prenotazione();
                        filtri.setDataIn(dataIn);
                        filtri.setDataOut(dataOut);
                        filtri.setNumOspiti(numOspiti);
                        request.getSession().setAttribute("prenotazione", filtri);
                        request.getRequestDispatcher("PrenotaStanzaGUI/ListaStrutture.jsp").forward(request, response);
                        break;
                    }
                }
            }
        } catch (RuntimeException | ParseException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}