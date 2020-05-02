package dev.innate.controller;

import dev.innate.entity.Brew;
import dev.innate.persistance.GenericDao;
import dev.innate.util.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/brewDetails"})
public class BrewDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("brewDetails.jsp");
        SessionManager.addUserToSession(request.getRemoteUser(), request.getSession());
        GenericDao brewDao = new GenericDao(Brew.class);

        // If we have a brew ID, attempt to get the brew from the database
        // Otherwise, redirect to allBrews
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            Brew brew = (Brew) brewDao.getById(Integer.parseInt(request.getParameter("id")));
            if (brew != null) {
                request.setAttribute("brew", brew);
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendRedirect("allBrews");
        }
    }
}
