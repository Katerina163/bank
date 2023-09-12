package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.calculations.Calculator;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.Tuple;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class TupleToLoanOffersDtoMapperTest {
    private TupleToLoanOffersDtoMapper mapper = new TupleToLoanOffersDtoMapper(new Calculator());

    @Test
    public void whenCorrect() {
        var tuple = Mockito.mock(Tuple.class);
        var u = UUID.randomUUID();
        when(tuple.get(0)).thenReturn(u);
        when(tuple.get(1)).thenReturn(100_000L);
        when(tuple.get(2)).thenReturn(10);
        var result = mapper.convert(tuple);
        assertThat(result.getId(), is(u));
        assertThat(result.getPercent(), is(10));
        assertThat(result.getSumm(), is(100_000L));
        assertThat(result.getMonthly(), is(8791L));
    }
}