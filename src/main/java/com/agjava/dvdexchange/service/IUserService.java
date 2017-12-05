package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.model.UserSignUpForm;
import com.agjava.dvdexchange.model.entity.User;

public interface IUserService {
    void createNewUser(UserSignUpForm signUpForm);

    User findByUserName(String username);
}
