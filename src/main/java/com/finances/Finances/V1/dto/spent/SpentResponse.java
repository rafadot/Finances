package com.finances.Finances.V1.dto.spent;

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
public class SpentResponse {

    UUID id;

    private BigDecimal value;

    private String title;

    private String description;

    private LocalDate date;

    private String typeName;

    public String getValue() {
        return BigDecimalUtil.bigDecimalToReal(value);
    }
}
