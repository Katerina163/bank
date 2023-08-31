package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.model.Credit;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreditToCreditDtoMapperTest {
    private CreditToCreditDtoMapper mapper = new CreditToCreditDtoMapper();

    @Test
    public void whenCorrect() {
        var credit = new Credit();
        var u = UUID.randomUUID();
        credit.setId(u);
        credit.setInterestRate(10);
        credit.setLimit(100_000_000L);
        var result = mapper.convert(credit);
        assertThat(result.getId(), is(u));
        assertThat(result.getPercent(), is(10));
        assertThat(result.getLimit(), is(100_000_000L));
    }
}