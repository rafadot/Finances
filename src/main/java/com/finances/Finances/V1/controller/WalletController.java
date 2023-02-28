package com.finances.Finances.V1.controller;

import com.finances.Finances.V1.dto.wallet.WalletRequest;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import com.finances.Finances.V1.service.interfaces.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PatchMapping
    public ResponseEntity<WalletResponse> patchWallet(@RequestParam UUID walletId, @RequestBody @Valid WalletRequest walletRequest){
        return new ResponseEntity<>(walletService.patchWallet(walletId,walletRequest), HttpStatus.OK);
    }
}
