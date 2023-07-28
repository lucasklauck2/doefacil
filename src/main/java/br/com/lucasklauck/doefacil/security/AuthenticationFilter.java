package br.com.lucasklauck.doefacil.security;


import static br.com.lucasklauck.doefacil.service.JWTTokenAutenticacaoService.HEADER_STRING;
import static br.com.lucasklauck.doefacil.service.JWTTokenAutenticacaoService.SECRET_KEY;
import static br.com.lucasklauck.doefacil.service.JWTTokenAutenticacaoService.TOKEN_PREFIX;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import br.com.lucasklauck.doefacil.service.JWTTokenAutenticacaoService;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse responseDois = (HttpServletResponse) response;
		
		Authentication authentication = null;
		
		try {
			
			authentication = JWTTokenAutenticacaoService.getAuthentication((HttpServletRequest) request);
			
		} catch (Exception e) {
			
			responseDois.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			return;
		}

		if (!isValidToken((HttpServletRequest) request)) {

			responseDois.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			return;
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);

	}

	private boolean isValidToken(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);

		if (token == null) {
			
			return false;
		}

		try {
			
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
			
		} catch (Exception e) {
			
			return false;
		}

		return true;
	}

}
