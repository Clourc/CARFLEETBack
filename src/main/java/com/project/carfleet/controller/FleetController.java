package com.project.carfleet.controller;

import java.util.List;
import java.util.Optional;

import com.project.carfleet.service.ConvertToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.project.carfleet.dto.FleetDto;
import com.project.carfleet.entity.Fleet;

import com.project.carfleet.repository.FleetRepository;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class FleetController {

    @Autowired
    private FleetRepository fleetRepository;

    @GetMapping("/fleets")
    public ResponseEntity<List<FleetDto>> getAllFleetDtos() {
        List<FleetDto> fleetDto = fleetRepository.findAllFleet();
        return new ResponseEntity<>(fleetDto, HttpStatus.OK);
    }

    @GetMapping("/fleets/{id}")
    public ResponseEntity<FleetDto> getFleetDtoById(@PathVariable long id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be greater than zero");
        }

        FleetDto fleetDto = fleetRepository.findFleetDtoById(id);
        if (fleetDto != null) {
            return new ResponseEntity<>(fleetDto, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fleet not found");
        }
    }

    @DeleteMapping("/fleets/delete/{id}")
    public ResponseEntity<String> deleteFleet(@PathVariable(value = "id") Long id) {
        Optional<Fleet> fleet = fleetRepository.findById(id);
        if (fleet.isPresent()) {
            fleetRepository.deleteById(id);
            return new ResponseEntity<>("Deletion successful", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fleet not found");
        }
    }

    @PostMapping("/fleets")
    public ResponseEntity<String> postFleet(@RequestBody Fleet fleet) {
        fleetRepository.save(fleet);
        return new ResponseEntity<>("Fleet saved successfully", HttpStatus.CREATED);
    }

}