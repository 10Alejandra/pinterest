package com.pinterest.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
public class UserRequestDTO implements Serializable {

    @NonNull
    private String username;
    private String password;
    private String email;
    private String urlImage;
    private String names;
    private String surnames;
    private String information;

}
