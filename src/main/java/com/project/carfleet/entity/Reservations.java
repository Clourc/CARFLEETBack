package com.project.carfleet.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import java.util.Date;
import jakarta.persistence.Entity;  

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
    
}

