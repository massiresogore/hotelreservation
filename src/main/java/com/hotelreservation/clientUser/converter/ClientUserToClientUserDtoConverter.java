package com.hotelreservation.clientUser.converter;

import com.hotelreservation.clientUser.ClientUser;
import com.hotelreservation.clientUser.dto.ClientUserDto;
import com.hotelreservation.role.Role;
import com.hotelreservation.role.converter.RoleToRoleDtoConverter;
import com.hotelreservation.role.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ClientUserToClientUserDtoConverter implements Converter<ClientUser, ClientUserDto> {
    private final RoleToRoleDtoConverter roleToRoleDtoConverter;

    public ClientUserToClientUserDtoConverter(RoleToRoleDtoConverter roleToRoleDtoConverter) {
        this.roleToRoleDtoConverter = roleToRoleDtoConverter;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ClientUserDto convert(ClientUser source) {
        //Convert role en roleDto
        Collection<Role> roleCollection  = source.getRoles();
        Collection<RoleDto> roleDtoCollection = roleCollection.stream().map(roleToRoleDtoConverter::convert).toList();

        return new ClientUserDto(
          source.getId(),
          source.getFirstName(),
          source.getLastName(),
          source.getEmail(),
          roleDtoCollection
        );
    }
}
