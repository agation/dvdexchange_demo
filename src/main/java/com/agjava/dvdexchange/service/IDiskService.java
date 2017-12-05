package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.model.entity.Disk;

import java.util.List;

public interface IDiskService {
    void createDisk(String name);

    List<Disk> availableDisksForCurrentUser();
}
