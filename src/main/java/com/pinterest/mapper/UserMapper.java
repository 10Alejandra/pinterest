package com.pinterest.mapper;

import com.pinterest.dto.user.UserRequestDTO;
import com.pinterest.dto.user.UserResponseDTO;
import com.pinterest.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


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
                .surname(user.getSurnames())
                .information(user.getInformation())
                .createdAt(user.getCreatedAt().toString())
                .updatedAt(user.getUpdateAt().toString())
                .build();
    }

    public User toUser(UserRequestDTO userRequestDTO) {
        return User.builder()
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .email(userRequestDTO.getEmail())
                .urlImage(userRequestDTO.getUrlImage())
                .names(userRequestDTO.getNames())
                .surnames(userRequestDTO.getSurnames())
                .information(userRequestDTO.getInformation())
                .build();
    }

}
