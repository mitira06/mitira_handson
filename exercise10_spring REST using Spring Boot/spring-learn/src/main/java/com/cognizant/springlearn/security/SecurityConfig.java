package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	/**
	 * Hands on: Creating users and roles in Spring Security.
	 * Two in-memory users - admin/pwd (ROLE_ADMIN) and user/pwd (ROLE_USER).
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
				.and()
				.withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}

	/**
	 * Hands on: Authorize based on JWT.
	 * - /authenticate is accessible to both USER and ADMIN roles via HTTP Basic
	 *   (so they can obtain a token).
	 * - Every other request must be authenticated, validated through the
	 *   JwtAuthorizationFilter, which checks the "Bearer " token on the
	 *   Authorization header.
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().httpBasic().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated()
				.and()
				.addFilter(new JwtAuthorizationFilter(authenticationManager()));
	}

}
