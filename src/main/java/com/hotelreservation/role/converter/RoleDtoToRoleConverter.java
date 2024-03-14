package com.hotelreservation.role.converter;

import com.hotelreservation.role.Role;
import com.hotelreservation.role.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoToRoleConverter implements Converter<RoleDto, Role> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Role convert(RoleDto source) {
        return new Role(source.name());
    }
}
