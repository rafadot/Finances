package com.finances.Finances.V1.dto.wallet;

import com.finances.Finances.V1.util.BigDecimalUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {

    private UUID id;

    private BigDecimal currentBalance;

    private BigDecimal monthlyExpense;

    public String getCurrentBalance() {
        return BigDecimalUtil.bigDecimalToReal(currentBalance);
    }

    public String getMonthlyExpense() {
        return BigDecimalUtil.bigDecimalToReal(monthlyExpense);
    }
}
