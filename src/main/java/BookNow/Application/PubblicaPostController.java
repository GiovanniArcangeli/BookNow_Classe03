package BookNow.Application;

import BookNow.Entity.Cliente;
import BookNow.Entity.Post;
import BookNow.Entity.Prenotazione;
import BookNow.Entity.Utente;
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

@WebServlet("/forum/*")
public class PubblicaPostController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = request.getPathInfo();
            switch (path) {

                case "/home": {
                    request.setAttribute("posts", StorageFacade.getInstance().getAllPosts());
                    request.getRequestDispatcher("ForumHome.jsp").forward(request, response);
                    break;
                }

                case "/nuovo-post": {
                    Utente utente = (Utente) request.getSession().getAttribute("utente");
                    if (utente == null) {
                        //Utente non loggato
                        //Viene mostrata la pagina di login
                        request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
                    } else if (utente.isAlbergatore()) {
                        //Decidere quale errore inviare
                    } else {

                        String titolo = request.getParameter("titolo");
                        String corpo = request.getParameter("corpo");
                        String tags = request.getParameter("tags");
                        Cliente autore = (Cliente) utente;

                        Post post = StorageFacade.getInstance().pubblicazionePost(autore, titolo, corpo, tags);

                        if (post.getID_Post() < 0)
                            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                        //SETTARE GLI ATTRIBUTI che servono alla view
                        request.getRequestDispatcher("").forward(request, response);
                    }
                }
            }
        } catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}