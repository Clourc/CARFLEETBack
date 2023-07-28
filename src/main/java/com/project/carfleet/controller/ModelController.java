package com.project.carfleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.repository.Modelrepository;

@Controller
public class ModelController {

    @Autowired
    private Modelrepository modelRepository;
}
