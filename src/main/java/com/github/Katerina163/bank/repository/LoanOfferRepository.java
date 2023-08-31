package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.LoanOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LoanOfferRepository extends CrudRepository<LoanOffer, UUID>, LoanOfferCustomRepository {
}
