package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.dto.user.UserRequest;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.service.interfaces.DashboardService;
import com.finances.Finances.V1.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DashboardService dashboardService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.CREATED);
    }

    @GetMapping("/dev")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(userService.allUsers(),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> deleteUser(@RequestParam UUID userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getUserDashboard(@RequestParam UUID userID){
        return new ResponseEntity<>(dashboardService.getUserDashboard(userID), HttpStatus.OK);
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<Map<String,String>> forgetPassword(@RequestParam String email){
        return new ResponseEntity<>(userService.forgetPassword(email),HttpStatus.OK);
    }

    @GetMapping("/validForgetPassword")
    public ResponseEntity<Boolean> validForgetPassword(@RequestParam String email, @RequestParam int code){
        return new ResponseEntity<>(userService.validForgetPassword(email,code),HttpStatus.OK);
    }

    @GetMapping("/patchForgetPassword")
    public ResponseEntity<Map<String,String>> patchForgetPassword(@RequestParam String email,@RequestParam String password){
        return new ResponseEntity<>(userService.patchForgetPassword(email,password),HttpStatus.OK);
    }

    @PatchMapping("/patchPassword")
    public ResponseEntity<Map<String,String>> patchPassword(@RequestParam UUID userID, @RequestParam String currentPassword, @RequestParam String newPassword){
        return new ResponseEntity<>(userService.patchPassword(userID,currentPassword,newPassword),HttpStatus.OK);
    }

}
