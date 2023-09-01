package com.project.carfleet.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    public UserEntity(String CP, String firstName, String lastName, String email, String password, String phone, String nbLicence, Role role) {
        this.CP = CP;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.nbLicence = nbLicence;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    private String CP;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String nbLicence;


    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCp() {
        return CP;
    }

    public void setCp(String nbCp) {
        this.CP = CP;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH) //Relation to fleet
    @JoinColumn(name = "fleet_id")
    private Fleet fleet;

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //Relation from user
    private List<Reservations> reservations;


    public List<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservations> reservations) {
        this.reservations = reservations;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}