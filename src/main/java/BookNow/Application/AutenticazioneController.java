package BookNow.Application;

import BookNow.Entity.Utente;
import BookNow.Storage.StorageFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class AutenticazioneController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Utente utente = StorageFacade.getInstance().controlloAccesso(username, password);

        //L'attributo è null se l'autenticazione fallisce
        request.setAttribute("utente", utente);

        if(utente == null)
            //Si ritorna alla pagina di login (SEGNALARE LOGIN FAIL NELLA VIEW)
            request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
        else
            //DECIDERE PAGINA
            request.getRequestDispatcher("").forward(request, response);

    }
}