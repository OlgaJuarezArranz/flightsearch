/**
 * flights search
 */
package com.lastminute.controllers;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lastminute.entities.FoundFlights;
import com.lastminute.entities.Search;
import com.lastminute.services.FlightsService;

/**
 * @author ojuarez
 *
 */
@Controller
@RequestMapping("/")
@SessionAttributes("fligcontr")
public class FlightsController {

    @Autowired
    FlightsService service;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = { "/allflights" }, method = RequestMethod.POST)
    public String searchPrices(Search search, ModelMap model) {
        // quitar
        Logger.getLogger(getClass().getName()).info("***-------" + search);
        Logger.getLogger(getClass().getName()).info("***-------" + search.getOriginAirport());
        // fin quitar

        try {
            // quitar
            Logger.getLogger(getClass().getName()).info("***1");
            // fin quitar
            List<FoundFlights> foundFlightsList = service.foundAndCalculateFlightsAndPrices(search);
            // quitar
            Logger.getLogger(getClass().getName()).info("***2");
            // fin quitar
            model.addAttribute("foundFlightsList", foundFlightsList);
            String msn = null;
            model.addAttribute("msn", msn);
        } catch (Exception ex) {
            // quitar
            // Logger.getLogger(getClass().getName()).info("there is not flights available");
            Logger.getLogger(getClass().getName()).info("ex:" + ex.getMessage());
            // quitar
            // String msn = "there is not flights available";
            String msn = ex.getMessage();
            model.addAttribute("msn", msn);
        }
        return "foundflights";
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        Search search = new Search();
        model.addAttribute("search", search);
        return "index";
    }
}
