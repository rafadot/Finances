package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.wallet.WalletRequest;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import com.finances.Finances.V1.model.Wallet;
import com.finances.Finances.V1.repository.WalletRepository;
import com.finances.Finances.V1.service.interfaces.WalletService;
import com.finances.Finances.exceptions.management.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public WalletResponse patchWallet(UUID walletId, WalletRequest walletRequest) {
        Optional<Wallet> optWallet = walletRepository.findById(walletId);

        if(optWallet.isEmpty())
            throw new BadRequestException("Id da carteira incorreto");

        Wallet wallet = Wallet.builder()
                .id(optWallet.get().getId())
                .currentBalance(walletRequest.getCurrentBalance() == null ? optWallet.get().getCurrentBalance() : walletRequest.getCurrentBalance())
                .monthlyExpense(walletRequest.getMonthlyExpense() == null ? optWallet.get().getMonthlyExpense() : walletRequest.getMonthlyExpense())
                .build();

        walletRepository.save(wallet);

        WalletResponse response = new WalletResponse();
        BeanUtils.copyProperties(wallet,response);

        return response;
    }
}
