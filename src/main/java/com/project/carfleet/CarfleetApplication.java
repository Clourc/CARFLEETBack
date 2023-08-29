package com.project.carfleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.carfleet.entity.Fleet;
import com.project.carfleet.entity.Model;
import com.project.carfleet.entity.User;
import com.project.carfleet.entity.Vehicule;

@SpringBootApplication
public class CarfleetApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarfleetApplication.class, args);
	}

	User aurelie = new User(1L, "1234567A", "aurelie", "ziegler", "aurelie.ziegler@example.com", "0601020304", "AZ123");
	User yannick = new User(2L, "2345678Y", "yannick", "minck", "yannickminck@example.com", "0602030405", "YM456");
	User jamal = new User(3L, "3456789J", "jamal", "ouldrabia", "jamal.ouldrabia@example.com", "0603040506", "JO789");
	User lucas = new User(4L, "4567890L", "lucas", "corbino", "lucas.corbino@example.com", "0604050607", "LC012");
	User richard = new User(5L, "5678901R", "richard", "nguyen", "richard.nguyen@example.com", "0605060708", "RN345");

	Fleet strasbourg = new Fleet(1L, "strasbourg");
	Fleet paris = new Fleet(2L, "paris");
	Fleet lyon = new Fleet(3L, "lyon");
	Fleet marseille = new Fleet(4L, "marseille");

	strasbourg.getUsers().add(aurelie);
	strasbourg.getUsers().add(yannick);
	aurelie.getUser().add(strasbourg);
	yannick.getUser().add(strasbourg);
	userRepository.save(aurelie);
	userRepository.save(yannick);

	paris.getUsers().add(jamal);
	paris.getUsers().add(lucas);
	jamal.getUser().add(paris);
	lucas.getUser().add(paris);
	userRepository.save(jamal);
	userRepository.save(lucas);

	marseille.getUsers().add(richard);
	richard.getUser().add(marseille);
	userRepository.save(richard);

Vehicule vehicule1 = new Vehicule(1L, "renault", "ZO-123-AA");
Vehicule vehicule2 = new Vehicule(2L, "renault", "CL-234-BB");
Vehicule vehicule3 = new Vehicule(3L, "renault", "KA-345-CC");
Vehicule vehicule4 = new Vehicule(4L, "renault", "ME-456-DD");
Vehicule vehicule5 = new Vehicule(5L, "citroen", "BE-567-EE");
Vehicule vehicule6 = new Vehicule(6L, "peugeot", "BO-678-FF");
Vehicule vehicule7 = new Vehicule(7L, "renault", "TR-789-GG");


Model model1 = new Model(1L, "https://i.imgur.com/FZ5BdEW.png", "electric", "citadine", "ZOE R110", 5, 5);
Model model2 = new Model(2L, "https://i.imgur.com/52m5kEK.png", "essence", "citadine", "CLIO RS Line", 5, 5);
Model model3 = new Model(3L, "https://i.imgur.com/Ja7IsPL.jpg", "essence", "citadine", "KANGOO 3", 5, 5);
Model model4 = new Model(4L, "https://i.imgur.com/5Z2ZQ8u.jpg", "essence", "berline", "MEGANE 3 phase 3", 5, 5);
Model model5 = new Model(5L, "https://i.imgur.com/ldO9jOD.jpg", "electric", "berline", "e BERLINGO shine", 5, 7);
Model model6 = new Model(6L, "https://i.imgur.com/Oy4emF7.jpg", "diesel", "fourgon", "BOXER Asphalt 333", 4, 3);
Model model7 = new Model(7L, "https://i.imgur.com/ZOUyLBS.jpg", "diesel", "fourgon", "TRAFIC Grand confort", 3, 4);


}

