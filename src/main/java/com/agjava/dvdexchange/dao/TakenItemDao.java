package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.TakenItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Transactional
@Repository
public class TakenItemDao implements ITakenItemDao {

    private static final String SELECT_TAKEN_BY_USER_ID = "FROM TakenItem ti WHERE ti.user.id = :userId";

    private static final String SELECT_TAKEN_FROM_USER_ID = "FROM TakenItem ti WHERE ti.disk.owner.id = :userId";

    private static final String REMOVE_TAKEN_BY_ID = "delete from TakenItem ti where ti.id = :id";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addTakenItem(TakenItem item) {
        entityManager.persist(item);
    }

    @Override
    public List<TakenItem> takenFromUser(long userId) {
        return this.entityManager.createQuery(
                SELECT_TAKEN_FROM_USER_ID).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<TakenItem> takenByUser(long userId) {
        return this.entityManager.createQuery(
                SELECT_TAKEN_BY_USER_ID).setParameter("userId", userId).getResultList();
    }

    @Override
    public void save(TakenItem item) {
        this.entityManager.persist(item);
    }

    @Override
    public void remove(TakenItem item) {
        int deleteResult = this.entityManager.createQuery(
                REMOVE_TAKEN_BY_ID).setParameter("id", item.getId()).executeUpdate();
        log.info("Remove TakenItem (id: {}). Result: {}", item.getId(), deleteResult);
    }


}
