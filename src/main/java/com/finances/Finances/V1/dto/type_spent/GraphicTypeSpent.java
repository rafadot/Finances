package com.finances.Finances.V1.dto.type_spent;

import com.finances.Finances.V1.dto.spent.SpentResponse;
import com.finances.Finances.V1.model.enums.TypeSpentColor;
import com.finances.Finances.V1.util.BigDecimalUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GraphicTypeSpent {

    private UUID id;

    private String name;

    private BigDecimal totalSpent;

    @Enumerated(value = EnumType.STRING)
    private TypeSpentColor color;

    private List<SpentResponse> spentList;

    private Double columPercentage;

    public String getTotalSpent() {
        return BigDecimalUtil.bigDecimalToReal(totalSpent);
    }
}
