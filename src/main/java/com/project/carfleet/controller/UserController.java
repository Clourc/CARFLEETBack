package com.project.carfleet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.project.carfleet.entity.User;
import com.project.carfleet.repository.UserRepository;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository; 

  @GetMapping ("/users")
  @ResponseBody
  public List <User> getAllUsers() {
    return userRepository.findAll();


  }
  @GetMapping("/users/{id}")
  @ResponseBody 
   public ResponseEntity<User> getUserById(@PathVariable long id) {
if (id <= 0) {
  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found");
}
  
       Optional<User> user = userRepository.findById((long) id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
  

}

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
      
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID");
        }

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
        
    }

@PostMapping("/users")
public ResponseEntity<String> postUser(@RequestBody User user) {
    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully");

}
}