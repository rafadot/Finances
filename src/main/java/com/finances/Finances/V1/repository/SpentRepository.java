package com.finances.Finances.V1.repository;

import com.finances.Finances.V1.model.Spent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpentRepository extends JpaRepository<Spent, UUID> {
}
