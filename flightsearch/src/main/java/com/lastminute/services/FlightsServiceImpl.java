/**
 * flights search
 */
package com.lastminute.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lastminute.daos.AirlinesDAO;
import com.lastminute.daos.AirportsDAO;
import com.lastminute.daos.DaysPriorDepartureDateDAO;
import com.lastminute.daos.FlightsDAO;
import com.lastminute.daos.PassengerTypesDAO;
import com.lastminute.entities.Flights;
import com.lastminute.entities.FoundFlights;
import com.lastminute.entities.PassengerTypes;
import com.lastminute.entities.Search;

/**
 * @author ojuarez
 *
 */
@Service("flightsService")
@Transactional
public class FlightsServiceImpl implements FlightsService {

    @Autowired
    private FlightsDAO flightsDAO;

    @Autowired
    private AirportsDAO airportsDAO;

    @Autowired
    private PassengerTypesDAO passengerTypesDAO;

    @Autowired
    private DaysPriorDepartureDateDAO daysPriorDepartureDateDAO;

    @Autowired
    private AirlinesDAO airlinesDAO;

    @Override
    public List<Flights> findFlights(List<Integer> cityIdsList) throws Exception {
        return flightsDAO.findFlights(cityIdsList);
    }

    @Override
    public List<FoundFlights> foundAndCalculateFlightsAndPrices(Search search) throws Exception {
        List<Integer> cityIdsLits = airportsDAO.findcityIdsList(search.getOriginAirport(), search.getDestinationAirport());
        List<Flights> flightsList = findFlights(cityIdsLits);
        // quitar
        for (int i = 0; i < flightsList.size(); i++) {
            Logger.getLogger(getClass().getName())
                            .info(" /-/-/ la lista de los vuelos encontrados entre la ciudad de origen y la ciudad de destino: id= "
                                            + flightsList.get(i).getAirportsByFlightDestinationAirportId()
                                            + " id de la ciudad de origen= "
                                            + flightsList.get(i).getAirportsByFlightOriginAirportId()
                                            + " id de la ciudad de destino= "
                                            + flightsList.get(i).getAirportsByFlightDestinationAirportId()
                                            + " codigo de vuelo= " + flightsList.get(i).getFlightCode()
                                            + " precio base del vuelo= " + flightsList.get(i).getFlightBasePrice());
        }
        // fin quitar
        Map<Integer, PassengerTypes> passengerRules = lookForRulesApplied(search);
        double percentagePriceDays = lookForPercentage(search.getDaysPriorDepartureNum());
        Map<String, Double> infantPricesMap = new HashMap<String, Double>();
        // quitar

        Logger.getLogger(getClass().getName()).info(
                        " /-/-/ numero de bebes en la busqueda desde el servicio de vuelos: " + search.getInfantPassengerNum());
        // fin quitar
        if (search.getInfantPassengerNum() != 0) {
            List<String> airlinesCodeList = new ArrayList<String>();
            for (int i = 0; i < flightsList.size(); i++) {
                String code = flightsList.get(i).getFlightCode().substring(0, 2);
                // quitar

                Logger.getLogger(getClass().getName()).info(" /-/-/ numero de vuelo: " + flightsList.get(i).getFlightCode()
                                + " codigo de la aerolinea: " + code);
                // fin quitar
                airlinesCodeList.add(code);
            }
            infantPricesMap = lookForInfantPrice(airlinesCodeList);
        }
        List<FoundFlights> foundFlightsList = calculate2(flightsList, passengerRules, percentagePriceDays, infantPricesMap,
                        search);
        return foundFlightsList;
    }

    private Map<Integer, PassengerTypes> lookForRulesApplied(Search search) {
        return passengerTypesDAO.lookForRulesApplied(search);
    }

    private double lookForPercentage(int daysPriorDepartureDate) {
        return daysPriorDepartureDateDAO.lookForPercentage(daysPriorDepartureDate);
    }

    private Map<String, Double> lookForInfantPrice(List<String> airlinesCodeList) {
        return airlinesDAO.lookForInfantPrice(airlinesCodeList);
    }

    private List<FoundFlights> calculate(List<Flights> flightsList, Map<Integer, PassengerTypes> passengerRules,
                    double percentagePriceDays, Map<String, Double> infantPricesMap, Search search) {

        List<FoundFlights> foundFlightsList = new ArrayList<FoundFlights>();
        // flightList is a list with all flights between city a and city b
        // passengerRules is a map with the types of passenger in this search,
        // the id is the key
        // percentagePriceDays is the percentage applied to this search due to
        // the days rule
        // infantPricesMap is a map with prices to infant in the airlines where
        // they found flights

        for (int i = 0; i < flightsList.size(); i++) {
            FoundFlights foundFlights = new FoundFlights();
            foundFlights.setFoundFlightCode(flightsList.get(i).getFlightCode());
            Integer one = new Integer(1);
            Integer two = new Integer(2);
            double price = 0.0f;
            // quitar
            Logger.getLogger(getClass().getName())
                            .info(" /-/-/ antes del if maldito " + infantPricesMap + " " + infantPricesMap.isEmpty());
            // fin quitar
            if (infantPricesMap.isEmpty()) {
                // there is not infant in this search
                // quitar
                Logger.getLogger(getClass().getName()).info("entro por aqui porque no hay bebes");
                // fin quitar
                if (passengerRules.containsKey(one) && passengerRules.containsKey(two)) {
                    // quitar
                    Logger.getLogger(getClass().getName()).info("*******************");
                    Logger.getLogger(getClass().getName()).info("no hay bebes, primera parte del if");
                    Logger.getLogger(getClass().getName()).info("numAdultos: " + search.getAdultPassengerNum());
                    Logger.getLogger(getClass().getName()).info("mapaDePasajero, clave one: " + passengerRules.get(one));
                    Logger.getLogger(getClass().getName()).info(
                                    "mapaDePasejero, clave one, precio: " + passengerRules.get(one).getPassengerTypePrice());
                    Logger.getLogger(getClass().getName()).info("listaDeVuelo:  " + flightsList.get(i));
                    Logger.getLogger(getClass().getName())
                                    .info("listaDeVuelo, precio: " + flightsList.get(i).getFlightBasePrice());
                    Logger.getLogger(getClass().getName()).info("numNinnos: " + search.getChildPassengerNum());
                    Logger.getLogger(getClass().getName()).info("mapaDePasajero, clave two: " + passengerRules.get(two));
                    Logger.getLogger(getClass().getName()).info(
                                    "mapaDePasejero, clave two, precio: " + passengerRules.get(two).getPassengerTypePrice());
                    // Logger.getLogger(getClass().getName()).info
                    // Logger.getLogger(getClass().getName()).info
                    Logger.getLogger(getClass().getName()).info("*******************");
                    // fin quitar
                    price = (search.getAdultPassengerNum() * passengerRules.get(one).getPassengerTypePrice()
                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                                    + (search.getChildPassengerNum() * passengerRules.get(two).getPassengerTypePrice()
                                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays);
                } else if (passengerRules.containsKey(one)) {
                    // quitar
                    Logger.getLogger(getClass().getName()).info("*******************");
                    Logger.getLogger(getClass().getName()).info("no hay bebes, segunda parte del if");
                    Logger.getLogger(getClass().getName()).info("a: " + search.getAdultPassengerNum());
                    Logger.getLogger(getClass().getName()).info("b: " + passengerRules.get(one).getPassengerTypePrice());
                    Logger.getLogger(getClass().getName()).info("c: " + flightsList.get(i).getFlightBasePrice());
                    Logger.getLogger(getClass().getName()).info("*******************");
                    // fin quitar
                    price = (search.getAdultPassengerNum() * passengerRules.get(one).getPassengerTypePrice()
                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays);
                    // quitar
                    Logger.getLogger(getClass().getName()).info("----------------");
                    Logger.getLogger(getClass().getName()).info("price=a*b*c= " + price);
                    Logger.getLogger(getClass().getName()).info("----------------");
                    // fin quitar
                } else if (passengerRules.containsKey(two)) {
                    // quitar
                    Logger.getLogger(getClass().getName()).info("*******************");
                    Logger.getLogger(getClass().getName()).info("no hay bebes, tercera parte del if");
                    Logger.getLogger(getClass().getName()).info("*******************");
                    // fin quitar
                    price = (search.getChildPassengerNum() * passengerRules.get(two).getPassengerTypePrice()
                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays);
                }
                foundFlights.setFoundFlightTotalPrice(price);
            } else {
                // there are infant is this search
                // quitar

                Logger.getLogger(getClass().getName()).info("entro por aqui porque hay bebes");
                // fin quitar
                Integer three = new Integer(3);
                double infantPrice = infantPricesMap.get(flightsList.get(i).getFlightCode().substring(0, 2));
                if (passengerRules.containsKey(one) && passengerRules.containsKey(two)) {
                    // quitar
                    Logger.getLogger(getClass().getName()).info("*******************");
                    Logger.getLogger(getClass().getName()).info("no hay bebes, primera parte del if");
                    Logger.getLogger(getClass().getName()).info("*******************");
                    // fin quitar
                    price = (search.getAdultPassengerNum() * passengerRules.get(one).getPassengerTypePrice()
                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                                    + (search.getChildPassengerNum() * passengerRules.get(two).getPassengerTypePrice()
                                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                                    + (search.getInfantPassengerNum() * infantPrice);
                } else if (passengerRules.containsKey(one)) {
                    // quitar
                    Logger.getLogger(getClass().getName()).info("*******************");
                    Logger.getLogger(getClass().getName()).info("no hay bebes, segunda parte del if");
                    Logger.getLogger(getClass().getName()).info("*******************");
                    // fin quitar
                    price = (search.getAdultPassengerNum() * passengerRules.get(one).getPassengerTypePrice()
                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                                    + (search.getInfantPassengerNum() * infantPrice);
                } else if (passengerRules.containsKey(two)) {
                    // quitar
                    Logger.getLogger(getClass().getName()).info("*******************");
                    Logger.getLogger(getClass().getName()).info("no hay bebes, tercera parte del if");
                    Logger.getLogger(getClass().getName()).info("*******************");
                    // fin quitar
                    price = (search.getChildPassengerNum() * passengerRules.get(two).getPassengerTypePrice()
                                    * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                                    + (search.getInfantPassengerNum() * infantPrice);
                }

            }
            // quitar
            Logger.getLogger(getClass().getName()).info(" /-/-/ precio total calculado por iteracion: " + price);
            // fin quitar
            foundFlights.setFoundFlightTotalPrice(price);
            foundFlightsList.add(foundFlights);
        }
        // quitar
        for (FoundFlights ff : foundFlightsList) {
            Logger.getLogger(getClass().getName())
                            .info(" /-/-/ la lista de los vuelos encontrados con su precio para devolver: codigo= "
                                            + ff.getFoundFlightCode() + " precio= " + ff.getFoundFlightTotalPrice());
        }
        // fin quitar

        return foundFlightsList;
    }

    private List<FoundFlights> calculate2(List<Flights> flightsList, Map<Integer, PassengerTypes> passengerRules,
                    double percentagePriceDays, Map<String, Double> infantPricesMap, Search search) {

        List<FoundFlights> foundFlightsList = new ArrayList<FoundFlights>();

        // flightList is a list with all flights between city a and city b
        // passengerRules is a map with the types of passenger in this search,
        // the id is the key
        // percentagePriceDays is the percentage applied to this search due to
        // the days rule
        // infantPricesMap is a map with prices to infant in the airlines where
        // they found flights

        // codigo refactorizado
        if (infantPricesMap.isEmpty()) {
            // there is not infant in this search
            // quitar
            Logger.getLogger(getClass().getName()).info("entro por aqui porque no hay bebes");
            // fin quitar
            foundFlightsList = calculatePrice(foundFlightsList, flightsList, passengerRules, percentagePriceDays, search);

        } else {
            // there are infant is this search
            // quitar
            Logger.getLogger(getClass().getName()).info("entro por aqui porque hay bebes");
            // fin quitar
            foundFlightsList = calculatePrice(foundFlightsList, flightsList, passengerRules, percentagePriceDays, search,
                            infantPricesMap);
        }
        // quitar
        for (FoundFlights ff : foundFlightsList) {
            Logger.getLogger(getClass().getName())
                            .info(" /-/-/ la lista de los vuelos encontrados con su precio para devolver: codigo= "
                                            + ff.getFoundFlightCode() + " precio= " + ff.getFoundFlightTotalPrice());
        }
        // fin quitar

        return foundFlightsList;

    }

    private List<FoundFlights> calculatePrice(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
                    Map<Integer, PassengerTypes> passengerRules, double percentagePriceDays, Search search) {

        Integer one = new Integer(1);
        Integer two = new Integer(2);
        // quitar
        Logger.getLogger(getClass().getName()).info(
                        " /*/*/*/*/**/*/*/*/*/     " + (passengerRules.containsKey(one) && passengerRules.containsKey(two)));
        Logger.getLogger(getClass().getName()).info(" /*/*/*/*/**/*/*/*/*/     " + (passengerRules.containsKey(one)));
        Logger.getLogger(getClass().getName()).info(" /*/*/*/*/**/*/*/*/*/     " + (passengerRules.containsKey(two)));
        // fin quitar
        if (passengerRules.containsKey(one) && passengerRules.containsKey(two)) {
            foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, search.getAdultPassengerNum(),
                            passengerRules.get(one).getPassengerTypePrice(), search.getChildPassengerNum(),
                            passengerRules.get(two).getPassengerTypePrice(), percentagePriceDays);

        } else if (passengerRules.containsKey(one)) {
            foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, search.getAdultPassengerNum(),
                            passengerRules.get(one).getPassengerTypePrice(), percentagePriceDays);

        } else if (passengerRules.containsKey(two)) {
            foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, search.getChildPassengerNum(),
                            passengerRules.get(two).getPassengerTypePrice(), percentagePriceDays);
        }
        return foundFlightsList;

    }

    private List<FoundFlights> calculatePrice(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
                    Map<Integer, PassengerTypes> passengerRules, double percentagePriceDays, Search search,
                    Map<String, Double> infantPricesMap) {
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        double price = 0.0f;

        if (passengerRules.containsKey(one) && passengerRules.containsKey(two)) {
            // quitar
            Logger.getLogger(getClass().getName()).info("*******************");
            Logger.getLogger(getClass().getName()).info("no hay bebes, primera parte del if");
            Logger.getLogger(getClass().getName()).info("*******************");
            // fin quitar
            foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, search.getAdultPassengerNum(),
                            passengerRules.get(one).getPassengerTypePrice(), search.getChildPassengerNum(),
                            passengerRules.get(two).getPassengerTypePrice(), percentagePriceDays,
                            search.getInfantPassengerNum(), infantPricesMap);

        } else if (passengerRules.containsKey(one)) {
            // quitar
            Logger.getLogger(getClass().getName()).info("*******************");
            Logger.getLogger(getClass().getName()).info("no hay bebes, segunda parte del if");
            Logger.getLogger(getClass().getName()).info("*******************");
            foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, search.getAdultPassengerNum(),
                            passengerRules.get(one).getPassengerTypePrice(), percentagePriceDays,
                            search.getInfantPassengerNum(), infantPricesMap);
            // fin quitar

        } else if (passengerRules.containsKey(two)) {
            // quitar
            Logger.getLogger(getClass().getName()).info("*******************");
            Logger.getLogger(getClass().getName()).info("no hay bebes, tercera parte del if");
            Logger.getLogger(getClass().getName()).info("*******************");
            // fin quitar
            foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, search.getChildPassengerNum(),
                            passengerRules.get(two).getPassengerTypePrice(), percentagePriceDays,
                            search.getInfantPassengerNum(), infantPricesMap);
        }
        return foundFlightsList;
        // fin codigo refactorizado
    }

    private List<FoundFlights> calculateAndIterate(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
                    int numPassengerType1, double pricePassengerType1, int numPassengerType2, double pricePassengerType2,
                    double percentagePriceDays) {
        double price = 0.0f;
        for (int i = 0; i < flightsList.size(); i++) {
            // quitar
            Logger.getLogger(getClass().getName()).info("*******************");
            Logger.getLogger(getClass().getName()).info("no hay bebes, primera parte del if");
            Logger.getLogger(getClass().getName()).info("numAdultos: " + numPassengerType1);
            Logger.getLogger(getClass().getName()).info("mapaDePasejero, clave one, precio: " + pricePassengerType1);
            Logger.getLogger(getClass().getName()).info("listaDeVuelo:  " + flightsList.get(i));
            Logger.getLogger(getClass().getName()).info("listaDeVuelo, precio: " + flightsList.get(i).getFlightBasePrice());
            Logger.getLogger(getClass().getName()).info("numNinnos: " + numPassengerType2);
            Logger.getLogger(getClass().getName()).info("mapaDePasejero, clave two, precio: " + pricePassengerType2);
            // Logger.getLogger(getClass().getName()).info
            // Logger.getLogger(getClass().getName()).info
            Logger.getLogger(getClass().getName()).info("*******************");
            // fin quitar
            FoundFlights foundFlights = new FoundFlights();
            foundFlights.setFoundFlightCode(flightsList.get(i).getFlightCode());

            price = (numPassengerType1 * pricePassengerType1 * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                            + (numPassengerType2 * pricePassengerType2 * flightsList.get(i).getFlightBasePrice()
                                            * percentagePriceDays);
            // quitar
            Logger.getLogger(getClass().getName()).info(" /-/-/ precio total calculado por iteracion: " + price);
            // fin quitar
            foundFlights.setFoundFlightTotalPrice(price);
            foundFlightsList.add(foundFlights);
        }
        return foundFlightsList;
    }

    private List<FoundFlights> calculateAndIterate(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
                    int numPassengerType1, double pricePassengerType1, double percentagePriceDays) {
        double price = 0.0f;
        for (int i = 0; i < flightsList.size(); i++) {
            // quitar
            Logger.getLogger(getClass().getName()).info("*******************");
            Logger.getLogger(getClass().getName()).info("no hay bebes, primera parte del if");
            Logger.getLogger(getClass().getName()).info("numAdultos: " + numPassengerType1);
            Logger.getLogger(getClass().getName()).info("mapaDePasejero, clave one, precio: " + pricePassengerType1);
            Logger.getLogger(getClass().getName()).info("listaDeVuelo:  " + flightsList.get(i));
            Logger.getLogger(getClass().getName()).info("listaDeVuelo, precio: " + flightsList.get(i).getFlightBasePrice());
            Logger.getLogger(getClass().getName()).info("*******************");
            // fin quitar
            FoundFlights foundFlights = new FoundFlights();
            foundFlights.setFoundFlightCode(flightsList.get(i).getFlightCode());

            price = (numPassengerType1 * pricePassengerType1 * flightsList.get(i).getFlightBasePrice() * percentagePriceDays);

            // quitar
            Logger.getLogger(getClass().getName()).info(" /-/-/ precio total calculado por iteracion: " + price);
            // fin quitar
            foundFlights.setFoundFlightTotalPrice(price);
            foundFlightsList.add(foundFlights);
        }
        return foundFlightsList;
    }

    private List<FoundFlights> calculateAndIterate(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
                    int numPassengerType1, double pricePassengerType1, int numPassengerType2, double pricePassengerType2,
                    double percentagePriceDays, int numPassengerType3, Map<String, Double> infantPricesMap) {
        double price = 0.0f;
        for (int i = 0; i < flightsList.size(); i++) {
            FoundFlights foundFlights = new FoundFlights();
            double infantPrice = infantPricesMap.get(flightsList.get(i).getFlightCode().substring(0, 2));
            foundFlights.setFoundFlightCode(flightsList.get(i).getFlightCode());

            price = (numPassengerType1 * pricePassengerType1 * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                            + (numPassengerType2 * pricePassengerType2 * flightsList.get(i).getFlightBasePrice()
                                            * percentagePriceDays)
                            + (numPassengerType3 * infantPrice);
            // quitar
            Logger.getLogger(getClass().getName()).info(" /-/-/ precio total calculado por iteracion: " + price);
            // fin quitar
            foundFlights.setFoundFlightTotalPrice(price);
            foundFlightsList.add(foundFlights);
        }
        return foundFlightsList;
    }

    private List<FoundFlights> calculateAndIterate(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
                    int numPassengerType1, double pricePassengerType1, double percentagePriceDays, int numPassengerType3,
                    Map<String, Double> infantPricesMap) {
        double price = 0.0f;
        for (int i = 0; i < flightsList.size(); i++) {
            FoundFlights foundFlights = new FoundFlights();
            double infantPrice = infantPricesMap.get(flightsList.get(i).getFlightCode().substring(0, 2));
            foundFlights.setFoundFlightCode(flightsList.get(i).getFlightCode());

            price = (numPassengerType1 * pricePassengerType1 * flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
                            + (numPassengerType3 * infantPrice);
            // quitar
            Logger.getLogger(getClass().getName()).info(" /-/-/ precio total calculado por iteracion: " + price);
            // fin quitar
            foundFlights.setFoundFlightTotalPrice(price);
            foundFlightsList.add(foundFlights);
        }
        return foundFlightsList;
    }
}