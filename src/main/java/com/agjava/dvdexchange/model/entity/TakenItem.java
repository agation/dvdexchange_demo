package com.agjava.dvdexchange.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TakenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    @OneToOne(optional = false)
    @JoinColumn(unique = true)
    private Disk disk;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }
}
