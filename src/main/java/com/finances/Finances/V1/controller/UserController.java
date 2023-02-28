package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.user.UserRequest;
import com.finances.Finances.V1.dto.user.AllUserResponse;
import com.finances.Finances.V1.dto.user.UserResponse;
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

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AllUserResponse>> allUsers(){
        return new ResponseEntity<>(userService.allUsers(),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> deleteUser(@RequestParam UUID userId){
        return new ResponseEntity<>(userService.deleteUser(userId),HttpStatus.OK);
    }
}
