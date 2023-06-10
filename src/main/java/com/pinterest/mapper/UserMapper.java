package com.pinterest.mapper;

import com.pinterest.dto.user.UserResponseDTO;
import com.pinterest.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UserMapper {

    public UserResponseDTO toUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .urlImage(user.getUrlImage())
                .names(user.getNames())
                .surname(user.getSurname())
                .information(user.getInformation())
                .createdAt(user.getCreatedAt().toString())
                .updatedAt(user.getUpdateAt().toString())
                .build();
    }

    public User toUser(UserResponseDTO userResponseDTO) {
        return User.builder()
                .id(userResponseDTO.getId())
                .username(userResponseDTO.getUsername())
                .password(userResponseDTO.getPassword())
                .email(userResponseDTO.getEmail())
                .urlImage(userResponseDTO.getUrlImage())
                .names(userResponseDTO.getNames())
                .surname(userResponseDTO.getSurname())
                .information(userResponseDTO.getInformation())
                .createdAt(LocalDateTime.parse(userResponseDTO.getCreatedAt()))
                .updateAt(LocalDateTime.parse(userResponseDTO.getUpdatedAt()))
                .build();
    }


}
