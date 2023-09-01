package com.project.carfleet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.project.carfleet.dto.UserDto;
import com.project.carfleet.jwt.JwtUtil;
import com.project.carfleet.service.ConvertToDto;
import com.project.carfleet.service.UserService;
import com.project.carfleet.utility.ApiResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.project.carfleet.entity.UserEntity;
import com.project.carfleet.repository.UserRepository;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private ConvertToDto convertToDto;

    @GetMapping("/users")
    @ResponseBody
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return convertToDto.convertListToDto(users, convertToDto::convertUserToDto);
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        if (id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found");
        }
        Optional<UserEntity> optionalUser = userRepository.findById((long) id);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return new ResponseEntity<>(convertToDto.convertUserToDto(user), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        }
    }

    @DeleteMapping("/users/{id}/delete")
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

    @PostMapping("/users/register")
    public ResponseEntity<String> postUser(@RequestBody UserEntity user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully");
    }

    @PostMapping("/users/login")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody UserDto user){
        HashMap<String, Object> data = new HashMap<>();
        try {
            userService.login(user);
            String token = jwtUtil.generateToken(user);
            data.put("user", user);
            data.put("token", token);
            data.put("Request", "User logged in");
            return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin-only")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> adminData(){
        HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("message", "This is only accessible by admins");
            return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-users")
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> userAdminData(){
        HashMap<String, Object> data = new HashMap<>();
        try {
            data.put("message", "This is accessible by everyone");
            return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}