package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.type_spent.TypeSpentRequest;
import com.finances.Finances.V1.dto.type_spent.TypeSpentResponse;
import com.finances.Finances.V1.model.TypeSpent;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.TypeSpentRepository;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.TypeSpentService;
import com.finances.Finances.V1.util.UserUtil;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypeSpentServiceImpl implements TypeSpentService {

    private final TypeSpentRepository typeSpentRepository;

    private final UserRepository userRepository;

    @Override
    public TypeSpentResponse create(UUID userid, TypeSpentRequest request) {
        User user = UserUtil.valid(userid, userRepository);

        for(TypeSpent typeSpent : user.getTypeSpentList()){
            if(typeSpent.getName().equalsIgnoreCase(request.getName()))
                throw new BadRequestException("Esse nome já foi atribuido a um tipo de gasto");
        }

        TypeSpent typeSpent = new TypeSpent();
        typeSpent.setTotalSpent(new BigDecimal(0));
        BeanUtils.copyProperties(request,typeSpent);
        typeSpentRepository.save(typeSpent);

        user.getTypeSpentList().add(typeSpent);
        userRepository.save(user);

        TypeSpentResponse response = new TypeSpentResponse();
        BeanUtils.copyProperties(typeSpent,response);

        return response;
    }

    @Override
    public List<TypeSpentResponse> listTypeSpent(UUID userId) {
        return null;
    }

}
