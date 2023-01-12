package com.fur.world_db_demo.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fur.world_db_demo.layer.service.impl.AuthUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuthRequestFilter extends OncePerRequestFilter {
	@Autowired
	private AuthUserDetailsService authUserDetailsService;

	@Autowired
	private JWTProvider jwtProvider;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (!isBypassAuthorization(request)) {
			String requestTokenHeader = request.getHeader("Authorization");
			String username = null;
			String jwtToken = null;
			// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
			// Token
			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
				try {
					username = jwtProvider.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					System.out.println("JWT Token has expired");
				}
				// Once we get the token validate it.
				doJWTValidation(username, jwtToken, request);

			} else if (requestTokenHeader != null && requestTokenHeader.startsWith("Basic ")) {
				String base64Credentials = requestTokenHeader.substring("Basic".length()).trim();
				String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
				String[] values = credentials.split(":", 2);
				doBasicValidation(values[0], values[1], request);
			} else {
				logger.warn("Token does not begin with Bearer/Basic String");
			}
		} else {
			this.setMasterAuth();
		}

		chain.doFilter(request, response);
	}

	// JWT
	private void doJWTValidation(String username, String jwtToken, HttpServletRequest request) {
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.authUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtProvider.validateToken(jwtToken, userDetails)) {
				configAuth(userDetails, request);
			}
		}
	}

	// Basic
	private void doBasicValidation(String userName, String password, HttpServletRequest request) {
		UserDetails userDetails = this.authUserDetailsService.loadUserByUsername(userName);
		if (bcryptEncoder.matches(password, userDetails.getPassword())) {
			configAuth(userDetails, request);
		}
	}

	// configure Spring Security to manually set authentication
	private void configAuth(UserDetails userDetails, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the Spring Security
		// Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}

	// request has BypassAuthorization
	@SuppressWarnings("deprecation")
	private boolean isBypassAuthorization(HttpServletRequest request) {
		String bypassAuthorization = request.getHeader("BypassAuthorization");
		if (StringUtils.isEmpty(bypassAuthorization)) {
			bypassAuthorization = request.getParameter("BypassAuthorization");
		}
		return Objects.nonNull(bypassAuthorization) && bypassAuthorization.equalsIgnoreCase("true");
	}

	// set Master Auth
	private void setMasterAuth() {
		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
				"Master", "", new ArrayList<>());
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
	}

}
