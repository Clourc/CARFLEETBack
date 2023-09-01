package com.project.carfleet.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date start_Date;
    private Date end_Date;


    public Reservations() {
    }

    public Reservations(Date start_Date, Date end_Date){

    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Date getStart_Date() {
        return start_Date;
    }


    public void setStart_Date(Date start_Date) {
        this.start_Date = start_Date;
    }


    public Date getEnd_Date() {
        return end_Date;
    }


    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH) //Relation to vehicle
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }


    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH) //Relation to user
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public UserEntity getUser() {
        return user;
    }


    public void setUser(UserEntity user) {
        this.user = user;
    }
}

