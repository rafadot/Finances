package com.finances.Finances.V1.dto.wallet;

import com.finances.Finances.V1.util.ConvertBigDecimal;
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
        return ConvertBigDecimal.convert(currentBalance);
    }

    public String getMonthlyExpense() {
        return ConvertBigDecimal.convert(monthlyExpense);
    }
}
