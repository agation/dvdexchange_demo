package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.Disk;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class DiskDao implements IDiskDao {

    private static final String SELECT_ALL_DISKS_QUERY = "FROM Disk as d ORDER BY d.id";

    private static final String SELECT_DISKS_BY_USER_ID_QUERY =
            "FROM Disk d where d not in (select ti.disk from TakenItem ti) and d.owner.id != :userId ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createDisk(Disk disk) {
        this.entityManager.persist(disk);
    }

    @Override
    public List<Disk> getAllDisks() {
        return entityManager.createQuery(SELECT_ALL_DISKS_QUERY, Disk.class).getResultList();
    }

    @Override
    public List<Disk> getDisksByUserId(long userId) {
        return this.entityManager.createQuery(
                SELECT_DISKS_BY_USER_ID_QUERY).setParameter("userId", userId).getResultList();
    }
}
