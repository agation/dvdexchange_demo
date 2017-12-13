package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.dao.IUserDao;
import com.agjava.dvdexchange.model.Role;
import com.agjava.dvdexchange.model.UserSignUpForm;
import com.agjava.dvdexchange.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setRole(Role.USER);
        this.userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {
        log.info("UserService#findByUserName '{}'", username);
        return this.userDao.getByName(username);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }
}
