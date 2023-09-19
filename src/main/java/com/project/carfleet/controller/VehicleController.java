package com.project.carfleet.controller;

import com.project.carfleet.dto.ModelDto;
import com.project.carfleet.dto.VehicleDto;
import com.project.carfleet.entity.Fleet;
import com.project.carfleet.entity.Model;
import com.project.carfleet.entity.Vehicle;
import com.project.carfleet.repository.FleetRepository;
import com.project.carfleet.repository.ModelRepository;
import com.project.carfleet.service.ConvertToDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.VehicleRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200") // autorise les requêtes provenant du domaine "http://localhost:4200".
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final FleetRepository fleetRepository;
    private final ModelRepository modelRepository;
    private final ConvertToDto convertToDto;

    public VehicleController(VehicleRepository vehicleRepository, FleetRepository fleetRepository, ModelRepository modelRepository, ConvertToDto convertToDto) {
        this.vehicleRepository = vehicleRepository;
        this.fleetRepository = fleetRepository;
        this.modelRepository = modelRepository;
        this.convertToDto = convertToDto;
    }

    @GetMapping(value = "/vehicles")
    @ResponseBody

    public List<VehicleDto> getVehicles(@RequestParam Long fleetId,
                                        @RequestParam(defaultValue = "", required = false) String energy,
                                        @RequestParam(defaultValue = "", required = false) String type) {
        boolean typeCheck = type.equals("citadine") || type.equals("fourgon") || type.equals("berline");
        boolean energyCheck = energy.equals("essence") || energy.equals("électrique") || energy.equals("diesel");
        List<Vehicle> vehicles = new ArrayList<>();
        if ((!type.isEmpty() && !typeCheck) || (!energy.isEmpty() && !energyCheck)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect parameter(s) provided");
        }
        if (!type.isEmpty()) {
            if (!energy.isEmpty()) {
                vehicles = vehicleRepository.findVehicleByTypeAndEnergy(fleetId, type, energy);
                return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
            }
            vehicles = vehicleRepository.findVehicleByType(fleetId, type);
            return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
        } else {
            if (!energy.isEmpty()) {
                vehicles = vehicleRepository.findVehicleByEnergy(fleetId ,energy);
                return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
            }
        }
        vehicles = vehicleRepository.findVehicleByFleet(fleetId);
        return convertToDto.convertListToDto(vehicles, convertToDto::convertVehicleToDto);
    }

    @GetMapping("/vehicles/{id}")
    @ResponseBody
    public VehicleDto getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle v = optionalVehicle.get();
            return convertToDto.convertVehicleToDto(v);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "vehicle not found");
        }
    }

    @PostMapping("/vehicles/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> postVehicle(@RequestBody VehicleDto vehicleToAdd) {
        Map<String, String> response = new HashMap<>();
        Vehicle newVehicle = new Vehicle(vehicleToAdd.getLicencePlate());
        Fleet vehicleFleet = this.fleetRepository.findById(vehicleToAdd.getFleet().getId()).get();
        Model vehicleModel = this.modelRepository.findById(vehicleToAdd.getModel().getId()).get();
        newVehicle.setFleet(vehicleFleet);
        newVehicle.setModel(vehicleModel);
        vehicleRepository.save(newVehicle);
        response.put("message", "Vehicle successfully added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("vehicles/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> deleteVehicleById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            vehicleRepository.deleteById(id);
            response.put("message", "Deletion successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
    }

}
