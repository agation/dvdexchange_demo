package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.User;

import java.util.List;

public interface IUserDao {
    void save(User user);

    User getByName(String name);

    List<User> getAllUsers();
}
