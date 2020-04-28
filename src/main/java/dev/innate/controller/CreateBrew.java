package dev.innate.controller;

import dev.innate.entity.*;
import dev.innate.persistance.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/createBrew"})
public class CreateBrew extends HttpServlet {
    Logger log = LogManager.getLogger(this.getClass());
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
        GenericDao miscDao = new GenericDao(Misc.class);
        List<Misc> otherIngredients = miscDao.getAll();
        request.setAttribute("otherIngredients", otherIngredients);

        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<String> fermentableIndexes = new ArrayList<>();
        List<String> hopIndexes = new ArrayList<>();
        List<String> miscIndexes = new ArrayList<>();
        request.getParameterNames().asIterator().forEachRemaining(entry -> {
            String index = "-1";
            if (Character.isDigit(entry.charAt(entry.length()-1))) {
                index = determineIndex(entry);
                if (isAFermentable(entry)) {
                    if (!fermentableIndexes.contains(index)) {
                        fermentableIndexes.add(index);
                    }
                } else if (isAHopEntry(entry)) {
                    if (!hopIndexes.contains(index)) {
                        hopIndexes.add(index);
                    }
                } else if (isMiscEntry(entry)) {
                    if (!miscIndexes.contains(index)) {
                        miscIndexes.add(index);
                    }
                }
            }
        });

        // Now we need to build out the objects that go together to form a brew
        Brew brew = new Brew();

        // Build the list of BrweFermentables
        GenericDao fermentableDao = new GenericDao(Fermentable.class);
        for (String index : fermentableIndexes) {
            Fermentable fermentable = (Fermentable) fermentableDao.getById(Integer.parseInt(request.getParameter(String.format("fermentable-%s", index))));
            BrewFermentable brewFermentable = new BrewFermentable();
            BrewFermentableId brewFermentableId = new BrewFermentableId();

            brewFermentableId.setBrew(brew);
            brewFermentableId.setFermentable(fermentable);

            brewFermentable.setPk(brewFermentableId);
            brewFermentable.setBrew(brew);
            brewFermentable.setFermentable(fermentable);
            brewFermentable.setAmount(Float.parseFloat(request.getParameter(String.format("fermentable-amount-%s", index))));
            brewFermentable.setUnitOfMeasure(request.getParameter(String.format("fermentable-amount-units-%s", index)));

            brew.addBrewFermentable(brewFermentable);
        }

        // Build the list of hops
        GenericDao hopDao = new GenericDao(Hop.class);
        for (String index : hopIndexes) {
            Hop hop = (Hop) hopDao.getById(Integer.parseInt(request.getParameter(String.format("hop-%s", index))));
            BrewHop brewHop = new BrewHop();
            BrewHopId brewHopId = new BrewHopId();

            brewHopId.setBrew(brew);
            brewHopId.setHop(hop);

            brewHop.setBrew(brew);
            brewHop.setHop(hop);
            brewHop.setPk(brewHopId);
            brewHop.setAmount(Double.parseDouble(request.getParameter(String.format("hop-amount-%s", index))));
            brewHop.setAmountUnitOfMeasure(request.getParameter(String.format("hop-amount-units-%s", index)));
            brewHop.setMethod(request.getParameter(String.format("hop-method-%s", index)));
            brewHop.setTimeInBrew(Double.parseDouble(request.getParameter(String.format("hop-time-%s", index))));
            brewHop.setTimeUnitOfMeasure(request.getParameter(String.format("hop-time-units-%s", index)));

            brew.addBrewHop(brewHop);
        }

        // Build the list of misc ingredients
        GenericDao miscDao = new GenericDao(Misc.class);
        for (String index : miscIndexes) {
            Misc misc = (Misc) miscDao.getById(Integer.parseInt(request.getParameter(String.format("misc-%s", index))));
            BrewMisc brewMisc = new BrewMisc();
            BrewMiscId brewMiscId = new BrewMiscId();

            brewMiscId.setBrew(brew);
            brewMiscId.setMisc(misc);

            brewMisc.setBrew(brew);
            brewMisc.setMisc(misc);
            brewMisc.setPk(brewMiscId);
            brewMisc.setAmount(Double.parseDouble(request.getParameter(String.format("misc-amount-%s", index))));
            brewMisc.setAmountUnitOfMeasure(request.getParameter(String.format("misc-amount-units-%s", index)));
            brewMisc.setAdditionParameter(request.getParameter(String.format("misc-addition-%s", index)));
            brewMisc.setTimeInBrew(Double.parseDouble(request.getParameter(String.format("misc-time-%s", index))));
            brewMisc.setTimeUnitOfMeasure(request.getParameter(String.format("misc-time-units-%s", index)));

            brew.addBrewMisc(brewMisc);
        }

        // Grab the rest of the parameters and create the brew.
        GenericDao styleDao = new GenericDao(Style.class);
        GenericDao userDao = new GenericDao(User.class);
        GenericDao brewDao = new GenericDao(Brew.class);

        // TODO make sure to add water notes, yeast, and pitch notes.
        brew.setBrewName(request.getParameter("name"));
        brew.setDescription(request.getParameter("description"));
        brew.setStyle((Style) styleDao.getById(Integer.parseInt(request.getParameter("style"))));
        brew.setUser((User)userDao.findByPropertyEqual("username", request.getRemoteUser()).get(0));

        brewDao.create(brew);
    }

    private String determineIndex(String parameterName) {
        // Figure out the fermentable index
            // check characters of key, starting from the end.
            // keep moving back in the key until we reach a character that's not a number
            // All the characters we've found so far represent the fermentable index
        Stack<Character> indexChars = new Stack<>();

        for (int i = parameterName.length() - 1; i >= 0; --i) {
            char currentChar = parameterName.charAt(i);
            if (Character.isDigit(currentChar)) {
                // add it to the stack
                indexChars.push(currentChar);
            } else {
                // We have all of the digits of the index, so break the loop
                i = -1;
            }
        }

        StringBuilder indexAsString = new StringBuilder();
        while (!indexChars.empty()) {
            indexAsString.append(indexChars.pop());
        }

        return indexAsString.toString();
    }

    private boolean isAFermentable(String parameter) {
        return Pattern.compile("fermentable", Pattern.CASE_INSENSITIVE).matcher(parameter).find();
    }

    private boolean isAHopEntry(String parameter) {
        return Pattern.compile("hop", Pattern.CASE_INSENSITIVE).matcher(parameter).find();
    }

    private boolean isMiscEntry(String parameter) {
        return Pattern.compile("misc", Pattern.CASE_INSENSITIVE).matcher(parameter).find();
    }
}
