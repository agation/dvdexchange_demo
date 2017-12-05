package com.agjava.dvdexchange.model;

import com.agjava.dvdexchange.model.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return user.getId();
    }

}
