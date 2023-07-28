package br.com.lucasklauck.doefacil.service;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTTokenAutenticacaoService {

	private static final Long EXPERATION_TOKEN = 864000000L;

	public static final String SECRET_KEY = "L25552iiddd";

	public static final String TOKEN_PREFIX = "Bearer";

	public static final String HEADER_STRING = "Authorization";

	public static void addToken(HttpServletResponse response, String username) throws IOException {

		String token = getJWTToken(username);

		response.addHeader(HEADER_STRING, token);

		response.getWriter().write("{\"authorization\": \"" + token + "\"}");
	}

	public static String getJWTToken(String email) {
		
		String jwt = Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + EXPERATION_TOKEN))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		
		return TOKEN_PREFIX + " " + jwt;
	}
	
	public static String extractEmailFromToken(String token) {
		
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody().getSubject();
	}

	public static Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			String email = extractEmailFromToken(token);
			
			if(email != null) {
				
				return new UsernamePasswordAuthenticationToken(email, null, emptyList());
			}
		}
		
		return null;
	}

}
