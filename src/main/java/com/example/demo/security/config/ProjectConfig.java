package com.example.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.security.auth.CustomAuthenticationFailureHandler;
import com.example.demo.security.auth.CustomAuthenticationSuccessHandler;
import com.example.demo.security.filter.CsrfTokenLoggerFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).loginPage("/login");
		http.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class)
		.authorizeRequests()
		.mvcMatchers("/administrarcontenido").hasRole("ADMIN")
		.mvcMatchers("/").permitAll()
		.mvcMatchers("/login").permitAll()
		.mvcMatchers("/register").permitAll()
		.anyRequest().authenticated();
		http.logout(
				logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
				);
	}

}
