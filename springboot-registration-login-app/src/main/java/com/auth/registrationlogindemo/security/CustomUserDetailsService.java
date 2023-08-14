package com.auth.registrationlogindemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.registrationlogindemo.entity.Role;
import com.auth.registrationlogindemo.entity.User;
import com.auth.registrationlogindemo.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
        User userExist = userRepository.findByEmail(email);

        if (userExist != null) {
        	
            return new org.springframework.security.core.userdetails.User(userExist.getEmail(),
                    userExist.getPassword(),
                    mapRolesToAuthorities(userExist.getRoles()));
            
        }else{
        	
            throw new UsernameNotFoundException("Invalid username or password.");
            
        }
    }

    // to map roles to authorities
    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
    	
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        
        return mapRoles;
    }
}

