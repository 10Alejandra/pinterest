package com.pinterest.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResponseDTO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String urlImage;
    private String names;
    private String surname;
    private String information;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatedAt;

}
