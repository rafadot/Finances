package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.billing.BillingRequest;
import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.model.Billing;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.BillingRepository;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.BillingService;
import com.finances.Finances.V1.service.interfaces.UserService;
import com.finances.Finances.V1.util.UserUtil;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;
    private final UserRepository userRepository;


    @Override
    public BillingResponse create(UUID userId, BillingRequest billingRequest) {
        if(billingRequest.getValue().compareTo(new BigDecimal(0)) == 0)
            throw new BadRequestException("Por favor digite um valor maior que R$ 0,00");

        User user = UserUtil.valid(userId,userRepository);

        Billing billing = new Billing();
        BeanUtils.copyProperties(billingRequest,billing);
        billingRepository.save(billing);

        user.getBilling().add(billing);
        userRepository.save(user);

        BillingResponse response = new BillingResponse();
        BeanUtils.copyProperties(billing,response);

        return response;
    }

    @Override
    public Map<String, String> delete(UUID userId, UUID billingId) {
        User user = UserUtil.valid(userId,userRepository);
        Map<String,String> response = new HashMap<>();

        for(Billing billing : user.getBilling()){
            if(billing.getId().equals(billingId)){
                response.put("message",billing.getTitle() + " deletado com sucesso!");
                user.getBilling().remove(billing);
                billingRepository.deleteById(billingId);
                userRepository.save(user);
                return response;
            }
        }
        response.put("message","Cobrança não existe");
        return response;
    }
}
