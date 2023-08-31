package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.request.CreditDto;

import java.util.Optional;
import java.util.UUID;

public interface CreditService {
    void modify(CreditDto credit);

    Optional<CreditDto> findById(UUID id);
}
