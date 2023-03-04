package com.finances.Finances.V1.dto.wallet;

import com.finances.Finances.V1.util.BigDecimalUtil;
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
    private String currentBalance;

    @NotNull
    private String monthlyExpense;

    public BigDecimal getCurrentBalance() {
        return BigDecimalUtil.realToBigDecimal(currentBalance);
    }

    public BigDecimal getMonthlyExpense() {
        return BigDecimalUtil.realToBigDecimal(monthlyExpense);
    }
}
