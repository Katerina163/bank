package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.dto.response.ClientsDto;
import com.github.Katerina163.bank.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> save(ClientDto client);

    Optional<Client> findByEmailAndPassword(String email, String password);

    Optional<ClientsDto> findByEmail(String email);

    void modify(ClientsDto client);

    Optional<ClientsDto> findById(UUID id);

    void addLoan(UUID clientId, UUID loanOfferId, long price);
}
