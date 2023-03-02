package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.spent.SpentRequest;
import com.finances.Finances.V1.dto.spent.SpentResponse;

import java.util.UUID;

public interface SpentService {
    SpentResponse create(UUID userId, String typeName, SpentRequest request);
}
