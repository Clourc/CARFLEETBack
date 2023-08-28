package com.project.carfleet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.carfleet.repository.UserRepository;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository; 
  
}
