package com.finances.Finances.V1.service.interfaces;

import com.finances.Finances.V1.dto.wallet.WalletRequest;
import com.finances.Finances.V1.dto.wallet.WalletResponse;

import java.util.UUID;

public interface WalletService {
    WalletResponse patchWallet(UUID walletId, WalletRequest walletRequest);
}
