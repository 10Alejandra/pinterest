package com.pinterest.dto.aut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthenticationRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4697393672674004040L;

    private String email;
    private String password;

}
