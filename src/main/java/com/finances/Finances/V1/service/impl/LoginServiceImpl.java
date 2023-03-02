package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.model.Login;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.DashboardService;
import com.finances.Finances.V1.service.interfaces.LoginService;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final DashboardService dashboardService;
    private final PasswordEncoder encoder;

    @Override
    public DashboardResponse login(Login login) {
        Optional<User> user = userRepository.findByEmail(login.getEmailOrUsername());

        if(user.isEmpty()){
            user = userRepository.findByUserName(login.getEmailOrUsername());
            if(user.isEmpty())
                throw new BadRequestException("Email ou nome de usuário invalido");
        }

        if(!encoder.matches(login.getPassword(),user.get().getPassword()))
            throw new BadRequestException("Senha inválida");

        return dashboardService.getUserDashboardLogin(user.get());
    }
}
