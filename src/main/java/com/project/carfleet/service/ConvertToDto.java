package com.project.carfleet.service;

import com.project.carfleet.dto.ModelDto;
import com.project.carfleet.dto.VehicleDto;
import com.project.carfleet.entity.Model;
import com.project.carfleet.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertToDto {

    public ModelDto convertModelToDto(Model model){
        return new ModelDto(model.getImage(), model.getEnergy(), model.getType(), model.getModelName(), model.getNbDoors(), model.getNbSeats());
    }

    public FleetDto

    public VehicleDto convertVehicleToDto(Vehicle vehicle){
        return new VehicleDto(vehicle.getBrand(), vehicle.getLicencePlate(), vehicle.getFleet(), convertModelToDto(vehicle.getModel()));
    }

    public List<VehicleDto> convertListVehicleToDto(List<Vehicle> vehicles){
        List<VehicleDto> vehiclesDto = new ArrayList<>();
        for(Vehicle v : vehicles){
            ModelDto model = convertModelToDto(v.getModel());
            vehiclesDto.add(new VehicleDto(v.getBrand(), v.getLicencePlate(), v.getFleet().getPlace(), model));
        }
        return vehiclesDto;
    }
}
