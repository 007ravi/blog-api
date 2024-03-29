package com.personalproject.blogapi.controllers;

import com.personalproject.blogapi.exceptions.ApiException;
import com.personalproject.blogapi.payloads.JwtAuthRequest;
import com.personalproject.blogapi.payloads.JwtAuthResponse;
import com.personalproject.blogapi.payloads.UserDto;
import com.personalproject.blogapi.security.JwtTokenHelper;
import com.personalproject.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse>createToken(
            @RequestBody JwtAuthRequest request
            ) throws Exception {

        this.authenticate(request.getUsername(),request.getPassword());


        UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());

        String token=this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

    }

    private void authenticate(String username,String password) throws Exception{
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);

        try {
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e){
            throw new ApiException("Invalid user name or passowrd");
        }
    }

    //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

        UserDto registerUser=this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
    }
}
