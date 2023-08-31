
package com.project.carfleet.dto;

public class VehiculeDTO {

    private Long id;
    private String brand;
    private String licencePlate;
    private String fleet;
    private ModelDto model;

    public VehiculeDTO(Long id, String brand, String licencePlate, String fleet, ModelDto model) {
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

    public ModelDto getModel() {
        return model;
    }

}
