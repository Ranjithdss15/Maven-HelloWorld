package com.sowisetech.advisor.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sowisetech.advisor.model.Advisor;
import com.sowisetech.advisor.service.AdvisorService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	AdvisorService advisorService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Advisor adv=advisorService.fetchAdvisorByEmailId(username);
		
		if ((adv.getEmailId()).equals(username)) {
			return new User(adv.getEmailId(), adv.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
