package dev.innate.controller;

import dev.innate.entity.*;
import dev.innate.persistance.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/createBrew"})
public class CreateBrew extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createBrew.jsp");

        // Get the fermentable options
        GenericDao fermentDao = new GenericDao(Fermentable.class);
        List<Fermentable> fermentables = fermentDao.getAll();
        request.setAttribute("fermentables", fermentables);

        // Get the hops options
        GenericDao hopDao = new GenericDao(Hop.class);
        List<Hop> hops = hopDao.getAll();
        request.setAttribute("hops", hops);

        // Get the style options
        GenericDao styleDao = new GenericDao(Style.class);
        List<Style> styles = styleDao.getAll();
        request.setAttribute("styles", styles);

        // Get the yeast options
        GenericDao yeastDao = new GenericDao(Yeast.class);
        List<Yeast> yeasts = yeastDao.getAll();
        request.setAttribute("yeasts", yeasts);

        // Get the other ingredient options.
        GenericDao miscDao = new GenericDao(Miscellaneous.class);
        List<Miscellaneous> otherIngredients = miscDao.getAll();
        request.setAttribute("otherIngredients", otherIngredients);

        dispatcher.forward(request, response);
    }
}
