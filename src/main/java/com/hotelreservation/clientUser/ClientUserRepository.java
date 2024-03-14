package com.hotelreservation.clientUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    Optional<ClientUser> findByEmail(String email);
}
