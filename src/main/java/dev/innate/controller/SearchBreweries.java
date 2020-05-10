package dev.innate.controller;

import dev.innate.client.OpenBreweryClient;
import dev.innate.model.Brewery;
import dev.innate.util.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet that handles requests for brewery searching
 */
@WebServlet(urlPatterns = {"/thirsty"})
public class SearchBreweries extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionManager.addUserToSession(request.getRemoteUser(), request.getSession());
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchBreweries.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionManager.addUserToSession(request.getRemoteUser(), request.getSession());

        OpenBreweryClient client = new OpenBreweryClient();
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        if (city != null && state != null) {
            List<Brewery> breweries = client.getBreweriesByCityState(city, state);
            request.setAttribute("breweries", breweries);
            request.setAttribute("city", city);
            request.setAttribute("state", state);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("breweries.jsp");
        dispatcher.forward(request, response);
    }
}
