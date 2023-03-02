package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.spent.SpentRequest;
import com.finances.Finances.V1.dto.spent.SpentResponse;
import com.finances.Finances.V1.dto.type_spent.TypeSpentRequest;
import com.finances.Finances.V1.dto.type_spent.TypeSpentResponse;
import com.finances.Finances.V1.service.interfaces.SpentService;
import com.finances.Finances.V1.service.interfaces.TypeSpentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/spent")
@RequiredArgsConstructor
public class SpentController {

    private final TypeSpentService typeSpentService;
    private final SpentService spentService;

    @PostMapping
    public ResponseEntity<SpentResponse> create(
            @RequestParam UUID userId,
            @RequestParam(defaultValue = "") String typeName,
            @RequestBody SpentRequest request){
        return new ResponseEntity<>(spentService.create(userId,typeName,request),HttpStatus.CREATED);
    }

    @PostMapping("/typeSpent")
    public ResponseEntity<TypeSpentResponse> create(@RequestParam UUID userId, @RequestBody @Valid TypeSpentRequest request){
        return new ResponseEntity<>(typeSpentService.create(userId,request), HttpStatus.CREATED);
    }
}
