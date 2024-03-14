package com.hotelreservation.clientUser;

import com.hotelreservation.role.Role;
import com.hotelreservation.role.RoleRepository;
import com.hotelreservation.system.exception.UserAllReadyExistException;

import java.util.Collections;

public class ClientUserService {
    private final ClientUserRepository clientUserRepository;
    //private  PasswordEncoder passwordEncoder; //plutard pour la sécurité
    private final RoleRepository roleRepository;

    public ClientUserService(ClientUserRepository clientUserRepository, RoleRepository roleRepository) {
        this.clientUserRepository = clientUserRepository;
        this.roleRepository = roleRepository;
    }

    //il faut d'abord crée un role avant d'ajouter un client
    public ClientUser registerClientUser(ClientUser user) {

        if (clientUserRepository.existsByEmail(user.getEmail())){
            throw new UserAllReadyExistException(user.getEmail());
        }


       // user.setPassword(passwordEncoder.encode(user.getPassword()));//plutard pour la sécurité
        //System.out.println(user.getPassword());

        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singletonList(userRole));


        return clientUserRepository.save(user);
    }


}
