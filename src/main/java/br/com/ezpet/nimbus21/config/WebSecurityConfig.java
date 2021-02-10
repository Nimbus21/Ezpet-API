package br.com.ezpet.nimbus21.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/usuarioAdmin/**")
		.permitAll()
		.anyRequest().authenticated().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
}
