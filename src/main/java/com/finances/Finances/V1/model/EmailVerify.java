package com.finances.Finances.V1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailVerify {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "America/Fortaleza")
    private Instant instant;
}
