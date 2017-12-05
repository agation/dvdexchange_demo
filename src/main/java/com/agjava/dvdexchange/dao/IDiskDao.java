package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.Disk;

import java.util.List;

public interface IDiskDao {
    void createDisk(Disk disk);

    List<Disk> getAllDisks();

    List<Disk> getDisksByUserId(long userId);
}
