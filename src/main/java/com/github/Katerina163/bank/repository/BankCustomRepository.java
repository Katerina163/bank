package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.Client;

import java.util.List;

public interface BankCustomRepository {
    List<Client> findClientsByName(String name);
}
