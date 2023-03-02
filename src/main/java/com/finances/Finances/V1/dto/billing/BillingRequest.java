package com.finances.Finances.V1.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingRequest {

    @NotNull
    private String title;

    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private BigDecimal value;

}
