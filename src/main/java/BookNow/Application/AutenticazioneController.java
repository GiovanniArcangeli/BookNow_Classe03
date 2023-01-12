package BookNow.Application;

import BookNow.Entity.Albergatore;
import BookNow.Entity.Cliente;
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
        if(utente == null) {
            request.setAttribute("loginError", true);
            request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
        } else if(utente.isAlbergatore()) {
            //L'utente è un albergatore
            Albergatore albergatore = StorageFacade.getInstance().getDatiAlbergatore(utente);
            request.getSession().setAttribute("utente", albergatore);
            request.getRequestDispatcher("ProfiloAlbergatore.jsp").forward(request, response);
        } else{
            //L'utente è un cliente
            Cliente cliente = StorageFacade.getInstance().getDatiCliente(utente);
            request.getSession().setAttribute("utente", cliente);
            request.getRequestDispatcher("ProfiloCliente.jsp").forward(request, response);
        }

    }
}