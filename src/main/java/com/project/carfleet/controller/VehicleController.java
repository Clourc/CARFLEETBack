package com.project.carfleet.controller;

import com.project.carfleet.dto.ModelDto;
import com.project.carfleet.dto.VehicleDto;
import com.project.carfleet.entity.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.VehicleRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    public VehicleController(VehicleRepository injectedRepository) {
        this.vehicleRepository = injectedRepository;
    }

    @GetMapping(value = "/vehicles")
    @ResponseBody
    public List<VehicleDto> getVehicles(@RequestParam(defaultValue = "", required = false) String energy,
                                          @RequestParam(defaultValue = "", required = false) String type){
        boolean typeCheck = type.equals("citadine") || type.equals("fourgon") || type.equals("berline");
        boolean energyCheck = energy.equals("essence") || energy.equals("electric") || energy.equals("diesel");
        if((!type.isEmpty() && !typeCheck) || (!energy.isEmpty() && !energyCheck)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect parameter(s) provided");
        }
        if(!type.isEmpty()){
            if(!energy.isEmpty()){
                return convertListVehicleToDTO(vehicleRepository.findVehicleByTypeAndEnergy(type, energy));
            }
            return convertListVehicleToDTO(vehicleRepository.findVehicleByType(type));
        } else {
            if(!energy.isEmpty()){
                return convertListVehicleToDTO(vehicleRepository.findVehicleByEnergy(energy));
            }
        }
        return convertListVehicleToDTO(vehicleRepository.findAll());
    }

    @GetMapping("/vehicles/{id}")
    @ResponseBody
    public VehicleDto getVehicleById(@PathVariable Long id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isPresent()){
            Vehicle v = optionalVehicle.get();
            ModelDto model =  new ModelDto(v.getModel().getImage(), v.getModel().getEnergy(), v.getModel().getType(), v.getModel().getModelName(), v.getModel().getNbDoors(), v.getModel().getNbSeats());
            return new VehicleDto(v.getId(), v.getBrand(), v.getLicencePlate(), v.getFleet().getPlace(), model);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "vehicle not found");
        }
    }

    @PostMapping("/vehicles/add")
    @ResponseBody
    public Vehicle postVehicle(@RequestBody Vehicle vehicleToAdd){
        vehicleRepository.save(vehicleToAdd);
        return vehicleToAdd;
    }

    @DeleteMapping("vehicles/{id}/delete")
    public String deleteVehicleById(@PathVariable Long id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isPresent()){
            vehicleRepository.deleteById(id);
            return "Vehicle id=" + id + " deleted";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
    }

    private List<VehicleDto> convertListVehicleToDTO(List<Vehicle> vehicles){
        List<VehicleDto> vehiclesDTO = new ArrayList<>();
        for(Vehicle v : vehicles){
            ModelDto model =  new ModelDto(v.getModel().getImage(), v.getModel().getEnergy(), v.getModel().getType(), v.getModel().getModelName(), v.getModel().getNbDoors(), v.getModel().getNbSeats());
            vehiclesDTO.add(new VehicleDto(v.getId(), v.getBrand(), v.getLicencePlate(), v.getFleet().getPlace(), model));
        }
        return vehiclesDTO;
    }
}
