package com.finances.Finances.V1.service.impl;

import com.finances.Finances.V1.dto.wallet.WalletRequest;
import com.finances.Finances.V1.dto.wallet.WalletResponse;
import com.finances.Finances.V1.model.User;
import com.finances.Finances.V1.model.Wallet;
import com.finances.Finances.V1.repository.UserRepository;
import com.finances.Finances.V1.repository.WalletRepository;
import com.finances.Finances.V1.service.interfaces.WalletService;
import com.finances.Finances.V1.util.BigDecimalUtil;
import com.finances.Finances.V1.util.UserUtil;
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
    private final UserRepository userRepository;

    @Override
    public WalletResponse patchCurrentBalance(UUID userId, String value) {
        User user = UserUtil.valid(userId,userRepository);
        Wallet wallet = user.getWallet();

        wallet.setCurrentBalance(BigDecimalUtil.realToBigDecimal(value));
        walletRepository.save(wallet);

        WalletResponse response = new WalletResponse();
        BeanUtils.copyProperties(wallet,response);

        return response;
    }
}
