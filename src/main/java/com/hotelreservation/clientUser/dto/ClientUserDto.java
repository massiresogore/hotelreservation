package com.hotelreservation.clientUser.dto;

import com.hotelreservation.role.Role;
import com.hotelreservation.role.dto.RoleDto;
import jakarta.validation.constraints.NotEmpty;

import java.util.Collection;

public record ClientUserDto(
        Long id,
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        @NotEmpty
        String email,
        Collection<RoleDto> role

) {
}
