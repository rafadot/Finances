package com.finances.Finances.V1.dto.wallet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finances.Finances.V1.util.DotToComma;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private BigDecimal currentBalance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private BigDecimal monthlyExpense;

    public String getCurrentBalance() {
        return DotToComma.convert(currentBalance);
    }

    public String getMonthlyExpense() {
        return DotToComma.convert(monthlyExpense);
    }
}
