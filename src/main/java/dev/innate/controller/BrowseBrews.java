package dev.innate.controller;

import dev.innate.entity.Brew;
import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;
import dev.innate.util.SessionManager;
import org.apache.logging.log4j.Level;
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
import java.util.List;

@WebServlet(urlPatterns = {"/yourBrews"})
public class BrowseBrews extends HttpServlet {
    Logger logger = LogManager.getLogger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao brewDao = new GenericDao(Brew.class);
        GenericDao userDao = new GenericDao(User.class);

        HttpSession session = request.getSession();
        SessionManager.addUserToSession(request.getRemoteUser(), session);
        List<Brew> brews = brewDao.findByPropertyEqual("user",(User) userDao.getById((Integer)session.getAttribute("userId")));

        request.setAttribute("brews", brews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("browseAllBrews.jsp");
        dispatcher.forward(request, response);
    }
}

