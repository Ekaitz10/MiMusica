package com.example.demo.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestValidationFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		var httpRequest = (HttpServletRequest) servletRequest;
		var httpResponse = (HttpServletResponse) servletResponse;
		String requestId = httpRequest.getHeader("Request-Id");
		if (requestId == null || requestId.isBlank()) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}