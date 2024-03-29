package BookNow.Application;

import BookNow.Entity.Cliente;
import BookNow.Entity.Utente;
import BookNow.Storage.StorageFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forum")
public class PubblicaPostController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("posts", StorageFacade.getInstance().getAllPosts());
        request.getRequestDispatcher("PubblicaPostGUI/ForumHome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                    Utente utente = (Utente) request.getSession().getAttribute("utente");
                    if (utente == null) {
                        //Utente non loggato
                        //Viene mostrata la pagina di login
                        request.getRequestDispatcher("AutenticazioneGUI/LoginPage.jsp").forward(request, response);
                    } else if (utente.getIsAlbergatore()) {
                        //Decidere quale errore inviare
                    } else {

                        String titolo = request.getParameter("titolo");
                        String corpo = request.getParameter("corpo");
                        String tags = request.getParameter("tags");
                        Cliente autore = (Cliente) utente;

                        StorageFacade.getInstance().pubblicazionePost(autore, titolo, corpo, tags);
                        request.setAttribute("posts", StorageFacade.getInstance().getAllPosts());
                        request.getRequestDispatcher("PubblicaPostGUI/ForumHome.jsp").forward(request, response);
                    }
        } catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}