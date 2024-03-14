package com.hotelreservation.clientUser.converter;

import com.hotelreservation.clientUser.ClientUser;
import com.hotelreservation.clientUser.dto.ClientUserDto;
import com.hotelreservation.role.Role;
import com.hotelreservation.role.converter.RoleDtoToRoleConverter;
import com.hotelreservation.role.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class ClientUserDtoToClientUserConverter implements Converter<ClientUserDto, ClientUser> {

    private final RoleDtoToRoleConverter roleDtoToRoleConverter;

    public ClientUserDtoToClientUserConverter(RoleDtoToRoleConverter roleDtoToRoleConverter) {
        this.roleDtoToRoleConverter = roleDtoToRoleConverter;
    }


    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ClientUser convert(ClientUserDto source) {
        ClientUser user = new ClientUser();
        user.setEmail(source.email());
        user.setId(source.id());
        user.setFirstName(source.firstName());
        user.setLastName(source.lastName());

        //Covertir roleDto en role
        Collection<RoleDto> roleDtoCollection = source.role();
        Collection<Role> roleCollection = roleDtoCollection.stream().map(roleDtoToRoleConverter::convert).toList();

        user.setRoles(roleCollection);
        return user;
    }

    /** A voir plutard
     * Construct a composed {@link Converter} that first applies this {@link Converter}
     * to its input, and then applies the {@code after} {@link Converter} to the
     * result.
     *
     * @param after the {@link Converter} to apply after this {@link Converter}
     *              is applied
     * @return a composed {@link Converter} that first applies this {@link Converter}
     * and then applies the {@code after} {@link Converter}
     * @since 5.3
     */
   /* @Override
    public <U> Converter<ClientUserDto, U> andThen(Converter<? super ClientUser, ? extends U> after) {
        return Converter.super.andThen(after);
    }*/
}
