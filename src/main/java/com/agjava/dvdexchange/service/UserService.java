package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.dao.IUserDao;
import com.agjava.dvdexchange.model.UserSignUpForm;
import com.agjava.dvdexchange.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void createNewUser(UserSignUpForm signUpForm) {
        User user = new User();
        user.setName(signUpForm.getName());
        user.setPassword(this.passwordEncoder.encode(signUpForm.getPassword()));
        this.userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return this.userDao.getByName(username);
    }
}
