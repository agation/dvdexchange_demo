package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.model.CurrentUser;

public interface ISecurityService {
    CurrentUser getLoggedInUser();

    void autoLogin(String username, String password);
}
