/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.sec;

import edu.mum.courseswitch.domain.User;
import edu.mum.courseswitch.service.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Component;

/**
 *
 * @author henok
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userService.getUser(username, password);
        if (user == null) {
            throw new BadCredentialsException("user not found");
        }
        if (password == null) {
            throw new BadCredentialsException("password is null");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("password didnt match");
        }
        //Collection<? extends GrantedAuthority> authorities = user
        /* if(name.equals("john") && password.equals("123")) {
         List<GrantedAuthority> grantedAuths= new ArrayList<>();
         grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
         Authentication auth= new UsernamePasswordAuthenticationToken(name, password,grantedAuths);
         return auth;
     } else {
         return null;
     } */

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        for (String role : userService.getUserRoles(username)) {
            grantedAuths.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
