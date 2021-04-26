package com.example.ofd.service;

import com.example.ofd.entity.User;
import com.example.ofd.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepo.findByUsername(userName);
        if (user == null) {
            throw new BadCredentialsException("Error №3 - Пользователь " + userName + " не найден");
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Error №4 - Неверный пароль");
        }
        UserDetails principal = userRepo.findByUsername(userName);
        return new UsernamePasswordAuthenticationToken(
                principal, password, principal.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}