/**
 * flight search
 */
package com.lastminute.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lastminute.configuration.AppConfig;
import com.lastminute.configuration.AppInitializer;
import com.lastminute.configuration.TestContext;
import com.lastminute.controllers.FlightsController;
import com.lastminute.entities.Search;
import com.lastminute.services.FlightsServiceImpl;

/**
 *
 * @author ojuarez
 *
 */
//@RunWith(MockitoJUnitRunner.class)
// @ContextConfiguration(classes = { TestContext.class, AppConfig.class,
// AppInitializer.class })
// @ContextConfiguration(classes = { AppConfig.class, AppInitializer.class })
// @WebAppConfiguration
public class FlightsControllerTest {

	private MockMvc mockMvc;

	
	@InjectMocks
	private FlightsController flightsController;

	@Mock
	FlightsServiceImpl flightsServiceImpl;

	@Mock
	View mockView;

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {

	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(flightsController).setSingleView(mockView).build();

	}

	@After
	public void tearDown() {
	}

	 @Test
	 public void showFormTest() throws Exception {
	 mockMvc.perform(get("/").param("originAirport",
	 "Amsterdam").param("destinationAirport", "Frakfurt")
	 .param("adultPassengerNum", "1").param("childPassengerNum",
	 "0").param("infantPassengerNum", "0")
	 .param("daysPriorDepartureNum", "31")).andExpect(status().isOk());
	 }

	//
	@Test
	public void searchPricesTest() throws Exception {
		Search search = new Search("Amsterdam", "Frakfurt", 1, 0, 0, 31);

		mockMvc.perform(post("/allflights").contentType(MediaType.APPLICATION_JSON).content(json(search).getBytes()))
				.andExpect(status().isOk()).andExpect(view().name("foundflights"));;
		
	}

	private String json(Object o) throws IOException {
		ObjectWriter ow = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY).writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(o);
		return json;
	}

}
