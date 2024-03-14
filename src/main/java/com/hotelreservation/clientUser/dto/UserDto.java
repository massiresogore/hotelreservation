package com.hotelreservation.clientUser.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        @NotEmpty
        String email
) {
}
