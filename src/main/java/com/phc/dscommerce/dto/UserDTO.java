package com.phc.dscommerce.dto;

import com.phc.dscommerce.entities.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private List<String> roles = new ArrayList<>();


    public UserDTO(Long id, String name, String email, String phone, LocalDate birthDate, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.roles = roles;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        for(GrantedAuthority role: entity.getRoles()) {
            roles.add(role.getAuthority());
        }
    }
}
