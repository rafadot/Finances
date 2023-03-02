package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.dto.spent.SpentResponse;
import com.finances.Finances.V1.dto.type_spent.TypeSpentResponse;
import com.finances.Finances.V1.dto.user.UserResponse;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.DashboardService;
import com.finances.Finances.V1.util.BigDecimalUtil;
import com.finances.Finances.V1.util.NextBilling;
import com.finances.Finances.V1.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    @Override
    public DashboardResponse getUserDashboardLogin(User user) {
        UserResponse userResponse = new UserResponse();
        WalletResponse walletResponse = new WalletResponse();
        List<BigDecimal> typeSpentValue = new ArrayList<>();

        BeanUtils.copyProperties(user,userResponse);
        BeanUtils.copyProperties(user.getWallet(),walletResponse);

        return DashboardResponse.builder()
                .user(userResponse)
                .wallet(walletResponse)
                .nextBilling(user.getBilling().size() > 0 ?
                        NextBilling.getNextBillingList(user.getBilling())
                                .stream()
                                .map(m ->{
                                    BillingResponse response = new BillingResponse();
                                    BeanUtils.copyProperties(m,response);
                                    return response;
                                }).collect(Collectors.toList())
                        :
                        null
                )
                .typeSpent(user.getTypeSpentList().size() > 0 ?
                                user.getTypeSpentList()
                                        .stream()
                                        .map(m ->{
                                            TypeSpentResponse response = new TypeSpentResponse();
                                            BeanUtils.copyProperties(m,response);
                                            typeSpentValue.add(m.getTotalSpent());
                                            response.setSpentList(m.getSpentList().stream().map(spent -> {
                                                SpentResponse spentResponse = new SpentResponse();
                                                BeanUtils.copyProperties(spent,spentResponse);
                                                return spentResponse;
                                            }).collect(Collectors.toList()));
                                            return response;
                                        }).collect(Collectors.toList())
                                :
                                null
                        )
                .graphicLine(BigDecimalUtil.graphicLineCalculate(typeSpentValue))
                .build();
    }

    @Override
    public DashboardResponse getUserDashboard(UUID userID) {
        User user = UserUtil.valid(userID,userRepository);

        UserResponse userResponse = new UserResponse();
        WalletResponse walletResponse = new WalletResponse();

        BeanUtils.copyProperties(user,userResponse);
        BeanUtils.copyProperties(user.getWallet(),walletResponse);

        return DashboardResponse.builder()
                .user(userResponse)
                .wallet(walletResponse)
                .nextBilling(user.getBilling().size() > 0 ?
                        NextBilling.getNextBillingList(user.getBilling())
                                .stream()
                                .map(m ->{
                                    BillingResponse response = new BillingResponse();
                                    BeanUtils.copyProperties(m,response);
                                    return response;
                                }).collect(Collectors.toList())
                        :
                        null
                )
                .typeSpent(user.getTypeSpentList().size() > 0 ?
                        user.getTypeSpentList()
                                .stream()
                                .map(m ->{
                                    TypeSpentResponse response = new TypeSpentResponse();
                                    BeanUtils.copyProperties(m,response);
                                    return response;
                                }).collect(Collectors.toList())
                        :
                        null
                )
                .build();
    }
}
