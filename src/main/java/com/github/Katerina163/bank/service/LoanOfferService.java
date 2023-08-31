package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.response.LoanOffersDto;
import com.github.Katerina163.bank.dto.response.Payment;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LoanOfferService {
    List<LoanOffersDto> findCreditsByEmail(String email);

    Map<String, List<LoanOffersDto>> findCreditsByPrice(Long price);

    List<Payment> findPaymentsByLoanOfferId(UUID id);
}
