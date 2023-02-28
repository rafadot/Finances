package com.finances.Finances.V1.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingRequest {

    private String title;

    private String description;

    private LocalDate date;

    private BigDecimal value;

}
