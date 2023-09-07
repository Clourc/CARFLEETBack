package com.project.carfleet.dto;

public class ModelDto {

    private Long id;
    private String brand;
    private String image;
    private String energy;
    private String type;
    private String modelName;
    private int nbDoors;
    private int nbSeats;


    public ModelDto(Long id, String brand, String image, String energy, String type, String modelName, int nbDoors, int nbSeats) {
        this.id = id;
        this.brand = brand;
        this.image = image;
        this.energy = energy;
        this.type = type;
        this.modelName = modelName;
        this.nbDoors = nbDoors;
        this.nbSeats = nbSeats;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getEnergy() {
        return energy;
    }


    public void setEnergy(String energy) {
        this.energy = energy;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getModelName() {
        return modelName;
    }


    public void setModelName(String modelName) {
        this.modelName = modelName;
    }


    public int getNbDoors() {
        return nbDoors;
    }


    public void setNbDoors(int nbDoors) {
        this.nbDoors = nbDoors;
    }


    public int getNbSeats() {
        return nbSeats;
    }


    public void setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
    }
}

