package com.project.carfleet.dto;

public class VehicleDto {

    private Long id;
    private String brand;
    private String licencePlate;
    private String fleet;
    private String model;

    public VehicleDto(Long id, String brand, String licencePlate, String fleet, String model) {
        this.id = id;
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.fleet = fleet;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getFleet() {
        return fleet;
    }

    public String getModel() {
        return model;
    }

}
