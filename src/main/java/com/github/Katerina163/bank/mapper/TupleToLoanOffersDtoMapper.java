package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.response.LoanOffersDto;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import java.util.UUID;

import static com.github.Katerina163.bank.calculations.Calculator.calcMonthlyRate;

@Component
public class TupleToLoanOffersDtoMapper implements Mapper<Tuple, LoanOffersDto> {
    @Override
    public LoanOffersDto convert(Tuple tuple) {
        var loan = new LoanOffersDto();
        loan.setId((UUID) tuple.get(0));
        loan.setSumm((Long) tuple.get(1));
        loan.setPercent((Integer) tuple.get(2));
        loan.setMonthly(calcMonthlyRate(loan.getPercent(), loan.getSumm()));
        return loan;
    }
}
