package com.project.carfleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.FleetRepository;

@Controller
public class FleetController {

    @Autowired
    private FleetRepository fleetRepository;

}
