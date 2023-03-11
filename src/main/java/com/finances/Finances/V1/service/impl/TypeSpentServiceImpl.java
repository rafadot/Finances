package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.spent.SpentResponse;
import com.finances.Finances.V1.dto.type_spent.TypeSpentRequest;
import com.finances.Finances.V1.dto.type_spent.TypeSpentResponse;
import com.finances.Finances.V1.model.TypeSpent;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.repository.TypeSpentRepository;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.service.interfaces.TypeSpentService;
import com.finances.Finances.V1.util.DatesUtil;
import com.finances.Finances.V1.util.UserUtil;
import com.finances.Finances.exceptions.management.BadRequestException;
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

    @Override
    public List<TypeSpentResponse> typeSpentFiltered(UUID userId, String date) {
        User user = UserUtil.valid(userId,userRepository);
        List<TypeSpent> typeSpentFiltered = DatesUtil.filterSpentDays(user.getTypeSpentList(),date);

        return typeSpentFiltered
                .stream()
                .map(typeSpent -> {
                    TypeSpentResponse typeSpentResponse = new TypeSpentResponse();
                    BeanUtils.copyProperties(typeSpent,typeSpentResponse);
                    typeSpentResponse.setSpentList(typeSpent.getSpentList()
                            .stream()
                            .map(spent -> {
                               SpentResponse spentResponse = new SpentResponse();
                               BeanUtils.copyProperties(spent,spentResponse);
                               return spentResponse;
                            }).collect(Collectors.toList()));
                    return typeSpentResponse;
                }).collect(Collectors.toList());
    }

}
