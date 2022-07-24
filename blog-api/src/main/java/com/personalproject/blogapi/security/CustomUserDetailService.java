package com.personalproject.blogapi.security;

import com.personalproject.blogapi.exceptions.ResourceNotFoundException;
import com.personalproject.blogapi.models.User;
import com.personalproject.blogapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ///loading user from database
        User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email: "+username,0));
        return user;
    }
}