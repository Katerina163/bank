package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.Bank;
import com.github.Katerina163.bank.model.Credit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankRepository extends CrudRepository<Bank, UUID>, BankCustomRepository {

    Bank save(Bank bank);

    Optional<Bank> findByNameAndPassword(String name, String password);

    @Query("select credits from Bank where name = :name")
    List<Credit> findAllCreditByName(String name);

    List<Bank> findAll();

    Bank findByName(String name);
}
