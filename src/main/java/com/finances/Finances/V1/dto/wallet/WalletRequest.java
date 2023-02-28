package com.finances.Finances.V1.dto.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {

    @NotNull
    private BigDecimal currentBalance;

    @NotNull
    private BigDecimal monthlyExpense;
}
