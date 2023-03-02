package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.billing.BillingRequest;
import com.finances.Finances.V1.dto.billing.BillingResponse;
import com.finances.Finances.V1.service.interfaces.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;

    @PostMapping
    public ResponseEntity<BillingResponse> create(@RequestParam UUID userId, @RequestBody @Valid BillingRequest billingRequest){
        return new ResponseEntity<>(billingService.create(userId,billingRequest), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> delete(@RequestParam UUID userId, @RequestParam UUID billingId){
        return new ResponseEntity<>(billingService.delete(userId,billingId),HttpStatus.OK);
    }

}
