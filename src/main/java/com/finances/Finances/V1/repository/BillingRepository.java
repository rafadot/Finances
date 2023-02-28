package com.finances.Finances.V1.repository;

import com.finances.Finances.V1.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingRepository extends JpaRepository<Billing, UUID> {
}
