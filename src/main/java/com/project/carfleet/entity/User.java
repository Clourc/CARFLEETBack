package com.project.carfleet.entity;

import java.util.List;

import jakarta.persistence.*; 

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nbCp;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nbLicence;
  

    public User() {
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getNbCp() {
      return nbCp;
    }

    public void setNbCp(String nbCp) {
      this.nbCp = nbCp;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getNbLicence() {
      return nbLicence;
    }

    public void setNbLicence(String nbLicence) {
      this.nbLicence = nbLicence;
    }

    @ManyToOne //Relation to fleet
    @JoinColumn(name = "fleet_id")
    private Fleet fleet;

    public Fleet getFleet() {
      return fleet;
    }

    public void setFleet(Fleet fleet) {
      this.fleet = fleet;
    }

    @OneToMany(mappedBy = "user") //Relation from user
    private List<Reservations> reservations;


    public List<Reservations> getReservations() {
      return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
      this.reservations = reservations;
    }


}