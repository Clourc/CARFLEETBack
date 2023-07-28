package com.project.carfleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.ModelRepository;

@Controller
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;
}
