
package com.project.carfleet.dto;

public class VehicleDto {

    private Long id;
    private String licencePlate;
    private FleetDto fleet;
    private ModelDto model;


    public VehicleDto(Long id, String licencePlate, FleetDto fleet, ModelDto model) {
        this.id = id;
        this.licencePlate = licencePlate;
        this.fleet = fleet;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public FleetDto getFleet() {
        return fleet;
    }

    public ModelDto getModel() {
        return model;
    }

}
