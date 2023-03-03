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
import com.finances.Finances.V1.util.DatesUtil;
import com.finances.Finances.V1.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        BeanUtils.copyProperties(user,userResponse);
        BeanUtils.copyProperties(user.getWallet(),walletResponse);

        return DashboardResponse.builder()
                .user(userResponse)
                .wallet(walletResponse)
                .nextBilling(user.getBilling().size() > 0 ?
                        DatesUtil.getNextBillingList(user.getBilling())
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
                                        .filter(typeSpent -> typeSpent.getTotalSpent().compareTo(new BigDecimal(0)) > 0)
                                        .map(m ->{
                                            TypeSpentResponse response = new TypeSpentResponse();
                                            BeanUtils.copyProperties(m,response);

                                            BigDecimal newTotalSpent = new BigDecimal(0);
                                            response.setSpentList(m.getSpentList().stream()
                                                    .filter(spent -> ChronoUnit.MONTHS.between(LocalDate.now(),spent.getDate()) < 1)
                                                    .map(spent -> {
                                                        m.setTotalSpent(newTotalSpent.add(new BigDecimal(spent.getValue().toString())));
                                                        SpentResponse spentResponse = new SpentResponse();
                                                        BeanUtils.copyProperties(spent,spentResponse);
                                                        return spentResponse;
                                                    }).collect(Collectors.toList()));

                                            response.setTotalSpent(m.getTotalSpent());
                                            return response;
                                        }).collect(Collectors.toList())
                                :
                                null
                        )
                .graphicLine(BigDecimalUtil.graphicLineCalculate(user.getTypeSpentList()))
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
                        DatesUtil.getNextBillingList(user.getBilling())
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
                                .filter(typeSpent -> typeSpent.getTotalSpent().compareTo(new BigDecimal(0)) > 0)
                                .map(m ->{
                                    TypeSpentResponse response = new TypeSpentResponse();
                                    BeanUtils.copyProperties(m,response);

                                    BigDecimal newTotalSpent = new BigDecimal(0);
                                    response.setSpentList(m.getSpentList().stream()
                                            .filter(spent -> ChronoUnit.MONTHS.between(LocalDate.now(),spent.getDate()) < 1)
                                            .map(spent -> {
                                                m.setTotalSpent(newTotalSpent.add(new BigDecimal(spent.getValue().toString())));
                                                SpentResponse spentResponse = new SpentResponse();
                                                BeanUtils.copyProperties(spent,spentResponse);
                                                return spentResponse;
                                            }).collect(Collectors.toList()));

                                    response.setTotalSpent(m.getTotalSpent());
                                    return response;
                                }).collect(Collectors.toList())
                        :
                        null
                )
                .graphicLine(BigDecimalUtil.graphicLineCalculate(user.getTypeSpentList()))
                .build();
    }
}
