package com.finances.Finances.V1.model;

import com.finances.Finances.V1.model.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id")
    private List<Billing> billing;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_user_id")
    private List<TypeSpent> typeSpentList = new ArrayList<>();

}
