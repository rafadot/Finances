package com.finances.Finances.V1.dto.type_spent;

import com.finances.Finances.V1.model.enums.TypeSpentColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeSpentRequest {

    @NotNull
    private String name;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private TypeSpentColor color;
}
