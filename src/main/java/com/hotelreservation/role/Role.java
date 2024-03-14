package com.hotelreservation.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelreservation.clientUser.ClientUser;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore //? pourquoi ignorer ?
    @ManyToMany(mappedBy = "roles")
    private Collection<ClientUser> users = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    /**
     * Assigner un role à l'utilisatteur
     *
     * @param user , c'est l'intance du l'utilisatteur
     */
    public void assignRoleToUser(ClientUser user){
        user.getRoles().add(this);
        this.getUsers().add(user);
    }

    /**
     * Retire un role à l'utilisatteur
     *
     * @param user , c'est l'intance du l'utilisatteur
     */
    public void removeUserFromRole(ClientUser user){
        user.getRoles().remove(this);
        this.getUsers().remove(user);

    }

    /**
     * Supprime tous les utilisatteurs par role
     */
    public void removeAllUsersFromRole(){
        if (this.getUsers() != null){
            List<ClientUser> roleUsers = this.getUsers().stream().toList();
            roleUsers.forEach(this :: removeUserFromRole);
        }
    }

    /**
     * Récupère le role d'un utilisatteur
     */
    public  String getName(){
        return name != null? name : "";
    }

    /*Getter et setter*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ClientUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<ClientUser> users) {
        this.users = users;
    }
}
