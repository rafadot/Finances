package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.spent.SpentRequest;
import com.finances.Finances.V1.dto.spent.SpentResponse;
import com.finances.Finances.V1.model.Spent;
import com.finances.Finances.V1.model.TypeSpent;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.SpentRepository;
import com.finances.Finances.V1.repository.TypeSpentRepository;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.repository.WalletRepository;
import com.finances.Finances.V1.service.interfaces.SpentService;
import com.finances.Finances.V1.util.UserUtil;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpentServiceImpl implements SpentService {

    private final SpentRepository spentRepository;
    private final UserRepository userRepository;
    private final TypeSpentRepository typeSpentRepository;

    @Override
    public SpentResponse create(UUID userId, String typeName, SpentRequest request) {

        if(request.getValue().compareTo(new BigDecimal(0)) == 0)
            throw new BadRequestException("Por favor digite um valor maior que R$ 0,00");

        if(typeName.equals(""))
            typeName = "Outros gastos";

        User user = UserUtil.valid(userId,userRepository);

        for(TypeSpent t : user.getTypeSpentList()){
            if(t.getName().equalsIgnoreCase(typeName)){

                if(request.getDate() == null)
                    request.setDate(LocalDate.now());

                Spent spent = new Spent();
                BeanUtils.copyProperties(request,spent);
                spent.setTypeName(typeName);
                spentRepository.save(spent);

                t.getSpentList().add(spent);
                t.setTotalSpent(t.getTotalSpent().add(spent.getValue()));
                typeSpentRepository.save(t);

                user.getWallet().setMonthlyExpense(user.getWallet().getMonthlyExpense().add(spent.getValue()));
                user.getWallet().setCurrentBalance(user.getWallet().getCurrentBalance().subtract(spent.getValue()));
                userRepository.save(user);

                SpentResponse response = new SpentResponse();
                BeanUtils.copyProperties(spent,response);

                return response;
            }
        }

        throw new BadRequestException("Categoria de gastos não encontrada");

    }
}
