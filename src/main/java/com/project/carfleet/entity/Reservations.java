package com.project.carfleet.entity;

import jakarta.annotation.Generated;
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
    
    @ManyToOne //Relation to vehicule
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    public Vehicule getVehicule() {
        return vehicule;
    }


    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @ManyToOne //Relation to user
    @JoinColumn(name = "user_id")
    private User user;


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
}

