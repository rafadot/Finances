package com.finances.Finances.V1.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {

    @NotNull
    private Double currentBalance;

    @NotNull
    private Double monthlyExpense;
}
