package com.pinterest.service;

import com.pinterest.dto.user.UserResponseDTO;
import com.pinterest.exception.ResourceExistsException;
import com.pinterest.exception.ResourceNotFoundException;
import com.pinterest.mapper.UserMapper;
import com.pinterest.model.User;
import com.pinterest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO getUserById(Long userId) {
        Optional<UserResponseDTO> user =userRepository.findById(userId).map(userMapper::toUserResponseDTO);
        return user.orElseThrow(() -> new ResourceNotFoundException("Brand with id=[" + userId + "] not found"));
    }

    public UserResponseDTO createUser(UserResponseDTO userResponseDTO) {
        Optional<User> foundUser = userRepository.findUserByEmailIgnoreCase(userResponseDTO.getEmail());

        if(foundUser.isEmpty()) {

            User userSaved = userRepository.save(userMapper.toUser(userResponseDTO));
            return userMapper.toUserResponseDTO(userSaved);
        }
        throw new ResourceExistsException("User with name=[" + userResponseDTO.getEmail() + "] already exists");
    }

}
