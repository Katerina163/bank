package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.response.LoanOffersDto;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;

import static com.github.Katerina163.bank.calculations.Calculator.calcMonthlyRate;
import static com.github.Katerina163.bank.model.QCredit.credit;
import static com.github.Katerina163.bank.model.QLoanOffer.loanOffer;

@Component
public class TupleToLoanOffersDtoMapper implements Mapper<Tuple, LoanOffersDto> {
    @Override
    public LoanOffersDto convert(Tuple tuple) {
        var loan = new LoanOffersDto();
        loan.setId(tuple.get(loanOffer.id));
        loan.setSumm(tuple.get(loanOffer.loanAmount));
        loan.setPercent(tuple.get(credit.interestRate));
        loan.setMonthly(calcMonthlyRate(loan.getPercent(), loan.getSumm()));
        return loan;
    }
}
