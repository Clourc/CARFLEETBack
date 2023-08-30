package com.project.carfleet;

import com.project.carfleet.repository.FleetRepository;
import com.project.carfleet.repository.ModelRepository;
import com.project.carfleet.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.carfleet.entity.Fleet;
import com.project.carfleet.entity.Model;
import com.project.carfleet.entity.User;
import com.project.carfleet.entity.Vehicule;
import com.project.carfleet.repository.UserRepository;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CarfleetApplication {

	private ModelRepository modelRepository;
	private FleetRepository fleetRepository;
	private VehiculeRepository vehiculeRepository;

	public CarfleetApplication(ModelRepository modelRepository, FleetRepository fleetRepository, VehiculeRepository vehiculeRepository){
		this.modelRepository = modelRepository;
		this.fleetRepository = fleetRepository;
		this.vehiculeRepository = vehiculeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CarfleetApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return (String[] args) -> {

			User aurelie = new User("1234567A", "aurelie", "ziegler", "aurelie.ziegler@example.com", "0601020304", "AZ123");
			User yannick = new User("2345678Y", "yannick", "minck", "yannickminck@example.com", "0602030405", "YM456");
			User jamal = new User("3456789J", "jamal", "ouldrabia", "jamal.ouldrabia@example.com", "0603040506", "JO789");
			User lucas = new User("4567890L", "lucas", "corbino", "lucas.corbino@example.com", "0604050607", "LC012");
			User richard = new User("5678901R", "richard", "nguyen", "richard.nguyen@example.com", "0605060708", "RN345");

			Fleet strasbourg = new Fleet("strasbourg");
			Fleet paris = new Fleet("paris");
			Fleet lyon = new Fleet("lyon");
			Fleet marseille = new Fleet("marseille");

			List<User> userListStrasbourg = new ArrayList<>();
			userListStrasbourg.add(aurelie);
			strasbourg.setUsers(userListStrasbourg);
			strasbourg.getUsers().add(yannick);
			aurelie.setFleet(strasbourg);
			yannick.setFleet(strasbourg);

			List<User> userListParis = new ArrayList<>();
			userListParis.add(jamal);
			paris.setUsers(userListParis);
			paris.getUsers().add(lucas);
			jamal.setFleet(paris);
			lucas.setFleet(paris);

			List<User> userListMarseille = new ArrayList<>();
			userListMarseille.add(richard);
			marseille.setUsers(userListMarseille);
			richard.setFleet(marseille);

			Vehicule vehicule1 = new Vehicule("renault", "ZO-123-AA");
			Vehicule vehicule2 = new Vehicule("renault", "CL-234-BB");
			Vehicule vehicule3 = new Vehicule("renault", "KA-345-CC");
			Vehicule vehicule4 = new Vehicule("renault", "ME-456-DD");
			Vehicule vehicule5 = new Vehicule("citroen", "BE-567-EE");
			Vehicule vehicule6 = new Vehicule("peugeot", "BO-678-FF");
			Vehicule vehicule7 = new Vehicule("renault", "TR-789-GG");

			Model model1 = new Model("https://i.imgur.com/FZ5BdEW.png", "electric", "citadine", "ZOE R110", 5, 5);
			Model model2 = new Model("https://i.imgur.com/52m5kEK.png", "essence", "citadine", "CLIO RS Line", 5, 5);
			Model model3 = new Model("https://i.imgur.com/Ja7IsPL.jpg", "essence", "citadine", "KANGOO 3", 5, 5);
			Model model4 = new Model("https://i.imgur.com/5Z2ZQ8u.jpg", "essence", "berline", "MEGANE 3 phase 3", 5, 5);
			Model model5 = new Model("https://i.imgur.com/ldO9jOD.jpg", "electric", "berline", "e BERLINGO shine", 5, 7);
			Model model6 = new Model("https://i.imgur.com/Oy4emF7.jpg", "diesel", "fourgon", "BOXER Asphalt 333", 4, 3);
			Model model7 = new Model("https://i.imgur.com/ZOUyLBS.jpg", "diesel", "fourgon", "TRAFIC Grand confort", 3, 4);

			List<Vehicule> model1List = new ArrayList<>();
			model1List.add(vehicule1);
			vehicule1.setModel(model1);
			vehicule1.setFleet(strasbourg);

			List<Vehicule> model2List = new ArrayList<>();
			model2List.add(vehicule2);
			vehicule2.setModel(model2);
			vehicule2.setFleet(strasbourg);

			List<Vehicule> vehiculeListStrasbourg = new ArrayList<>();
			vehiculeListStrasbourg.add(vehicule1);
			vehiculeListStrasbourg.add(vehicule2);

			List<Vehicule> model3List = new ArrayList<>();
			model3List.add(vehicule3);
			vehicule3.setModel(model3);
			vehicule3.setFleet(paris);

			List<Vehicule> model4List = new ArrayList<>();
			model4List.add(vehicule4);
			vehicule4.setModel(model4);
			vehicule4.setFleet(paris);

			List<Vehicule> vehiculeListParis = new ArrayList<>();
			vehiculeListParis.add(vehicule3);
			vehiculeListParis.add(vehicule4);

			List<Vehicule> model5List = new ArrayList<>();
			model5List.add(vehicule5);
			vehicule5.setModel(model5);
			vehicule5.setFleet(lyon);

			List<Vehicule> vehiculeListLyon = new ArrayList<>();
			vehiculeListLyon.add(vehicule5);

			List<Vehicule> model6List = new ArrayList<>();
			model6List.add(vehicule6);
			vehicule6.setModel(model6);
			vehicule6.setFleet(marseille);

			List<Vehicule> model7List = new ArrayList<>();
			model7List.add(vehicule7);
			vehicule7.setModel(model7);
			vehicule7.setFleet(marseille);

			List<Vehicule> vehiculeListMarseille = new ArrayList<>();
			vehiculeListMarseille.add(vehicule6);
			vehiculeListMarseille.add(vehicule7);

			fleetRepository.save(strasbourg);
			fleetRepository.save(paris);
			fleetRepository.save(lyon);
			fleetRepository.save(marseille);

			modelRepository.save(model1);
			modelRepository.save(model2);
			modelRepository.save(model3);
			modelRepository.save(model4);
			modelRepository.save(model5);
			modelRepository.save(model6);
			modelRepository.save(model7);

			vehiculeRepository.save(vehicule1);
			vehiculeRepository.save(vehicule2);
			vehiculeRepository.save(vehicule3);
			vehiculeRepository.save(vehicule4);
			vehiculeRepository.save(vehicule5);
			vehiculeRepository.save(vehicule6);
			vehiculeRepository.save(vehicule7);

		};
	}
}





		

