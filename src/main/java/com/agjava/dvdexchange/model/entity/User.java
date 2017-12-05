package com.agjava.dvdexchange.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @JsonIgnore
    @NotNull
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Disk> disks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<TakenItem> takenItems = new HashSet<>();

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Disk> getDisks() {
        return disks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TakenItem> getTakenItems() {
        return takenItems;
    }
}
