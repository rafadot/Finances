package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import com.finances.Finances.V1.model.Login;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.LoginService;
import com.finances.Finances.V1.util.NextBilling;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
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

        UserResponse userResponse = new UserResponse();
        WalletResponse walletResponse = new WalletResponse();
        BillingResponse billingResponse = new BillingResponse();

        BeanUtils.copyProperties(user.get(),userResponse);
        BeanUtils.copyProperties(user.get().getWallet(),walletResponse);

        if(user.get().getBilling().size() >= 1)
            BeanUtils.copyProperties(NextBilling.getNextBillingList(user.get().getBilling()),billingResponse);

        return DashboardResponse.builder()
                .user(userResponse)
                .wallet(walletResponse)
                .nextBilling(user.get().getBilling().size() >= 1 ? billingResponse : null)
                .build();
    }
}
