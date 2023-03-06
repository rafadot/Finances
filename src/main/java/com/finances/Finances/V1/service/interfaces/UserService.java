package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.user.UserRequest;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {
    UserResponse create(UserRequest user);
    List<User> allUsers();
    Map<String,String> deleteUser(UUID userId);
    Map<String,String> forgetPassword(String email);
    Boolean validForgetPassword(String email, int code);
    Map<String,String> patchForgetPassword(String email, String password);
    Map<String,String> patchPassword(UUID userID, String currentPassword, String newPassword);


}
