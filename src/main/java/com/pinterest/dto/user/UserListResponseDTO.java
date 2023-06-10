package com.pinterest.dto.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class UserListResponseDTO implements Serializable {

    private List<UserRequestDTO> users;
}
