package br.com.ezpet.nimbus21.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.addFilterAfter(csrfCookieFilter(), CsrfFilter.class);
	}
	
	// javax.servlet.Filter;
	protected Filter csrfCookieFilter() {
		return new CsrfCookieFilter();
	}
}
