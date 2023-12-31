package com.github.Katerina163.bank.repository;


import javax.persistence.Tuple;
import java.util.List;
import java.util.UUID;

public interface LoanOfferCustomRepository {
    List<Tuple> findLoanOffersByEmail(String email);

    Tuple findOfferById(UUID id);
}
