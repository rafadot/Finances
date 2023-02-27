package com.finances.Finances.V1.dto.wallet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponse {

    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#,##")
    private Double currentBalance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#,##")
    private Double monthlyExpense;
}
