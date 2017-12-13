package com.agjava.dvdexchange.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Disk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @NotNull()
    private String name;

    @ManyToOne
    @NotNull
    private User owner;

    public Disk() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
