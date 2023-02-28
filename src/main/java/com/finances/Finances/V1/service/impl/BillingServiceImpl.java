package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.billing.BillingRequest;
import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.model.Billing;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.BillingRepository;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.BillingService;
import com.finances.Finances.V1.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    @Override
    public BillingResponse create(UUID userId, BillingRequest billingRequest) {
        User user = userService.valid(userId);

        Billing billing = new Billing();
        BeanUtils.copyProperties(billingRequest,billing);
        billingRepository.save(billing);

        user.getBilling().add(billing);
        userRepository.save(user);

        BillingResponse response = new BillingResponse();
        BeanUtils.copyProperties(billing,response);

        return response;
    }
}
