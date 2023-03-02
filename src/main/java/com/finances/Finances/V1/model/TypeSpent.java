package com.finances.Finances.V1.model;

import com.finances.Finances.V1.model.enums.TypeSpentColor;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeSpent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private BigDecimal totalSpent;

    @Enumerated(value = EnumType.STRING)
    private TypeSpentColor color;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_spent_id")
    private List<Spent> spentList;
}
