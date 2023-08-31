package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.request.BankDto;
import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.dto.request.CreditDto;
import com.github.Katerina163.bank.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    Optional<Bank> save(BankDto bank);

    Optional<Bank> findByNameAndPassword(String name, String password);

    List<ClientDto> findClientsByName(String name);

    List<CreditDto> findCreditsByName(String name);

    void addCredit(String bankName, CreditDto credit);
}
