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
	
		List<Integer> cityIdsLits = airportsDAO.findcityIdsList(search.getOriginAirport(),
				search.getDestinationAirport());
		List<Flights> flightsList = findFlights(cityIdsLits);
	
		Map<Integer, PassengerTypes> passengerRules = lookForRulesApplied(search);
		double percentagePriceDays = lookForPercentage(search.getDaysPriorDepartureNum());
		Map<String, Double> infantPricesMap = new HashMap<String, Double>();
	
		if (search.getInfantPassengerNum() != 0) {
			List<String> airlinesCodeList = new ArrayList<String>();
			for (int i = 0; i < flightsList.size(); i++) {
				String code = flightsList.get(i).getFlightCode().substring(0, 2);
						airlinesCodeList.add(code);
			}
			infantPricesMap = lookForInfantPrice(airlinesCodeList);
		}
		List<FoundFlights> foundFlightsList = calculate(flightsList, passengerRules, percentagePriceDays,
				infantPricesMap, search);
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

		foundFlightsList = calculatePrice(foundFlightsList, flightsList, passengerRules, percentagePriceDays, search,
				infantPricesMap);
		return foundFlightsList;

	}

	private List<FoundFlights> calculatePrice(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
			Map<Integer, PassengerTypes> passengerRules, double percentagePriceDays, Search search,
			Map<String, Double> infantPricesMap) {
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		Integer three = new Integer(3);
		List<Double> numPsgrAndrulesPsgrTypesList = new ArrayList<Double>(6);

		if (passengerRules.containsKey(one) && passengerRules.containsKey(two)) {
		
			numPsgrAndrulesPsgrTypesList.add(0, new Double(search.getAdultPassengerNum()));
			numPsgrAndrulesPsgrTypesList.add(1, new Double(passengerRules.get(one).getPassengerTypePrice()));
			numPsgrAndrulesPsgrTypesList.add(2, new Double(search.getChildPassengerNum()));
			numPsgrAndrulesPsgrTypesList.add(3, new Double(passengerRules.get(two).getPassengerTypePrice()));
			numPsgrAndrulesPsgrTypesList.add(4, new Double(search.getInfantPassengerNum()));

			foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, numPsgrAndrulesPsgrTypesList,
					percentagePriceDays, infantPricesMap);

		} else if (passengerRules.containsKey(one)) {
		
			numPsgrAndrulesPsgrTypesList.add(0, new Double(search.getAdultPassengerNum()));
			numPsgrAndrulesPsgrTypesList.add(1, new Double(passengerRules.get(one).getPassengerTypePrice()));
			numPsgrAndrulesPsgrTypesList.add(2, new Double(0));
			numPsgrAndrulesPsgrTypesList.add(3, new Double(0));
			numPsgrAndrulesPsgrTypesList.add(4, new Double(search.getInfantPassengerNum()));

			foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, numPsgrAndrulesPsgrTypesList,
					percentagePriceDays, infantPricesMap);

		} else if (passengerRules.containsKey(two)) {
		

			numPsgrAndrulesPsgrTypesList.add(0, new Double(0));
			numPsgrAndrulesPsgrTypesList.add(1, new Double(0));
			numPsgrAndrulesPsgrTypesList.add(2, new Double(search.getChildPassengerNum()));
			numPsgrAndrulesPsgrTypesList.add(3, new Double(passengerRules.get(two).getPassengerTypePrice()));
			numPsgrAndrulesPsgrTypesList.add(4, new Double(search.getInfantPassengerNum()));

			foundFlightsList = calculateAndIterate(foundFlightsList, flightsList, numPsgrAndrulesPsgrTypesList,
					percentagePriceDays, infantPricesMap);
		}
		return foundFlightsList;

	}

	private List<FoundFlights> calculateAndIterate(List<FoundFlights> foundFlightsList, List<Flights> flightsList,
			List<Double> numPsgrAndrulesPsgrTypesList, double percentagePriceDays,
			Map<String, Double> infantPricesMap) {
		double price = 0.0f;

		for (int i = 0; i < flightsList.size(); i++) {
			FoundFlights foundFlights = new FoundFlights();
			foundFlights.setFoundFlightCode(flightsList.get(i).getFlightCode());
			if (infantPricesMap.isEmpty()) {
				numPsgrAndrulesPsgrTypesList.add(5, new Double(0));
			} else {
				double infantPrice = infantPricesMap.get(flightsList.get(i).getFlightCode().substring(0, 2));
				numPsgrAndrulesPsgrTypesList.add(5, new Double(infantPrice));
			}
			price = (numPsgrAndrulesPsgrTypesList.get(0) * numPsgrAndrulesPsgrTypesList.get(1)
					* flightsList.get(i).getFlightBasePrice() * percentagePriceDays)
					+ (numPsgrAndrulesPsgrTypesList.get(2) * numPsgrAndrulesPsgrTypesList.get(3)
							* flightsList.get(i).getFlightBasePrice() * percentagePriceDays
							+ numPsgrAndrulesPsgrTypesList.get(4) * numPsgrAndrulesPsgrTypesList.get(5));
			
			foundFlights.setFoundFlightTotalPrice(price);
			foundFlightsList.add(foundFlights);
		}

		return foundFlightsList;
	}

}