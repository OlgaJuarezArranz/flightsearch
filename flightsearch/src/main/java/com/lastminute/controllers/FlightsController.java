/**
 * flights search
 */
package com.lastminute.controllers;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        Logger.getLogger(getClass().getName()).info("***-------" + search.getClass());
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
           
        } catch (Exception ex) {
            
            Logger.getLogger(getClass().getName()).info("ex:" + ex.getMessage());
        
         
        }
        return "foundflights";
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        Search search = new Search();
        model.addAttribute("search", search);
        return "index";
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public String handleException1(HttpMessageNotReadableException ex)
    {
        return ex.getMessage();
    }
}
