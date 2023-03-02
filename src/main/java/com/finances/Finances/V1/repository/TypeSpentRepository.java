package com.finances.Finances.V1.repository;

import com.finances.Finances.V1.model.TypeSpent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeSpentRepository extends JpaRepository<TypeSpent, UUID> {

    Optional<TypeSpent> findByName(String name);
}
