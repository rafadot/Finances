package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.billing.BillingRequest;
import com.finances.Finances.V1.dto.billing.BillingResponse;

import java.util.Map;
import java.util.UUID;

public interface BillingService {

    BillingResponse create(UUID userId, BillingRequest billingRequest);

    Map<String,String> delete(UUID userId, UUID billingId);

}
