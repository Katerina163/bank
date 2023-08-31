package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.dto.response.ClientsDto;
import com.github.Katerina163.bank.mapper.Mapper;
import com.github.Katerina163.bank.model.Client;
import com.github.Katerina163.bank.model.LoanOffer;
import com.github.Katerina163.bank.repository.ClientRepository;
import com.github.Katerina163.bank.repository.CreditRepository;
import com.github.Katerina163.bank.repository.LoanOfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SimpleClientService implements ClientService {
    private final ClientRepository repository;
    private final CreditRepository creditRepository;
    private final LoanOfferRepository loanOfferRepository;
    private final Mapper<ClientDto, Client> clientDtoToClientMapper;
    private final Mapper<Client, ClientsDto> clientToClientsDtoMapper;

    public SimpleClientService(ClientRepository repository,
                               CreditRepository creditRepository,
                               LoanOfferRepository loanOfferRepository,
                               Mapper<ClientDto, Client> clientDtoToClientMapper,
                               Mapper<Client, ClientsDto> clientToClientsDtoMapper) {
        this.repository = repository;
        this.creditRepository = creditRepository;
        this.loanOfferRepository = loanOfferRepository;
        this.clientDtoToClientMapper = clientDtoToClientMapper;
        this.clientToClientsDtoMapper = clientToClientsDtoMapper;
    }

    @Override
    public Optional<Client> save(ClientDto client) {
        return Optional.of(repository.save(clientDtoToClientMapper.convert(client)));
    }

    @Override
    public Optional<Client> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<ClientsDto> findByEmail(String email) {
        return repository.findByEmail(email).map(clientToClientsDtoMapper::convert);
    }

    @Override
    public void modify(ClientsDto client) {
        var c = repository.findById(client.getId()).get();
        c.setFullName(client.getFullName());
        c.setEmail(client.getEmail());
        c.setPassport(client.getPassport());
        c.setPhone(client.getPhone());
    }

    @Override
    public Optional<ClientsDto> findById(UUID id) {
        return repository.findById(id).map(clientToClientsDtoMapper::convert);
    }

    @Override
    public void addLoan(UUID clientId, UUID loanOfferId, long price) {
        var offer = new LoanOffer();
        offer.setClient(repository.findById(clientId).get());
        offer.setMonth(LocalDate.now());
        offer.setCredit(creditRepository.findById(loanOfferId).get());
        offer.setLoanAmount(price);
        loanOfferRepository.save(offer);
    }
}
