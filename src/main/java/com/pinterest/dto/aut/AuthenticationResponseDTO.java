package com.pinterest.dto.aut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthenticationResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3899254222504179823L;

    private String accessToken;
    private String refreshToken;

}
