package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.request.CreditDto;
import com.github.Katerina163.bank.model.Credit;
import org.springframework.stereotype.Component;

@Component
public class CreditToCreditDtoMapper implements Mapper<Credit, CreditDto> {
    @Override
    public CreditDto convert(Credit credit) {
        var dto = new CreditDto();
        dto.setId(credit.getId());
        dto.setPercent(credit.getInterestRate());
        dto.setLimit(credit.getLimit());
        return dto;
    }
}
