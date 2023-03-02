package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.type_spent.TypeSpentRequest;
import com.finances.Finances.V1.dto.type_spent.TypeSpentResponse;

import java.util.UUID;

public interface TypeSpentService {
    TypeSpentResponse create(UUID userid,TypeSpentRequest request);
}
