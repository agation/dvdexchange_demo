package com.agjava.dvdexchange.dao;

import com.agjava.dvdexchange.model.entity.TakenItem;

import java.util.List;

public interface ITakenItemDao {
    void addTakenItem(TakenItem item);

    List<TakenItem> takenFromUser(long userId);

    List<TakenItem> takenByUser(long userId);

    void save(TakenItem item);

    void remove(TakenItem item);
}
