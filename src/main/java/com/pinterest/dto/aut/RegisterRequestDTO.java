package com.pinterest.dto.aut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8650283910996636524L;

    private String names;
    private String surnames;
    private String email;
    private String password;
    private String username;

}
