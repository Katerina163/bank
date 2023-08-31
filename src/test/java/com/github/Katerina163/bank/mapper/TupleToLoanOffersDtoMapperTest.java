package com.github.Katerina163.bank.mapper;

import com.querydsl.core.Tuple;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static com.github.Katerina163.bank.model.QCredit.credit;
import static com.github.Katerina163.bank.model.QLoanOffer.loanOffer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class TupleToLoanOffersDtoMapperTest {
    private TupleToLoanOffersDtoMapper mapper = new TupleToLoanOffersDtoMapper();

    @Test
    public void whenCorrect() {
        var tuple = Mockito.mock(Tuple.class);
        var u = UUID.randomUUID();
        when(tuple.get(loanOffer.id)).thenReturn(u);
        when(tuple.get(loanOffer.loanAmount)).thenReturn(100_000L);
        when(tuple.get(credit.interestRate)).thenReturn(10);
        var result = mapper.convert(tuple);
        assertThat(result.getId(), is(u));
        assertThat(result.getPercent(), is(10));
        assertThat(result.getSumm(), is(100_000L));
        assertThat(result.getMonthly(), is(8791L));
    }
}