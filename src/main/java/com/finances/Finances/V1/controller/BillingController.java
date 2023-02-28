package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.billing.BillingRequest;
import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.service.interfaces.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping
    public ResponseEntity<BillingResponse> create(@RequestParam UUID userId, @RequestBody BillingRequest billingRequest){
        return new ResponseEntity<>(billingService.create(userId,billingRequest), HttpStatus.CREATED);
    }

}
