package com.project.carfleet.controller;

import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.VehiculeRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class VehiculeController {
    
    @Autowired
    private VehiculeRepository vehiculeRepository;
}
