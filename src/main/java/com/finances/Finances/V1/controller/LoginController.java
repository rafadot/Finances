package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.model.Login;
import com.finances.Finances.V1.service.interfaces.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<DashboardResponse> login(@RequestBody Login login){
        return new ResponseEntity<>(loginService.login(login), HttpStatus.OK);
    }

}
