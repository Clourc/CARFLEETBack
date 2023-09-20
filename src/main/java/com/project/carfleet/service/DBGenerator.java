package com.project.carfleet.service;

import com.project.carfleet.entity.*;
import com.project.carfleet.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.format.DateTimeFormatter;

@Service
public class DBGenerator {

    private final ModelRepository modelRepository;
    private final VehicleRepository vehicleRepository;
    private final FleetRepository fleetRepository;
    private final ReservationsRepository reservationsRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public DBGenerator(ModelRepository modelRepository, VehicleRepository vehicleRepository, FleetRepository fleetRepository, ReservationsRepository reservationsRepository, UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.modelRepository = modelRepository;
        this.vehicleRepository = vehicleRepository;
        this.fleetRepository = fleetRepository;
        this.reservationsRepository = reservationsRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public void generateFleetDatas() {
        Role user = roleRepository.findById(1L).get();
        Role admin = roleRepository.findById(2L).get();

        UserEntity aurelie = new UserEntity("1234567A", "Aurelie", "Ziegler", "aurelie.ziegler@example.com", bcryptEncoder.encode("aurZIE123!"), "0601020304", "AZ123", admin);
        UserEntity yannick = new UserEntity("2345678Y", "Yannick", "Minck", "yannickminck@example.com", bcryptEncoder.encode("yanMIN456!"), "0602030405", "YM456", user);
        UserEntity jamal = new UserEntity("3456789J", "Jamal", "Ouldrabia", "jamal.ouldrabia@example.com", bcryptEncoder.encode("jamOUL789!"), "0603040506", "JO789", user);
        UserEntity lucas = new UserEntity("4567890L", "Lucas", "Corbino", "lucas.corbino@example.com", bcryptEncoder.encode("lucCOR012!"), "0604050607", "LC012", admin);
        UserEntity richard = new UserEntity("5678901R", "Richard", "Nguyen", "richard.nguyen@example.com", bcryptEncoder.encode("ricNGU345!"), "0605060708", "RN345", user);

        Fleet strasbourg = new Fleet("Strasbourg");
        Fleet paris = new Fleet("Paris");
        Fleet lyon = new Fleet("Lyon");
        Fleet marseille = new Fleet("Marseille");

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

        Vehicle vehicle1 = new Vehicle("ZO-123-AA");
        Vehicle vehicle2 = new Vehicle("CL-234-BB");
        Vehicle vehicle3 = new Vehicle("KA-345-CC");
        Vehicle vehicle4 = new Vehicle("ME-456-DD");
        Vehicle vehicle5 = new Vehicle("BE-567-EE");
        Vehicle vehicle6 = new Vehicle("BO-678-FF");
        Vehicle vehicle7 = new Vehicle("TR-789-GG");

        Vehicle vehicle8 = new Vehicle("ZO-234-AB");
        Vehicle vehicle9 = new Vehicle("CL-345-BC");
        Vehicle vehicle10 = new Vehicle("KA-456-CD");
        Vehicle vehicle11 = new Vehicle("ME-567-DE");
        Vehicle vehicle12 = new Vehicle("BE-678-EF");
        Vehicle vehicle13 = new Vehicle("BO-789-FG");
        Vehicle vehicle14 = new Vehicle("TR-890-GH");

        Vehicle vehicle15 = new Vehicle("ZO-345-AC");
        Vehicle vehicle16 = new Vehicle("CL-456-BD");
        Vehicle vehicle17 = new Vehicle("KA-567-CE");
        Vehicle vehicle18 = new Vehicle("ME-678-DF");
        Vehicle vehicle19 = new Vehicle("BE-789-EG");
        Vehicle vehicle20 = new Vehicle("BO-890-FH");
        Vehicle vehicle21 = new Vehicle("TR-901-GI");

        Vehicle vehicle22 = new Vehicle("ZO-456-AD");
        Vehicle vehicle23 = new Vehicle("CL-567-BE");
        Vehicle vehicle24 = new Vehicle("KA-678-CF");
        Vehicle vehicle25 = new Vehicle("ME-789-DG");
        Vehicle vehicle26 = new Vehicle("BE-890-EF");
        Vehicle vehicle27 = new Vehicle("BO-901-FI");
        Vehicle vehicle28 = new Vehicle("TR-012-GJ");

        Model model1 = new Model("renault", "https://i.imgur.com/52m5kEK.png", "électrique", "citadine", "ZOE R110", 5, 5);
        Model model2 = new Model("renault", "https://i.imgur.com/FZ5BdEW.png", "essence", "citadine", "CLIO RS Line", 5, 5);
        Model model3 = new Model("renault", "https://i.imgur.com/Ja7IsPL.jpg", "essence", "utilitaire", "KANGOO 3", 5, 5);
        Model model4 = new Model("renault", "https://i.imgur.com/5Z2ZQ8u.jpg", "essence", "berline", "MEGANE 3 phase 3", 5, 5);
        Model model5 = new Model("citroen", "https://i.imgur.com/ldO9jOD.jpg", "électrique", "berline", "e BERLINGO shine", 5, 7);
        Model model6 = new Model("peugeot", "https://i.imgur.com/Oy4emF7.jpg", "diesel", "utilitaire", "BOXER Asphalt 333", 4, 3);
        Model model7 = new Model("renault", "https://i.imgur.com/ZOUyLBS.jpg", "diesel", "utilitaire", "TRAFIC Grand confort", 3, 4);

        List<Vehicle> model1List = new ArrayList<>();
        model1List.add(vehicle1);
        model1List.add(vehicle8);
        model1List.add(vehicle15);
        model1List.add(vehicle22);
        vehicle1.setModel(model1);
        vehicle8.setModel(model1);
        vehicle15.setModel(model1);
        vehicle22.setModel(model1);
        vehicle1.setFleet(strasbourg);
        vehicle8.setFleet(paris);
        vehicle15.setFleet(lyon);
        vehicle22.setFleet(marseille);

        List<Vehicle> model2List = new ArrayList<>();
        model2List.add(vehicle2);
        model2List.add(vehicle9);
        model2List.add(vehicle16);
        model2List.add(vehicle23);
        vehicle2.setModel(model2);
        vehicle9.setModel(model2);
        vehicle16.setModel(model2);
        vehicle23.setModel(model2);
        vehicle2.setFleet(strasbourg);
        vehicle9.setFleet(paris);
        vehicle16.setFleet(lyon);
        vehicle23.setFleet(marseille);

        List<Vehicle> model3List = new ArrayList<>();
        model3List.add(vehicle3);
        model3List.add(vehicle10);
        model3List.add(vehicle17);
        model3List.add(vehicle24);
        vehicle3.setModel(model3);
        vehicle10.setModel(model3);
        vehicle17.setModel(model3);
        vehicle24.setModel(model3);
        vehicle3.setFleet(strasbourg);
        vehicle10.setFleet(paris);
        vehicle17.setFleet(lyon);
        vehicle24.setFleet(marseille);

        List<Vehicle> model4List = new ArrayList<>();
        model4List.add(vehicle4);
        model4List.add(vehicle11);
        model4List.add(vehicle18);
        model4List.add(vehicle25);
        vehicle4.setModel(model4);
        vehicle11.setModel(model4);
        vehicle18.setModel(model4);
        vehicle25.setModel(model4);
        vehicle4.setFleet(strasbourg);
        vehicle11.setFleet(paris);
        vehicle18.setFleet(lyon);
        vehicle25.setFleet(marseille);


        List<Vehicle> model5List = new ArrayList<>();
        model5List.add(vehicle5);
        model5List.add(vehicle12);
        model5List.add(vehicle19);
        model5List.add(vehicle26);
        vehicle5.setModel(model5);
        vehicle12.setModel(model5);
        vehicle19.setModel(model5);
        vehicle26.setModel(model5);
        vehicle5.setFleet(strasbourg);
        vehicle12.setFleet(paris);
        vehicle19.setFleet(lyon);
        vehicle26.setFleet(marseille);


        List<Vehicle> model6List = new ArrayList<>();
        model6List.add(vehicle6);
        model6List.add(vehicle13);
        model6List.add(vehicle20);
        model6List.add(vehicle27);
        vehicle6.setModel(model6);
        vehicle13.setModel(model6);
        vehicle20.setModel(model6);
        vehicle27.setModel(model6);
        vehicle6.setFleet(strasbourg);
        vehicle13.setFleet(paris);
        vehicle20.setFleet(lyon);
        vehicle27.setFleet(marseille);

        List<Vehicle> model7List = new ArrayList<>();
        model7List.add(vehicle7);
        model7List.add(vehicle14);
        model7List.add(vehicle21);
        model7List.add(vehicle28);
        vehicle7.setModel(model7);
        vehicle14.setModel(model7);
        vehicle21.setModel(model7);
        vehicle28.setModel(model7);
        vehicle7.setFleet(strasbourg);
        vehicle14.setFleet(paris);
        vehicle21.setFleet(lyon);
        vehicle28.setFleet(marseille);

        List<Vehicle> vehicleListStrasbourg = new ArrayList<>();
        vehicleListStrasbourg.add(vehicle1);
        vehicleListStrasbourg.add(vehicle2);
        vehicleListStrasbourg.add(vehicle3);
        vehicleListStrasbourg.add(vehicle4);
        vehicleListStrasbourg.add(vehicle5);
        vehicleListStrasbourg.add(vehicle6);
        vehicleListStrasbourg.add(vehicle7);

        List<Vehicle> vehicleListParis = new ArrayList<>();
        vehicleListParis.add(vehicle8);
        vehicleListParis.add(vehicle9);
        vehicleListParis.add(vehicle10);
        vehicleListParis.add(vehicle11);
        vehicleListParis.add(vehicle12);
        vehicleListParis.add(vehicle13);
        vehicleListParis.add(vehicle14);

        List<Vehicle> vehicleListLyon = new ArrayList<>();
        vehicleListLyon.add(vehicle15);
        vehicleListLyon.add(vehicle16);
        vehicleListLyon.add(vehicle17);
        vehicleListLyon.add(vehicle18);
        vehicleListLyon.add(vehicle19);
        vehicleListLyon.add(vehicle20);
        vehicleListLyon.add(vehicle21);

        List<Vehicle> vehicleListMarseille = new ArrayList<>();
        vehicleListMarseille.add(vehicle22);
        vehicleListMarseille.add(vehicle23);
        vehicleListMarseille.add(vehicle24);
        vehicleListMarseille.add(vehicle25);
        vehicleListMarseille.add(vehicle26);
        vehicleListMarseille.add(vehicle27);
        vehicleListMarseille.add(vehicle28);

        List<Model> models = Arrays.asList(model1, model2, model3, model4, model5, model6, model7);
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7, vehicle8, vehicle9, vehicle10, vehicle11, vehicle12, vehicle13, vehicle14, vehicle15, vehicle16, vehicle17, vehicle18, vehicle19, vehicle20, vehicle21, vehicle22, vehicle23, vehicle24, vehicle25, vehicle26, vehicle27, vehicle28);

        modelRepository.saveAll(models);
        vehicleRepository.saveAll(vehicles);

        generateReservations();
    }

    public void generateRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        roles.add(new Role("ADMIN"));
        roleRepository.saveAll(roles);
        generateFleetDatas();
    }

    public void generateReservations() {
//Strasbourg
        Reservations resa1 = new Reservations(new Date(1693994400000L), new Date(1694599200000L), "Test1");
        resa1.setUser(userRepository.findById(1L).get());
        resa1.setVehicle(vehicleRepository.findById(1L).get());

        Reservations resa2 = new Reservations(new Date(1694340000000L), new Date(1725962400000L), "Test2 en cours");
        resa2.setUser(userRepository.findById(2L).get());
        resa2.setVehicle(vehicleRepository.findById(2L).get());

        Reservations resa6 = new Reservations(new Date(1725963000000L), new Date(1726567800000L), "Test6 à venir");
        resa6.setUser(userRepository.findById(2L).get());
        resa6.setVehicle(vehicleRepository.findById(6L).get());
//Paris
        Reservations resa3 = new Reservations(new Date(1694163600000L), new Date(1694768400000L), "Test3");
        resa3.setUser(userRepository.findById(3L).get());
        resa3.setVehicle(vehicleRepository.findById(8L).get());

        Reservations resa7 = new Reservations(new Date(1694340000000L), new Date(1725962400000L), "Test7 en cours");
        resa7.setUser(userRepository.findById(3L).get());
        resa7.setVehicle(vehicleRepository.findById(9L).get());

        Reservations resa4 = new Reservations(new Date(1725963000000L), new Date(1726567800000L), "Test4 à venir");
        resa4.setUser(userRepository.findById(4L).get());
        resa4.setVehicle(vehicleRepository.findById(14L).get());
//Marseille
        Reservations resa5 = new Reservations(new Date(1694336400000L), new Date(1694941200000L), "Test5");
        resa5.setUser(userRepository.findById(5L).get());
        resa5.setVehicle(vehicleRepository.findById(26L).get());

        Reservations resa8 = new Reservations(new Date(1725963000000L), new Date(1726567800000L), "Test8 à venir");
        resa8.setUser(userRepository.findById(5L).get());
        resa8.setVehicle(vehicleRepository.findById(27L).get());

        Reservations resa9 = new Reservations(new Date(1694340000000L), new Date(1725962400000L), "Test9 en cours");
        resa9.setUser(userRepository.findById(5L).get());
        resa9.setVehicle(vehicleRepository.findById(24L).get());

        List<Reservations> reservations = Arrays.asList(resa1, resa2, resa3, resa4, resa5, resa6, resa7, resa8, resa9);
        reservationsRepository.saveAll(reservations);

    }
}
