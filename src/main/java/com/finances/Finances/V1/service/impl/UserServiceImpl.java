package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.user.UserRequest;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.model.Wallet;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.repository.WalletRepository;
import com.finances.Finances.V1.service.interfaces.UserService;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = new User();

        BeanUtils.copyProperties(userRequest,user);
        user.setPassword(encoder.encode(user.getPassword()));

        Wallet wallet = Wallet.builder()
                .currentBalance(new BigDecimal("0.00"))
                .monthlyExpense(new BigDecimal("0.00"))
                .build();
        walletRepository.save(wallet);
        user.setWallet(wallet);

        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);

        return userResponse;
    }

    @Override
    public List<UserResponse> allUsers() {
        return userRepository.findAll().stream().map(m ->{
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(m,response);
            return response;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String,String> deleteUser(UUID userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty())
            throw new BadRequestException("Usuário não existe");

        String username = user.get().getUserName();
        userRepository.deleteById(userId);

        Map<String,String> response = new HashMap<>();
        response.put("message",username + " deletado com sucesso!");

        return response;
    }
}
