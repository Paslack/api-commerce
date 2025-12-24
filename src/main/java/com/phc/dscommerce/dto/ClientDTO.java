package com.phc.dscommerce.dto;

import com.phc.dscommerce.entities.User;
import lombok.Getter;

@Getter
public class ClientDTO {

    private Long id;
    private String name;

    public ClientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
