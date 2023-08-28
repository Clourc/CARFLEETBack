package com.project.carfleet.controller;

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
    public List<Vehicule> getVehicules(@RequestParam(defaultValue = "", required = false) String energy,
                                       @RequestParam(defaultValue = "", required = false) String type){
        boolean typeCheck = type.equals("citadine") || type.equals("utilitaire");
        boolean energyCheck = energy.equals("essence") || energy.equals("électrique");
        if((!type.isEmpty() && !typeCheck) || (!energy.isEmpty() && !energyCheck)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect parameter(s) provided");
        }
        if(!type.isEmpty()){
            if(!energy.isEmpty()){
                return vehiculeRepository.findVehiculeByTypeAndEnergy(type, energy);
            }
            return vehiculeRepository.findVehiculeByType(type);
        } else {
            if(!energy.isEmpty()){
                return vehiculeRepository.findVehiculeByEnergy(energy);
            }
        }
        return vehiculeRepository.findAll();
    }

    @GetMapping("/vehicules/{id}")
    @ResponseBody
    public Vehicule getVehiculeById(@PathVariable Long id){
        Optional<Vehicule> optionalVehicule = vehiculeRepository.findById(id);
        if(optionalVehicule.isPresent()){
            return optionalVehicule.get();
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
}
