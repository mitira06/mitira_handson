package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.springlearn.controller.CountryController;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

	@Autowired
	private CountryController countryController;

	@Autowired
	private MockMvc mvc;

	/**
	 * Doc 5: every endpoint now requires authentication, so tests pass
	 * HTTP Basic credentials (user/pwd) just like the doc's curl -u examples.
	 */
	private String basicAuthHeader(String username, String password) {
		String credentials = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
	}

	@Test
	void contextLoads() {
		assertNotNull(countryController);
	}

	@Test
	void testGetCountry() throws Exception {
		ResultActions actions = mvc.perform(get("/countries/india")
				.header("Authorization", basicAuthHeader("user", "pwd")));

		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").exists());
		actions.andExpect(jsonPath("$.code").value("IN"));
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	void testGetCountryException() throws Exception {
		ResultActions actions = mvc.perform(get("/countries/az")
				.header("Authorization", basicAuthHeader("user", "pwd")));

		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Country not found"));
	}

	@Test
	void testAddCountryValid() throws Exception {
		ResultActions actions = mvc.perform(post("/countries")
				.header("Authorization", basicAuthHeader("user", "pwd"))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"code\":\"IN\",\"name\":\"India\"}"));

		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.code").value("IN"));
	}

	@Test
	void testAddCountryInvalid() throws Exception {
		ResultActions actions = mvc.perform(post("/countries")
				.header("Authorization", basicAuthHeader("user", "pwd"))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"code\":\"I\",\"name\":\"India\"}"));

		actions.andExpect(status().isBadRequest());
		actions.andExpect(jsonPath("$.errors[0]").value("Country code should be 2 characters"));
	}

	/**
	 * Doc 5: Hands on - no credentials at all should be rejected.
	 */
	@Test
	void testGetCountriesUnauthenticated() throws Exception {
		ResultActions actions = mvc.perform(get("/countries"));

		actions.andExpect(status().isUnauthorized());
	}

	/**
	 * Doc 5: Hands on - GET /authenticate with valid Basic credentials
	 * returns a non-empty JWT token.
	 */
	@Test
	void testAuthenticate() throws Exception {
		ResultActions actions = mvc.perform(get("/authenticate")
				.header("Authorization", basicAuthHeader("user", "pwd")));

		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.token").exists());
	}

}
