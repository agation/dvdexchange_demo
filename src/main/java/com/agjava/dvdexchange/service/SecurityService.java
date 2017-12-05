package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.model.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityService implements ISecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public CurrentUser getLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof CurrentUser) {
            return (CurrentUser) userDetails;
        }

        log.debug("securityContext details not CurrentUser! userDetails: {}", userDetails);
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        log.info("Try auto login username: {}; password: {};", username, password);

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            log.info("Successfully {} logged in. Authenticate: {}", username, authenticate);
        }
    }
}
