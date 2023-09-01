
package com.project.carfleet.dto;

public class VehicleDto {

    private String brand;
    private String licencePlate;
    private String fleet;
    private ModelDto model;


    public VehicleDto(String brand, String licencePlate, String fleet, ModelDto model) {
        this.brand = brand;
        this.licencePlate = licencePlate;
        this.fleet = fleet;
        this.model = model;
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

    public ModelDto getModel() {
        return model;
    }

}
