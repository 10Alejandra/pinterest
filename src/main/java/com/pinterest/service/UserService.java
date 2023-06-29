package com.pinterest.service;

import com.pinterest.dto.user.UserRequestDTO;
import com.pinterest.dto.user.UserResponseDTO;
import com.pinterest.exception.ResourceNotFoundException;
import com.pinterest.mapper.UserMapper;
import com.pinterest.model.User;
import com.pinterest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO getUserById(Long userId) {
        return userMapper.toUserResponseDTO(getUserByIdOrThrowException(userId));
    }

    public User getUserByIdOrThrowException(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with id=[" + userId + "] not found"));
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User userSaved = userRepository.save(userMapper.toUser(userRequestDTO));
        return userMapper.toUserResponseDTO(userSaved);

    }
}
