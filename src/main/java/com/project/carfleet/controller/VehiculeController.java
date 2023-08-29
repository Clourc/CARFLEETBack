package com.project.carfleet.controller;

import com.project.carfleet.dto.VehiculeDTO;
import com.project.carfleet.entity.Vehicule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.VehiculeRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class VehiculeController {

    private final VehiculeRepository vehiculeRepository;
    public VehiculeController(VehiculeRepository injectedRepository) {
        this.vehiculeRepository = injectedRepository;
    }

    @GetMapping(value = "/vehicules")
    @ResponseBody
    public List<VehiculeDTO> getVehicules(@RequestParam(defaultValue = "", required = false) String energy,
                                       @RequestParam(defaultValue = "", required = false) String type){
        boolean typeCheck = type.equals("citadine") || type.equals("utilitaire");
        boolean energyCheck = energy.equals("essence") || energy.equals("électrique");
        if((!type.isEmpty() && !typeCheck) || (!energy.isEmpty() && !energyCheck)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect parameter(s) provided");
        }
        if(!type.isEmpty()){
            if(!energy.isEmpty()){
                return convertListVehiculeToDTO(vehiculeRepository.findVehiculeByTypeAndEnergy(type, energy));
            }
            return convertListVehiculeToDTO(vehiculeRepository.findVehiculeByType(type));
        } else {
            if(!energy.isEmpty()){
                return convertListVehiculeToDTO(vehiculeRepository.findVehiculeByEnergy(energy));
            }
        }
        return convertListVehiculeToDTO(vehiculeRepository.findAll());
    }

    @GetMapping("/vehicules/{id}")
    @ResponseBody
    public VehiculeDTO getVehiculeById(@PathVariable Long id){
        Optional<Vehicule> optionalVehicule = vehiculeRepository.findById(id);
        if(optionalVehicule.isPresent()){
            Vehicule v = optionalVehicule.get();
            return new VehiculeDTO(v.getId(), v.getBrand(), v.getLicencePlate(), v.getFleet().getPlace(), v.getModel().getModelName());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "vehicule not found");
        }
    }

    @PostMapping("/vehicules/add")
    @ResponseBody
    public Vehicule postVehicule(@RequestBody Vehicule vehiculeToAdd){
        vehiculeRepository.save(vehiculeToAdd);
        return vehiculeToAdd;
    }

    @DeleteMapping("vehicules/{id}/delete")
    public String deleteVehiculeById(@PathVariable Long id){
        Optional<Vehicule> optionalVehicule = vehiculeRepository.findById(id);
        if(optionalVehicule.isPresent()){
            vehiculeRepository.deleteById(id);
            return "Vehicule id=" + id + " deleted";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicule not found");
    }

    private List<VehiculeDTO> convertListVehiculeToDTO(List<Vehicule> vehicules){
        List<VehiculeDTO> vehiculesDTO = new ArrayList<>();
        for(Vehicule v : vehicules){
            vehiculesDTO.add(new VehiculeDTO(v.getId(), v.getBrand(), v.getLicencePlate(), v.getFleet().getPlace(), v.getModel().getModelName()));
        }
        return vehiculesDTO;
    }
}
