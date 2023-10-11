package com.project.carfleet.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Fleet {

    public Fleet(String place) {
        this.place = place;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String place;

    public Fleet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @OneToMany(mappedBy = "fleet", cascade = CascadeType.ALL) //Relation from vehicle
    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @OneToMany(mappedBy = "fleet", cascade = CascadeType.ALL) //Relation from user
    private List<UserEntity> users;

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
