package br.com.ezpet.nimbus21.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.addFilterAfter(csrfCookieFilter(), CsrfFilter.class);
		
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/", "/home", "/login")
		.permitAll()
		.anyRequest().authenticated().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
//	private Filter csrfHeaderFilter() {
//        return new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request,
//                    HttpServletResponse response, FilterChain filterChain)
//                    throws ServletException, IOException {
//                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
//                        .getName());
//                if (csrf != null) {
//                    Cookie cookie = new Cookie("XSRF-TOKEN", csrf.getToken());
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                }
//                filterChain.doFilter(request, response);
//            }
//        };
//    }
	
}
