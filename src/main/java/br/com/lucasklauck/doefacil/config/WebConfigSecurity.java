package br.com.lucasklauck.doefacil.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.lucasklauck.doefacil.security.AuthenticationFilter;
import br.com.lucasklauck.doefacil.security.LoginFilter;
import br.com.lucasklauck.doefacil.service.UserDetailServiceImpl;
import jakarta.servlet.http.HttpSessionListener;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebConfigSecurity implements HttpSessionListener{

	@Autowired
	private UserDetailServiceImpl userDetailsService;

    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	
         final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
         daoAuthenticationProvider.setUserDetailsService(userDetailsService);
         daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
         
         return daoAuthenticationProvider;
    }
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	
        return (web) -> web.ignoring().requestMatchers("/usuario/cadastrar");
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.cors().configurationSource(this.corsConfigurationSource()).and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests().requestMatchers("/login").permitAll();
        http.authorizeHttpRequests().requestMatchers("/usuario/cadastrar").permitAll();
        http.authorizeHttpRequests().requestMatchers("/**").authenticated();
        http.authorizeHttpRequests().anyRequest().permitAll();
        
        http.addFilterBefore(new LoginFilter("/login", authenticationProvider()),
				UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

	
	private CorsConfigurationSource corsConfigurationSource() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.applyPermitDefaultValues();

		source.registerCorsConfiguration("/**", config);

		return source;
	}
}
