package com.ssafy.manv.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ssafy.manv.service.JwtTokenService;


public class JwtAuthenticationFilter extends GenericFilterBean {

	@Autowired
	private JwtTokenService jwtTokenService;
	
	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);  
	
	public JwtAuthenticationFilter(JwtTokenService jwtTokenProvider) {
		this.jwtTokenService = jwtTokenProvider;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		String token = jwtTokenService.resolveToken((HttpServletRequest)request);
		logger.info("doFilter - " + token);
		if(token != null && jwtTokenService.validateToken(token)) {
			logger.info("doFilter in ");
			Authentication auth = jwtTokenService.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
	
}
