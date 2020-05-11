package dev.innate.util;

import dev.innate.entity.*;
import dev.innate.persistance.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class grabs from and populates data from the HttpRequest related to the CreateBrew form.
 */
public class BrewFormUtil {
    /**
     * Add form select options to request.
     *
     * @param request the request
     */
    public void addFormSelectOptionsToRequest(HttpServletRequest request) {
        getFermentableSelectOptions(request);
        getHopSelectOptions(request);
        getStyleSelectOptions(request);
        getYeastSelectOptions(request);
        getMiscSelectOptions(request);
    }

    /**
     * Gets the miscellaneous ingredient options
     *
     * @param request
     */
    private void getMiscSelectOptions(HttpServletRequest request) {
        // Get the other ingredient options.
        GenericDao miscDao = new GenericDao(Misc.class);
        List<Misc> otherIngredients = miscDao.getAll();
        request.setAttribute("otherIngredients", otherIngredients);
    }

    /**
     * Gets the miscellaneous ingredient options
     *
     * @param request
     */
    private void getYeastSelectOptions(HttpServletRequest request) {
        // Get the yeast options
        GenericDao yeastDao = new GenericDao(Yeast.class);
        List<Yeast> yeasts = yeastDao.getAll();
        request.setAttribute("yeasts", yeasts);
    }

    /**
     * Gets the Style options
     *
     * @param request
     */
    private void getStyleSelectOptions(HttpServletRequest request) {
        // Get the style options
        GenericDao styleDao = new GenericDao(Style.class);
        List<Style> styles = styleDao.getAll();
        request.setAttribute("styles", styles);
    }

    /**
     * Gets the Hop options
     *
     * @param request
     */
    private void getHopSelectOptions(HttpServletRequest request) {
        // Get the hops options
        GenericDao hopDao = new GenericDao(Hop.class);
        List<Hop> hops = hopDao.getAll();
        request.setAttribute("hops", hops);
    }

    /**
     * Gets the Fermentable options
     *
     * @param request
     */
    private void getFermentableSelectOptions(HttpServletRequest request) {
        // Get the fermentable options
        GenericDao fermentDao = new GenericDao(Fermentable.class);
        List<Fermentable> fermentables = fermentDao.getAll();
        request.setAttribute("fermentables", fermentables);
    }

    /**
     * Create brew from form.
     *
     * @param request the HttpServletRequest that contains the form data
     * @return the id of the newly created Brew
     */
    public int createBrewFromForm(HttpServletRequest request) {
        // Now we need to build out the objects that go together to form a brew
        GenericDao styleDao = new GenericDao(Style.class);
        GenericDao yeastDao = new GenericDao(Yeast.class);
        GenericDao userDao = new GenericDao(User.class);
        GenericDao brewDao = new GenericDao(Brew.class);

        // Build a brew
        BrewBuilder brewBuilder = new BrewBuilder();
        Brew brew = brewBuilder.withName(request.getParameter("name"))
                .withDescription(request.getParameter("description"))
                .withStyle((Style) styleDao.getById(Integer.parseInt(request.getParameter("style"))))
                .withUser((User)userDao.findByPropertyEqual("username", request.getRemoteUser()).get(0))
                .withYeast((Yeast) yeastDao.getById(Integer.parseInt(request.getParameter("yeast"))))
                .withPitchNotes(request.getParameter("yeast-pitch-notes"))
                .withWaterNotes(request.getParameter("water-notes"))
                .build();

        getBrewFermentablesFromForm(request, brew);
        getBrewHopsFromForm(request, brew);
        getBrewMiscFromForm(request, brew);

        // Finally, sew it all together into a brew
        return brewDao.create(brew);
    }

    /**
     * Gets the miscellaneous ingredients from the form
     *
     * @param request the HttpServletRequest that contains the form data
     * @param brew
     */
    private void getBrewMiscFromForm(HttpServletRequest request, Brew brew) {
        // Grab the misc item parallel arrays
        String[] miscIds = request.getParameterValues("misc");
        String[] miscAmounts = request.getParameterValues("misc-amount");
        String[] miscAmountUnits = request.getParameterValues("misc-amount-units");
        String[] miscTimes = request.getParameterValues("misc-time");
        String[] miscTimeUnits = request.getParameterValues("misc-time-units");
        String[] miscAdditionParameters = request.getParameterValues("misc-addition");


        // Build the list of misc ingredients
        if (miscIds != null && miscAmounts != null && miscAmountUnits != null && miscTimes != null
                && miscTimes != null && miscAdditionParameters != null){
            GenericDao miscDao = new GenericDao(Misc.class);
            for (int index = 0; index < miscIds.length; ++index) {
                Misc misc = (Misc) miscDao.getById(Integer.parseInt(miscIds[index]));

                BrewMisc brewMisc = new BrewMisc();
                brewMisc.setBrew(brew);
                brewMisc.setMisc(misc);

                brewMisc.setAmount(Double.parseDouble(miscAmounts[index]));
                brewMisc.setAmountUnitOfMeasure(miscAmountUnits[index]);
                brewMisc.setAdditionParameter(miscAdditionParameters[index]);
                brewMisc.setTimeInBrew(Double.parseDouble(miscTimes[index]));
                brewMisc.setTimeUnitOfMeasure(miscTimeUnits[index]);

                brew.addBrewMisc(brewMisc);
            }
        }
    }

    /**
     * Gets the Fermentables from the form
     *
     * @param request the HttpServletRequest that contains the form data
     * @param brew the instance of Brew they should be added to
     */
    private void getBrewFermentablesFromForm(HttpServletRequest request, Brew brew) {
        // Grab the lists of fermentable information (these should be parallel lists)
        String[] fermentableIds = request.getParameterValues("fermentable");
        String[] fermentableAmounts = request.getParameterValues("fermentable-amount");
        String[] fermentableAmountUnits = request.getParameterValues("fermentable-amount-units");

        // Loop through (classic, because we need the index) the parallel arrays, creating a fermentable object for each index
        if (fermentableIds != null && fermentableAmounts != null && fermentableAmountUnits != null){
            GenericDao fermentableDao = new GenericDao(Fermentable.class);
            for (int index = 0; index < fermentableIds.length; ++index) {
                Fermentable fermentable = (Fermentable) fermentableDao.getById(Integer.parseInt(fermentableIds[index]));

                BrewFermentable brewFermentable = new BrewFermentable();
                brewFermentable.setBrew(brew);
                brewFermentable.setFermentable(fermentable);
                brewFermentable.setAmount(Float.parseFloat(fermentableAmounts[index]));
                brewFermentable.setUnitOfMeasure(fermentableAmountUnits[index]);

                brew.addBrewFermentable(brewFermentable);
            }
        }
    }

    /**
     * Gets the Hops from the form
     *
     * @param request the HttpServletRequest that contains the form data
     * @param brew the instance of Brew they should be added to
     */
    private void getBrewHopsFromForm(HttpServletRequest request, Brew brew) {
        // Grab the set of hop parallel arrays
        String[] hopIds = request.getParameterValues("hop");
        String[] hopAmounts = request.getParameterValues("hop-amount");
        String[] hopAmountUnits = request.getParameterValues("hop-amount-units");
        String[] hopTimes = request.getParameterValues("hop-time");
        String[] hopTimeUnits = request.getParameterValues("hop-time-units");
        String[] hopMethods = request.getParameterValues("hop-method");

        // Build the list of hops
        if (hopIds != null
                && hopAmounts != null
                && hopAmountUnits != null
                && hopTimes != null
                && hopTimeUnits != null
                && hopMethods != null) {
            GenericDao hopDao = new GenericDao(Hop.class);
            for (int index = 0; index < hopIds.length; ++index) {
                Hop hop = (Hop) hopDao.getById(Integer.parseInt(hopIds[index]));

                BrewHop brewHop = new BrewHop();
                brewHop.setBrew(brew);
                brewHop.setHop(hop);
                brewHop.setAmount(Double.parseDouble(hopAmounts[index]));
                brewHop.setAmountUnitOfMeasure(hopAmountUnits[index]);
                brewHop.setMethod(hopMethods[index]);
                brewHop.setTimeInBrew(Double.parseDouble(hopTimes[index]));
                brewHop.setTimeUnitOfMeasure(hopTimeUnits[index]);

                brew.addBrewHop(brewHop);
            }
        }
    }

}
