package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.request.BankDto;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BankDtoToBankMapperTest {
    private BankDtoToBankMapper mapper = new BankDtoToBankMapper();

    @Test
    public void whenCorrect() {
        var dto = new BankDto();
        dto.setName("bank");
        dto.setPassword("password");
        var result = mapper.convert(dto);
        assertThat(result.getName(), is("bank"));
        assertThat(result.getPassword(), is("password"));
    }
}