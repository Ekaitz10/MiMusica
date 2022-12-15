package com.example.demo.security.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Authentication authentication) throws IOException {
		var authorities = authentication.getAuthorities();
		var authAdmin = authorities.stream().filter(a -> a.getAuthority().equals("ROLE_ADMIN")).findFirst();
		var authUser = authorities.stream().filter(a -> a.getAuthority().equals("ROLE_USER")).findFirst();
		
		if (authAdmin.isPresent() || authUser.isPresent()) {
			httpServletResponse.sendRedirect("/perfil");
		} else {
			httpServletResponse.sendRedirect("/error");
		}
	}
}

