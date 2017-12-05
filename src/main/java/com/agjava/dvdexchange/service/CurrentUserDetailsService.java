package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.model.CurrentUser;
import com.agjava.dvdexchange.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private IUserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with name '%s' was not found", username));
        }

        log.debug("Load user by name '{}'; with password '{}'", username, user.getPassword());

        return new CurrentUser(user);
    }
}
