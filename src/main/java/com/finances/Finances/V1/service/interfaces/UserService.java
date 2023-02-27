package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.user.UserRequest;
import com.finances.Finances.V1.dto.user.UserResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest user);

    List<UserResponse> allUsers();

    Map<String,String> deleteUser(UUID userId);
}
