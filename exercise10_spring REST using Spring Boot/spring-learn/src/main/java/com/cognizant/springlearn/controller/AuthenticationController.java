package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	/**
	 * Hands on: Create authentication service that returns JWT.
	 * GET /authenticate, credentials passed via HTTP Basic Authorization
	 * header, response contains the generated JWT token.
	 */
	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("Start");
		LOGGER.debug("authHeader: {}", authHeader);

		Map<String, String> map = new HashMap<String, String>();

		String user = getUser(authHeader);
		String token = generateJwt(user);
		map.put("token", token);

		LOGGER.info("End");
		return map;
	}

	/**
	 * Reads the Base64 encoded credentials following "Basic " in the
	 * Authorization header, decodes it, and returns the username portion
	 * (text before the colon).
	 */
	private String getUser(String authHeader) {
		LOGGER.info("Start");

		String encodedCredentials = authHeader.replace("Basic ", "");
		byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
		String decodedCredentials = new String(decodedBytes);

		String user = decodedCredentials.substring(0, decodedCredentials.indexOf(":"));
		LOGGER.debug("user: {}", user);

		LOGGER.info("End");
		return user;
	}

	/**
	 * Generates a JWT for the given user, signed with HS256, issued now,
	 * expiring 20 minutes (1,200,000 ms) from now.
	 */
	private String generateJwt(String user) {
		LOGGER.info("Start");

		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);

		// Set the token issue time as current time
		builder.setIssuedAt(new Date());

		// Set the token expiry as 20 minutes from now
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");

		String token = builder.compact();

		LOGGER.info("End");
		return token;
	}

}
