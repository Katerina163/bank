package com.github.Katerina163.bank.service;

import com.github.Katerina163.bank.dto.response.LoanOffersDto;
import com.github.Katerina163.bank.dto.response.Payment;
import com.github.Katerina163.bank.mapper.Mapper;
import com.github.Katerina163.bank.model.Bank;
import com.github.Katerina163.bank.repository.BankRepository;
import com.github.Katerina163.bank.repository.CreditRepository;
import com.github.Katerina163.bank.repository.LoanOfferRepository;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.Katerina163.bank.calculations.Calculator.calPercentMonth;
import static com.github.Katerina163.bank.calculations.Calculator.calcMonthlyRate;
import static com.github.Katerina163.bank.model.QCredit.credit;
import static com.github.Katerina163.bank.model.QLoanOffer.loanOffer;

@Service
@Transactional
public class SimpleLoanOfferService implements LoanOfferService {
    private final LoanOfferRepository repository;
    private final BankRepository bankRepository;
    private final CreditRepository creditRepository;
    private final Mapper<Tuple, LoanOffersDto> tupleToLoanOffersDtoMapper;

    public SimpleLoanOfferService(LoanOfferRepository repository,
                                  BankRepository bankRepository,
                                  CreditRepository creditRepository,
                                  Mapper<Tuple, LoanOffersDto> tupleToLoanOffersDtoMapper) {
        this.repository = repository;
        this.bankRepository = bankRepository;
        this.creditRepository = creditRepository;
        this.tupleToLoanOffersDtoMapper = tupleToLoanOffersDtoMapper;
    }

    @Override
    public List<LoanOffersDto> findCreditsByEmail(String email) {
        return repository.findLoanOffersByEmail(email).stream()
                .map(tupleToLoanOffersDtoMapper::convert)
                .collect(Collectors.toList());
    }

    /*
        не самый оптимальный способ
     */
    @Override
    public Map<String, List<LoanOffersDto>> findCreditsByPrice(Long price) {
        Map<String, List<LoanOffersDto>> result = new HashMap<>();
        List<Bank> banks = bankRepository.findAll();
        for (var bank : banks) {
            List<LoanOffersDto> offers = creditRepository.findAllByBankId(bank.getId())
                    .stream()
                    .map(credit -> {
                        var loan = new LoanOffersDto();
                        loan.setId(credit.getId());
                        loan.setSumm(credit.getLimit());
                        loan.setPercent(credit.getInterestRate());
                        loan.setMonthly(calcMonthlyRate(loan.getPercent(), price));
                        return loan;
                    })
                    .collect(Collectors.toList());
            if (!offers.isEmpty()) {
                result.put(bank.getName(), offers);
            }
        }
        return result;
    }

    @Override
    public List<Payment> findPaymentsByLoanOfferId(UUID id) {
        var offer = repository.findOfferById(id);
        var sum = offer.get(loanOffer.loanAmount);
        var percent = offer.get(credit.interestRate);
        var date = offer.get(loanOffer.month);
        var monthly = calcMonthlyRate(percent, sum);
        List<Payment> result = new ArrayList<>(11);
        var first = new Payment();
        first.setDate(date.plusMonths(1));
        first.setSum(monthly);
        first.setSumPercent(calPercentMonth(sum, percent));
        first.setSumLoan(monthly - first.getSumPercent());
        first.setDebt(sum - monthly + first.getSumPercent());
        result.add(first);
        for (int i = 1; i < 11; i++) {
            var prev = result.get(i - 1);
            var next = new Payment();
            next.setDate(prev.getDate().plusMonths(1));
            next.setSum(monthly);
            next.setSumPercent(calPercentMonth(prev.getDebt(), percent));
            next.setSumLoan(monthly - next.getSumPercent());
            next.setDebt(prev.getDebt() - monthly + next.getSumPercent());
            result.add(next);
        }
        var next = result.get(10);
        var last = new Payment();
        last.setDate(next.getDate().plusMonths(1));
        last.setSumLoan(next.getDebt());
        last.setSumPercent(calPercentMonth(next.getDebt(), percent));
        last.setSum(last.getSumLoan() + last.getSumPercent());
        last.setDebt(0L);
        result.add(last);
        return result;
    }
}










