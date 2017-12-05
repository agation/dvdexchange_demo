package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.User;

public interface IUserDao {
    void save(User user);

    User getByName(String name);
}
