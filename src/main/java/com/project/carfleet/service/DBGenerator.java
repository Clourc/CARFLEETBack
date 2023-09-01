package com.project.carfleet.service;

import com.project.carfleet.entity.*;
import com.project.carfleet.repository.FleetRepository;
import com.project.carfleet.repository.ModelRepository;
import com.project.carfleet.repository.RoleRepository;
import com.project.carfleet.repository.VehicleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DBGenerator {

    private final ModelRepository modelRepository;
    private final VehicleRepository vehicleRepository;
    private final FleetRepository fleetRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public DBGenerator(ModelRepository modelRepository, VehicleRepository vehicleRepository, FleetRepository fleetRepository, RoleRepository roleRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.modelRepository = modelRepository;
        this.vehicleRepository = vehicleRepository;
        this.fleetRepository = fleetRepository;
        this.roleRepository = roleRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public void generateFleetDatas() {
        Role user = roleRepository.findById(1L).get();
        Role admin = roleRepository.findById(2L).get();

        UserEntity aurelie = new UserEntity("1234567A", "aurelie", "ziegler", "aurelie.ziegler@example.com", bcryptEncoder.encode("aurZIE123!"), "0601020304", "AZ123", admin);
        UserEntity yannick = new UserEntity("2345678Y", "yannick", "minck", "yannickminck@example.com", bcryptEncoder.encode("yanMIN456!"), "0602030405", "YM456", user);
        UserEntity jamal = new UserEntity("3456789J", "jamal", "ouldrabia", "jamal.ouldrabia@example.com", bcryptEncoder.encode("jamOUL789!"), "0603040506", "JO789", user);
        UserEntity lucas = new UserEntity("4567890L", "lucas", "corbino", "lucas.corbino@example.com", bcryptEncoder.encode("lucCOR012!"), "0604050607", "LC012", admin);
        UserEntity richard = new UserEntity("5678901R", "richard", "nguyen", "richard.nguyen@example.com", bcryptEncoder.encode("ricNGU345!"), "0605060708", "RN345", user);

        Fleet strasbourg = new Fleet("strasbourg");
        Fleet paris = new Fleet("paris");
        Fleet lyon = new Fleet("lyon");
        Fleet marseille = new Fleet("marseille");

        List<UserEntity> userListStrasbourg = new ArrayList<>();
        userListStrasbourg.add(aurelie);
        strasbourg.setUsers(userListStrasbourg);
        strasbourg.getUsers().add(yannick);
        aurelie.setFleet(strasbourg);
        yannick.setFleet(strasbourg);
        fleetRepository.save(strasbourg);

        List<UserEntity> userListParis = new ArrayList<>();
        userListParis.add(jamal);
        paris.setUsers(userListParis);
        paris.getUsers().add(lucas);
        jamal.setFleet(paris);
        lucas.setFleet(paris);
        fleetRepository.save(paris);

        fleetRepository.save(lyon);

        List<UserEntity> userListMarseille = new ArrayList<>();
        userListMarseille.add(richard);
        marseille.setUsers(userListMarseille);
        richard.setFleet(marseille);
        fleetRepository.save(marseille);

        Vehicle vehicle1 = new Vehicle("renault", "ZO-123-AA");
        Vehicle vehicle2 = new Vehicle("renault", "CL-234-BB");
        Vehicle vehicle3 = new Vehicle("renault", "KA-345-CC");
        Vehicle vehicle4 = new Vehicle("renault", "ME-456-DD");
        Vehicle vehicle5 = new Vehicle("citroen", "BE-567-EE");
        Vehicle vehicle6 = new Vehicle("peugeot", "BO-678-FF");
        Vehicle vehicle7 = new Vehicle("renault", "TR-789-GG");

        Model model1 = new Model("https://i.imgur.com/FZ5BdEW.png", "electric", "citadine", "ZOE R110", 5, 5);
        Model model2 = new Model("https://i.imgur.com/52m5kEK.png", "essence", "citadine", "CLIO RS Line", 5, 5);
        Model model3 = new Model("https://i.imgur.com/Ja7IsPL.jpg", "essence", "citadine", "KANGOO 3", 5, 5);
        Model model4 = new Model("https://i.imgur.com/5Z2ZQ8u.jpg", "essence", "berline", "MEGANE 3 phase 3", 5, 5);
        Model model5 = new Model("https://i.imgur.com/ldO9jOD.jpg", "electric", "berline", "e BERLINGO shine", 5, 7);
        Model model6 = new Model("https://i.imgur.com/Oy4emF7.jpg", "diesel", "fourgon", "BOXER Asphalt 333", 4, 3);
        Model model7 = new Model("https://i.imgur.com/ZOUyLBS.jpg", "diesel", "fourgon", "TRAFIC Grand confort", 3, 4);

        List<Vehicle> model1List = new ArrayList<>();
        model1List.add(vehicle1);
        vehicle1.setModel(model1);
        vehicle1.setFleet(strasbourg);

        List<Vehicle> model2List = new ArrayList<>();
        model2List.add(vehicle2);
        vehicle2.setModel(model2);
        vehicle2.setFleet(strasbourg);

        List<Vehicle> vehicleListStrasbourg = new ArrayList<>();
        vehicleListStrasbourg.add(vehicle1);
        vehicleListStrasbourg.add(vehicle2);

        List<Vehicle> model3List = new ArrayList<>();
        model3List.add(vehicle3);
        vehicle3.setModel(model3);
        vehicle3.setFleet(paris);

        List<Vehicle> model4List = new ArrayList<>();
        model4List.add(vehicle4);
        vehicle4.setModel(model4);
        vehicle4.setFleet(paris);

        List<Vehicle> vehicleListParises = new ArrayList<>();
        vehicleListParises.add(vehicle3);
        vehicleListParises.add(vehicle4);

        List<Vehicle> model5List = new ArrayList<>();
        model5List.add(vehicle5);
        vehicle5.setModel(model5);
        vehicle5.setFleet(lyon);

        List<Vehicle> vehicleListLyon = new ArrayList<>();
        vehicleListLyon.add(vehicle5);

        List<Vehicle> model6List = new ArrayList<>();
        model6List.add(vehicle6);
        vehicle6.setModel(model6);
        vehicle6.setFleet(marseille);

        List<Vehicle> model7List = new ArrayList<>();
        model7List.add(vehicle7);
        vehicle7.setModel(model7);
        vehicle7.setFleet(marseille);

        List<Vehicle> vehicleListMarseille = new ArrayList<>();
        vehicleListMarseille.add(vehicle6);
        vehicleListMarseille.add(vehicle7);

        List<Model> models = Arrays.asList(model1, model2, model3, model4, model5, model6, model7);
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7);

        modelRepository.saveAll(models);
        vehicleRepository.saveAll(vehicles);
    }

    public void generateRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        roles.add(new Role("ADMIN"));
        roleRepository.saveAll(roles);
        generateFleetDatas();
    }

}
