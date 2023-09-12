package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.Credit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {
    Optional<Credit> findById(UUID id);

    @Query("from Credit c where c.bank.id = :id and c.limit > :price")
    List<Credit> findAllByBankId(UUID id, Long price);
}
