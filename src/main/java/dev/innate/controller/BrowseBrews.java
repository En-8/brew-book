package dev.innate.controller;

import dev.innate.entity.Brew;
import dev.innate.persistance.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/browseAll", "/"})
public class BrowseBrews extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao brewDao = new GenericDao(Brew.class);
        request.setAttribute("brews", brewDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("browseAllBrews.jsp");
        dispatcher.forward(request, response);
    }
}
