package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.request.BankDto;
import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.dto.request.CreditDto;
import com.github.Katerina163.bank.mapper.Mapper;
import com.github.Katerina163.bank.model.Bank;
import com.github.Katerina163.bank.model.Client;
import com.github.Katerina163.bank.model.Credit;
import com.github.Katerina163.bank.repository.BankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SimpleBankService implements BankService {
    private final BankRepository repository;
    private final Mapper<BankDto, Bank> bankDtoToBankMapper;
    private final Mapper<Credit, CreditDto> creditToCreditDtoMapper;
    private final Mapper<Client, ClientDto> clientToClientDtoMapper;

    public SimpleBankService(BankRepository repository, Mapper<BankDto, Bank> bankDtoToBankMapper,
                             Mapper<Credit, CreditDto> creditToCreditDtoMapper,
                             Mapper<Client, ClientDto> clientToClientDtoMapper) {
        this.repository = repository;
        this.bankDtoToBankMapper = bankDtoToBankMapper;
        this.creditToCreditDtoMapper = creditToCreditDtoMapper;
        this.clientToClientDtoMapper = clientToClientDtoMapper;
    }

    @Override
    public Optional<Bank> save(BankDto bank) {
        return Optional.of(repository.save(bankDtoToBankMapper.convert(bank)));
    }

    @Override
    public Optional<Bank> findByNameAndPassword(String name, String password) {
        return repository.findByNameAndPassword(name, password);
    }

    @Override
    public List<ClientDto> findClientsByName(String name) {
        return repository.findClientsByName(name).stream()
                .map(clientToClientDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDto> findCreditsByName(String name) {
        return repository.findAllCreditByName(name).stream()
                .map(creditToCreditDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void addCredit(String bankName, CreditDto dto) {
        var bank = repository.findByName(bankName);
        var credit = new Credit();
        credit.setLimit(dto.getLimit());
        credit.setInterestRate(dto.getPercent());
        bank.addCredit(credit);
    }
}
