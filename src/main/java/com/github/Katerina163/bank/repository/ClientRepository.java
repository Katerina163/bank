package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {

    Client save(Client client);

    Optional<Client> findByEmailAndPassword(String email, String password);

    Optional<Client> findByEmail(String email);
}
