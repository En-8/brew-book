package dev.innate.controller;

import dev.innate.util.BrewFormUtil;
import dev.innate.util.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet that handles requests to create a new brew
 */
@WebServlet(urlPatterns = {"/createBrew"})
public class CreateBrew extends HttpServlet {
    Logger log = LogManager.getLogger(this.getClass());
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BrewFormUtil formUtil = new BrewFormUtil();
        RequestDispatcher dispatcher = request.getRequestDispatcher("createBrew.jsp");
        HttpSession session = request.getSession();
        SessionManager.addUserToSession(request.getRemoteUser(), session);
        formUtil.addFormSelectOptionsToRequest(request);

        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BrewFormUtil formUtil = new BrewFormUtil();
        int brewId = formUtil.createBrewFromForm(request);
        log.info(String.format("User %s created brew with id %d", request.getRemoteUser(), brewId));
        response.sendRedirect(String.format("brewDetails?id=%d", brewId));
    }
}
