package dev.innate.controller;

import dev.innate.entity.Role;
import dev.innate.entity.User;
import dev.innate.persistance.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/createUser"})
public class CreateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userId") != null) {
            // send the user to the account management page
            // Getting here will only be possible via direct navigation.
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User newUser = new User();
        newUser.setUsername(request.getParameter("username"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));

        GenericDao dao = new GenericDao(User.class);
        int userId = dao.create(newUser);
        newUser.setId(userId);

        GenericDao roleDao = new GenericDao(Role.class);
        Role role = new Role();
        role.setUser(newUser);
        role.setUsername(newUser.getUsername());
        role.setRoleName("registered_user");
        roleDao.create(role);

        HttpSession session = request.getSession();
        session.setAttribute("username", newUser.getUsername());
        session.setAttribute("userId", userId);
        session.setAttribute("email", newUser.getEmail());

        response.sendRedirect("login");
    }
}
