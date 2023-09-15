package com.project.carfleet.dto;

import com.project.carfleet.entity.Role;

public class UserDto {
    private Long id;
    private String CP;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String nbLicence;
    private Role role;
    public UserDto(Long id, String CP, String firstName, String lastName, String email, String password, String phone, String nbLicence, Role role){
        this.id = id;
        this.CP = CP;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.nbLicence = nbLicence;
        this.role = role;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
