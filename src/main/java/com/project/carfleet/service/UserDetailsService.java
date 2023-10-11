package com.project.carfleet.service;

import com.project.carfleet.jwt.UserPrincipal;
import com.project.carfleet.repository.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPrincipal loadUserByCP(String CP) {
        if (userRepository.findByCP(CP).isPresent()) {
            return new UserPrincipal(userRepository.findByCP(CP).get());
        }
        throw new RuntimeException("Aucun utilisateur trouvé par ce numéro de CP");
    }
}
