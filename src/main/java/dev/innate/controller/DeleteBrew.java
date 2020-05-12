package dev.innate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.innate.entity.User;
import dev.innate.model.DeleteBrewRequest;
import dev.innate.entity.Brew;
import dev.innate.persistance.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Servlet that exclusively handles requests to delete brews.
 */
@WebServlet(urlPatterns = {"/deleteBrew"})
public class DeleteBrew extends HttpServlet {
    private static Logger logger = LogManager.getLogger(DeleteBrew.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int brewId = Integer.parseInt(request.getParameter("id"));
        logger.info(String.format(request.getSession().getId() + " Attempting to delete brew (id = %d)", brewId));
        GenericDao brewDao = new GenericDao(Brew.class);
        brewDao.delete(brewDao.getById(brewId));
        GenericDao userDao = new GenericDao(User.class);
        request.setAttribute("brews",  brewDao.findByPropertyEqual("user", userDao.getById((Integer)(request.getSession().getAttribute("userId")))));

        RequestDispatcher dispatcher = request.getRequestDispatcher("brewsByUser.jsp");
        dispatcher.forward(request, response);
    }
}
