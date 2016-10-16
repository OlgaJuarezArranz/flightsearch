/**
 * flight search
 */
package com.lastminute.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.google.inject.matcher.Matchers;
import com.lastminute.configuration.AppConfig;
import com.lastminute.configuration.AppInitializer;
import com.lastminute.controllers.FlightsController;
import com.lastminute.entities.FoundFlights;
import com.lastminute.entities.Search;
import com.lastminute.services.FlightsService;
import com.lastminute.services.FlightsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author ojuarez
 *
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppInitializer.class })
@WebAppConfiguration
public class FlightsControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private FlightsController flightsController;

	@Mock
	private FlightsServiceImpl flightsServiceImpl;

	@Mock
	FlightsService service;

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {

	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(flightsController).setViewResolvers(viewResolver()).build();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void showFormTest() throws Exception {

		ModelMap model = new ModelMap();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/views/index.jsp"))
				.andExpect(model().attribute("search", instanceOf(Search.class)))
				.andExpect(model().attribute("search", hasProperty("originAirport", is((Matchers) null))))
				.andExpect(model().attribute("search", hasProperty("destinationAirport", is((Matchers) null))))
				.andExpect(model().attribute("search", hasProperty("adultPassengerNum", is(0))))
				.andExpect(model().attribute("search", hasProperty("childPassengerNum", is(0))))
				.andExpect(model().attribute("search", hasProperty("infantPassengerNum", is(0))))
				.andExpect(model().attribute("search", hasProperty("daysPriorDepartureNum", is(0)))).andDo(print());

		// verifyNoMoreInteractions(flightsServiceImpl);
	}

	//
	@Test
	public void searchPricesTest() throws Exception {

		Search search = new Search();

		List<FoundFlights> foundFlightsList = new ArrayList<FoundFlights>();
		FoundFlights foundFlights1 = new FoundFlights("TK2372", 157.6);
		FoundFlights foundFlights2 = new FoundFlights("TK2659", 198.4);
		FoundFlights foundFlights3 = new FoundFlights("LH5909", 90.4);
		foundFlightsList.add(foundFlights1);
		foundFlightsList.add(foundFlights2);
		foundFlightsList.add(foundFlights3);
		ModelMap model = new ModelMap();

		when(service.foundAndCalculateFlightsAndPrices(Mockito.any(Search.class))).thenReturn(foundFlightsList);

		mockMvc.perform(post("/allflights").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("originAirport", "Amsterdam").param("destinationAirport", "Frakfurt")
				.param("adultPassengerNum", "1").param("childPassengerNum", "0").param("infantPassengerNum", "0")
				.param("daysPriorDepartureNum", "31").sessionAttr("search", search).sessionAttr("model", model))
				.andExpect(status().isOk()).andExpect(view().name("foundflights"))
				.andExpect(forwardedUrl("/WEB-INF/views/foundflights.jsp"))
				.andExpect(model().attribute("foundFlightsList", hasSize(3)))
				.andExpect(model().attribute("foundFlightsList",
						hasItem(allOf(hasProperty("foundFlightCode", is("TK2372")),
								hasProperty("foundFlightTotalPrice", is(157.6))))))
				.andExpect(model().attribute("foundFlightsList",
						hasItem(allOf(hasProperty("foundFlightCode", is("TK2659")),
								hasProperty("foundFlightTotalPrice", is(198.4))))))
				.andExpect(model().attribute("foundFlightsList",
						hasItem(allOf(hasProperty("foundFlightCode", is("LH5909")),
								hasProperty("foundFlightTotalPrice", is(90.4))))))
				.andDo(print());
		// verify(flightsServiceImpl,
		// times(1)).foundAndCalculateFlightsAndPrices(search_);
		// verifyNoMoreInteractions(flightsServiceImpl);

	}

	private ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

}
