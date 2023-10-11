package com.project.carfleet.service;

import com.project.carfleet.dto.UserDto;
import com.project.carfleet.entity.UserEntity;
import com.project.carfleet.repository.RoleRepository;
import com.project.carfleet.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final RoleRepository roleRepository;
    private final ConvertToDto convertToDto;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder, RoleRepository roleRepository, ConvertToDto convertToDto) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepository = roleRepository;
        this.convertToDto = convertToDto;
    }

    public boolean checkHashedPassword(String password) {
        if (password != null) {
            Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{8,}");
            return pattern.matcher(password).matches();
        } else {
            throw new RuntimeException("Aucun mot de passe trouvé");
        }
    }

    public boolean verifyHashedPasswordDuringLogin(String password, String hashedPassword) {
        return bcryptEncoder.matches(password, hashedPassword);
    }

    public UserEntity getUserByCP(String CP) {
        if (userRepository.findByCP(CP).isPresent()) {
            return userRepository.findByCP(CP).get();
        }
        throw new RuntimeException("Le CP n'existe pas");
    }

    public UserDto login(UserDto userDto) {
        UserEntity user = getUserByCP(userDto.getCP());
        if (!verifyHashedPasswordDuringLogin(userDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        Long roleId = user.getRole().getId();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setNbLicence(user.getNbLicence());
        userDto.setRole(roleRepository.findById(roleId).get());
        userDto.setFleet(convertToDto.convertFleetToDto(user.getFleet()));
        userDto.setPassword("hidden");
        return userDto;
    }

    public boolean checkCP(String CP) {
        return userRepository.findByCP(CP).isEmpty();
    }

}
