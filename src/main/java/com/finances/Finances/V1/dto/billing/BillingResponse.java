package com.finances.Finances.V1.dto.billing;

import com.finances.Finances.V1.util.BigDecimalUtil;
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
public class BillingResponse {

    private UUID id;

    private String title;

    private String description;

    private LocalDate date;

    private BigDecimal value;

    public String getValue() {
        return BigDecimalUtil.bigDecimalToReal(value);
    }
}
