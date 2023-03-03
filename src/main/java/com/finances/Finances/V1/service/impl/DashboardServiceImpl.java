package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.dto.dashboard.DashboardResponse;
import com.finances.Finances.V1.dto.spent.SpentResponse;
import com.finances.Finances.V1.dto.type_spent.GraphicTypeSpent;
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
        final BigDecimal[] maxValue = {new BigDecimal(0)};

        BeanUtils.copyProperties(user,userResponse);
        BeanUtils.copyProperties(user.getWallet(),walletResponse);

        DashboardResponse dashboardResponse = DashboardResponse.builder()
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
                                    GraphicTypeSpent response = new GraphicTypeSpent();
                                    BeanUtils.copyProperties(m,response);

                                    BigDecimal[] newTotalSpent = {new BigDecimal(0)};
                                    response.setSpentList(m.getSpentList().stream()
                                            .filter(spent -> ChronoUnit.MONTHS.between(spent.getDate(),LocalDate.now()) < 1)
                                            .map(spent -> {
                                                newTotalSpent[0] = newTotalSpent[0].add(spent.getValue());
                                                SpentResponse spentResponse = new SpentResponse();
                                                BeanUtils.copyProperties(spent,spentResponse);
                                                return spentResponse;
                                            }).collect(Collectors.toList()));

                                    if(newTotalSpent[0].compareTo(maxValue[0]) > 0)
                                        maxValue[0] = newTotalSpent[0];

                                    response.setTotalSpent(newTotalSpent[0]);
                                    return response;
                                })
                                .collect(Collectors.toList())
                        :
                        null
                )
                .graphicLine(BigDecimalUtil.graphicLineCalculate(user.getTypeSpentList()))
                .build();

        dashboardResponse.getTypeSpent().stream()
                .peek(colum ->{
                    double value = Double.parseDouble(colum.getTotalSpent().replace(",","."));
                    colum.setColumPercentage((100*value)/(maxValue[0].doubleValue()));
                }).collect(Collectors.toList());

        return dashboardResponse;
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
                                    GraphicTypeSpent response = new GraphicTypeSpent();
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
