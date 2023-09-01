package com.project.carfleet.dto;

public class FleetDto {
    private Long id;
    private String place;

    public FleetDto(Long id, String place) {
        this.id = id;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
