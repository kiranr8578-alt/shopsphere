package com.shopsphere.service;

import com.shopsphere.entity.User;
import com.shopsphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
                boolean find=user.getUsername().equals(username);
                if(!find){

                    throw new UsernameNotFoundException("Username not found");
//                    System.out.println("Username not found");
                }
 System.out.println("came still herr");
        // return a Spring Security User object
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())  // encrypted password
                .authorities("user")            // roles or authorities
                .build();
    }
}
