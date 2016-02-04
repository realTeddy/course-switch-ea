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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

        String password = authentication.getCredentials().toString();
        String username = authentication.getName();
        if (password == null || "".equals(password) || username == null || "".equals(username)) {
            throw new BadCredentialsException("username/password cannot be empty");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        User user = userService.getUser(username);
        
        if (user == null || !encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username/password");
        }

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
