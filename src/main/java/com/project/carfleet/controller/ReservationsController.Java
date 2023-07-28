package main.java.com.project.carfleet.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;
import com.project.carfleet.entity.Reservations;
import com.project.carfleet.repository.ReservationsRepository;
import java.util.List;
import java.util.Optional;

@Controller 
public class ReservationsController{

    @Autowired
    private ReservationsRepository reservationsRepository;
}