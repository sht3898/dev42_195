package com.ssafy.manv.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberService memberService;
	
	private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("CustomUserDetail username = "+username);
		return memberService.findByEmail(username);
		//사용자가 DB에 없는경우.
		//https://odol87.tistory.com/7 loadUserByUsername 참고.
	}
	
}
