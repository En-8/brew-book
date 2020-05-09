package dev.innate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.innate.Model.DeleteBrewRequest;
import dev.innate.entity.Brew;
import dev.innate.persistance.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deleteBrew"})
public class DeleteBrew extends HttpServlet {
    private static Logger logger = LogManager.getLogger(DeleteBrew.class);

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer body = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        } catch (Exception exception) {
            logger.error("Could not extract body from request object");
        }

        DeleteBrewRequest deleteBrewRequest = mapper.readValue(body.toString(), DeleteBrewRequest.class);
        logger.info(String.format("Attempting to delete brew (id = %d)", deleteBrewRequest.getId()));
        GenericDao brewDao = new GenericDao(Brew.class);
        brewDao.delete(brewDao.getById(deleteBrewRequest.getId()));
    }
}
