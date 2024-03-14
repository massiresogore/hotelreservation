package com.hotelreservation.role.dto;

import jakarta.validation.constraints.NotEmpty;

public record RoleDto(
        @NotEmpty
        String name) {
}
