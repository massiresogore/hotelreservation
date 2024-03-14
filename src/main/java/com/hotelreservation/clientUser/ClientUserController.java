package com.hotelreservation.clientUser;

import com.hotelreservation.clientUser.converter.ClientUserDtoToClientUserConverter;
import com.hotelreservation.clientUser.converter.ClientUserToClientUserDtoConverter;
import com.hotelreservation.clientUser.dto.ClientUserDto;
import com.hotelreservation.system.Result;
import com.hotelreservation.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class ClientUserController {
    private final ClientUserService clientUserService;
    private final ClientUserToClientUserDtoConverter ClientUserToClientUserDtoConverter ;
    private final ClientUserDtoToClientUserConverter ClientUserDtoToClientUserConverter;

    public ClientUserController(ClientUserService clientUserService, com.hotelreservation.clientUser.converter.ClientUserToClientUserDtoConverter clientUserToClientUserDtoConverter, com.hotelreservation.clientUser.converter.ClientUserDtoToClientUserConverter clientUserDtoToClientUserConverter) {
        this.clientUserService = clientUserService;
        ClientUserToClientUserDtoConverter = clientUserToClientUserDtoConverter;
        ClientUserDtoToClientUserConverter = clientUserDtoToClientUserConverter;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {
                    MediaType.APPLICATION_ATOM_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public Result createClientUser(
            @RequestParam("firstName") String firstname,
            @RequestParam("lastName") String lastname,
            @RequestParam("email") String email,
            @RequestParam("password") String password
           ){
      return new Result(true,
              StatusCode.SUCCESS,
              "user created successfully",
              this.ClientUserToClientUserDtoConverter
                      .convert(this.clientUserService
                              .registerClientUser(new ClientUser(firstname,lastname,email,password))
              )
      );
    }

    @GetMapping
    public Result getUsers(){

        return new Result(true,
                StatusCode.SUCCESS,
                "all users",
                clientUserService
                        .findUsers()
                        .stream()
                        .map(ClientUserToClientUserDtoConverter::convert)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{email}")
    public Result getUserByEmail(@PathVariable("email") String email){
        return new Result(true,
                StatusCode.SUCCESS,
                "find user success",
                ClientUserToClientUserDtoConverter.convert(this.clientUserService.findUser(email))
        );
    }

    @DeleteMapping("/{email}")
    public Result deleteUserByEmail(@PathVariable("email") String email){
        this.clientUserService.deleteUser(email);
        return new Result(true,StatusCode.SUCCESS, "delete user success");
    }




}
