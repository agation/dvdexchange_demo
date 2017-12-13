package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class UserDao implements IUserDao {

    private static final String GET_USER_BY_NAME_QUERY = "From User u where u.name = :name";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        this.entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByName(String name) {
        User result = null;
        try {
            result = this.entityManager.createQuery(GET_USER_BY_NAME_QUERY, User.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException exc) {
            log.debug("Exception with select user by name: {};", name, exc);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return this.entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
