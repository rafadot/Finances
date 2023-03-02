package com.finances.Finances.V1.dto.spent;

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
public class SpentRequest {

    @NotNull
    private BigDecimal value;

    private String title;

    private String description;

    private LocalDate date;
}
