package com.project.carfleet.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity; 

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nbCp;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String nbLicence;
  

    public Wizard() {
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public int getNbCp() {
      return nbCp;
    }

    public void setNbCp(int nbCp) {
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

    public String getFleetId() {
      return fleetId;
    }

    public void setFleetId(String fleetId) {
      this.fleetId = fleetId;
    }

    // getters and setters omitted for brevity
}