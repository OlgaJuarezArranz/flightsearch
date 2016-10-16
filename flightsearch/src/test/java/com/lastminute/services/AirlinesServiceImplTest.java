/**
 * flight search
 */
package com.lastminute.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lastminute.configuration.AppConfig;
import com.lastminute.configuration.AppInitializer;
import com.lastminute.daos.AirlinesDAO;
import com.lastminute.entities.Search;

/**
*
* @author ojuarez
*
*/
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { AppConfig.class, AppInitializer.class })
@WebAppConfiguration
public class AirlinesServiceImplTest {
	
	@InjectMocks
	private AirlinesServiceImpl airlinesServiceImplMock;
	
	@Mock
	private AirlinesDAO airlinesDAOMock;
	
	private List<String> airlinesIataCodeList;
	
	private Map<String, Double> infantPriceMap;
	
	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {

	}
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		airlinesIataCodeList = new ArrayList<String>();
		airlinesIataCodeList.add("IB");
		airlinesIataCodeList.add("BA");
		airlinesIataCodeList.add("LH");
		airlinesIataCodeList.add("FR");
		airlinesIataCodeList.add("VY");
		airlinesIataCodeList.add("TK");
		airlinesIataCodeList.add("U2");
		
		infantPriceMap = new HashMap<String, Double>();
		infantPriceMap.put("IB", new Double(10));
		infantPriceMap.put("BA", new Double(15));
		infantPriceMap.put("LH", new Double(7));
		infantPriceMap.put("FR", new Double(20));
		infantPriceMap.put("VY", new Double(10));
		infantPriceMap.put("TK", new Double(5));
		infantPriceMap.put("U2", new Double(19.9));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void lookForInfantPriceTest () {
						
		when(airlinesDAOMock.lookForInfantPrice(airlinesIataCodeList)).thenReturn(infantPriceMap);
		
		 assertNotNull(airlinesDAOMock.lookForInfantPrice(airlinesIataCodeList));
		 assertThat(airlinesDAOMock.lookForInfantPrice(airlinesIataCodeList), instanceOf(Map.class));
		 
		
	}
}
