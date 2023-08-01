package com.project.carfleet.controller;

import com.project.carfleet.entity.Vehicule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.VehiculeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
public class VehiculeController {
    
    @Autowired
    private VehiculeRepository vehiculeRepository;

    @GetMapping("/vehicules")
    @ResponseBody
    public List<Vehicule> getVehicules(){
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

    @GetMapping("vehicules/{id}/delete")
    public String deleteVehiculeById(@PathVariable Long id){
        vehiculeRepository.deleteById(id);
        return "Vehicule deleted";
    }
}
