package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.request.BankDto;
import com.github.Katerina163.bank.model.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankDtoToBankMapper implements Mapper<BankDto, Bank> {
    @Override
    public Bank convert(BankDto bankDto) {
        var result = new Bank();
        result.setName(bankDto.getName());
        result.setPassword(bankDto.getPassword());
        return result;
    }
}
