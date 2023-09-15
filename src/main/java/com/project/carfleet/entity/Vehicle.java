package com.project.carfleet.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    public Vehicle(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licencePlate;

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH) // Relation to fleet
    @JoinColumn(name = "fleet_id")
    private Fleet fleet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH) // Relation to model
    @JoinColumn(name = "model_id")
    private Model model;

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL) // Relation from reservation
    private List<Reservations> reservations;

    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }
}
