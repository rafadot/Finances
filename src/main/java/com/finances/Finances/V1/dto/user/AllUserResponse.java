package com.finances.Finances.V1.dto.user;

import com.finances.Finances.V1.model.Billing;
import com.finances.Finances.V1.model.Wallet;
import com.finances.Finances.V1.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllUserResponse {

    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Wallet wallet;

    private List<Billing> billing;
}
