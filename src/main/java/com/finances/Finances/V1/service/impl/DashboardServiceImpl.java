package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.DashboardService;
import com.finances.Finances.V1.util.NextBilling;
import com.finances.Finances.V1.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    @Override
    public DashboardResponse getUserDashboardLogin(User user) {
        UserResponse userResponse = new UserResponse();
        WalletResponse walletResponse = new WalletResponse();
        BillingResponse billingResponse = new BillingResponse();

        BeanUtils.copyProperties(user,userResponse);
        BeanUtils.copyProperties(user.getWallet(),walletResponse);

        if(user.getBilling().size() >= 1)
            BeanUtils.copyProperties(NextBilling.getNextBillingList(user.getBilling()),billingResponse);

        return DashboardResponse.builder()
                .user(userResponse)
                .wallet(walletResponse)
                .nextBilling(user.getBilling().size() >= 1 ? billingResponse : null)
                .build();
    }

    @Override
    public DashboardResponse getUserDashboard(UUID userID) {
        User user = UserUtil.valid(userID,userRepository);

        UserResponse userResponse = new UserResponse();
        WalletResponse walletResponse = new WalletResponse();
        BillingResponse billingResponse = new BillingResponse();

        BeanUtils.copyProperties(user,userResponse);
        BeanUtils.copyProperties(user.getWallet(),walletResponse);

        if(user.getBilling().size() >= 1)
            BeanUtils.copyProperties(NextBilling.getNextBillingList(user.getBilling()),billingResponse);

        return DashboardResponse.builder()
                .user(userResponse)
                .wallet(walletResponse)
                .nextBilling(user.getBilling().size() >= 1 ? billingResponse : null)
                .build();
    }
}
