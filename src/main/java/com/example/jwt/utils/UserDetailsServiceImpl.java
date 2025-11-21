package com.example.jwt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = this.userRepository.findByUsername(username);
        if(byUsername==null){
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(byUsername.getUsername()).password(byUsername.getPassword()).roles(byUsername.getRole()).build();
    }
}
