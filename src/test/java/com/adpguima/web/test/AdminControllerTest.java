package com.adpguima.web.test;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.adpguima.web.config.AdminWebContextConfig;
import com.adpguima.web.config.RootContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AdminWebContextConfig.class, RootContextConfig.class })
@WebAppConfiguration
public class AdminControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testAdminContext() throws UnsupportedEncodingException, Exception {
		String expected = "Welcome admin";
		String actual = mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn().getResponse().getContentAsString();
		assertEquals(expected, actual);
	}

	@Test
	public void testWelcomeURI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/welcome/1"))
				.andExpect(MockMvcResultMatchers.view().name("welcome"))
				.andExpect(MockMvcResultMatchers.model().attribute("name", "André"));
	}

}
