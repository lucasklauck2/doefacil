package br.com.lucasklauck.doefacil.security;

import static java.util.Collections.emptyList;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lucasklauck.doefacil.dto.LoginDTO;
import br.com.lucasklauck.doefacil.service.JWTTokenAutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	AuthenticationProvider authenticationProvider;
	
	public LoginFilter(String url, AuthenticationProvider authenticationProvider) {
		super(new AntPathRequestMatcher(url));
		
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
		
		return authenticationProvider.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword(), emptyList()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		response.setContentType("application/json");
		JWTTokenAutenticacaoService.addToken(response, authResult.getName());
	}

}
