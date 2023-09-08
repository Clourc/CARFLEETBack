package com.project.carfleet.controller;

import com.project.carfleet.dto.ModelDto;
import com.project.carfleet.dto.VehicleDto;
import com.project.carfleet.entity.Vehicle;
import com.project.carfleet.service.ConvertToDto;
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
    private final ConvertToDto convertToDto;
    public VehicleController(VehicleRepository injectedRepository, ConvertToDto convertToDto) {
        this.vehicleRepository = injectedRepository;
        this.convertToDto = convertToDto;
    }

    @GetMapping(value = "/vehicles")
    @ResponseBody
    public List<VehicleDto> getVehicles(@RequestParam(defaultValue = "", required = false) String energy,
                                          @RequestParam(defaultValue = "", required = false) String type){
        boolean typeCheck = type.equals("citadine") || type.equals("fourgon") || type.equals("berline");
        boolean energyCheck = energy.equals("essence") || energy.equals("électrique") || energy.equals("diesel");
        List<Vehicle> vehicles = new ArrayList<>();
        if((!type.isEmpty() && !typeCheck) || (!energy.isEmpty() && !energyCheck)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect parameter(s) provided");
        }
        if(!type.isEmpty()){
            if(!energy.isEmpty()){
                vehicles = vehicleRepository.findVehicleByTypeAndEnergy(type, energy);
                return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
            }
            vehicles = vehicleRepository.findVehicleByType(type);
            return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
        } else {
            if(!energy.isEmpty()){
                vehicles = vehicleRepository.findVehicleByEnergy(energy);
                return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
            }
        }
        vehicles = vehicleRepository.findAll();
        return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
    }

    @GetMapping("/vehicles/{id}")
    @ResponseBody
    public VehicleDto getVehicleById(@PathVariable Long id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isPresent()){
            Vehicle v = optionalVehicle.get();
            return convertToDto.convertVehicleToDto(v);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "vehicle not found");
        }
    }

    @PostMapping("/vehicles/add")
    @ResponseBody
    public VehicleDto postVehicle(@RequestBody Vehicle vehicleToAdd){
        Vehicle newVehicle = new Vehicle(vehicleToAdd.getLicencePlate());
        newVehicle.setFleet(vehicleToAdd.getFleet());
        newVehicle.setModel(vehicleToAdd.getModel());
        vehicleRepository.save(newVehicle);
        return convertToDto.convertVehicleToDto(newVehicle);
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

}
