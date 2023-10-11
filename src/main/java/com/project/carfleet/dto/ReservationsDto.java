package com.project.carfleet.dto;

import com.project.carfleet.entity.Reservations;

import java.util.Date;

public class ReservationsDto {

    private Long id;
    private Date start_Date;
    private Date end_Date;
    private String reason;
    private VehicleDto vehicle;
    private UserDto user;

    public ReservationsDto(Long id, Date start_Date, Date end_Date, String reason, VehicleDto vehicle, UserDto user) {
        this.id = id;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.reason = reason;
        this.vehicle = vehicle;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Date getStart_Date() {
        return start_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public VehicleDto getVehicle() {
        return vehicle;
    }

    public UserDto getUser() {
        return user;
    }

    public String getReason() {
        return reason;
    }
}
