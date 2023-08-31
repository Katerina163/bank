package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.request.CreditDto;
import com.github.Katerina163.bank.mapper.Mapper;
import com.github.Katerina163.bank.model.Credit;
import com.github.Katerina163.bank.repository.CreditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SimpleCreditService implements CreditService {
    private final CreditRepository repository;
    private final Mapper<Credit, CreditDto> creditToCreditDtoMapper;

    public SimpleCreditService(CreditRepository repository,
                               Mapper<Credit, CreditDto> creditToCreditDtoMapper) {
        this.repository = repository;
        this.creditToCreditDtoMapper = creditToCreditDtoMapper;
    }

    @Override
    public void modify(CreditDto dto) {
        var credit = repository.findById(dto.getId()).get();
        credit.setInterestRate(dto.getPercent());
        credit.setLimit(dto.getLimit());
    }

    @Override
    public Optional<CreditDto> findById(UUID id) {
        return repository.findById(id).map(creditToCreditDtoMapper::convert);
    }
}
