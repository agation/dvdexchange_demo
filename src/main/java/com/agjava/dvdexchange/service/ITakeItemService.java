package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.model.entity.Disk;
import com.agjava.dvdexchange.model.entity.TakenItem;

import java.util.List;

public interface ITakeItemService {

    List<TakenItem> takenDisksForCurrentUser();

    List<TakenItem> givenDisksForCurrentUser();

    void takeDisk(Disk disk);

    void returnDisk(TakenItem item);

}
