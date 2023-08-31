package com.github.Katerina163.bank.calculations;

import org.junit.Test;

import static com.github.Katerina163.bank.calculations.Calculator.calPercentMonth;
import static com.github.Katerina163.bank.calculations.Calculator.calcMonthlyRate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CalculatorTest {
    @Test
    public void whenCorrect() {
        var result = calcMonthlyRate(5, 300_000L);
        var result2 = calcMonthlyRate(15, 100_000_000L);
        var result3 = calcMonthlyRate(20, 50_000_000L);
        assertThat(result, is(25681L));
        assertThat(result2, is(9025700L));
        assertThat(result3, is(4631700L));
    }

    @Test
    public void corect() {
        var result = calPercentMonth(300_000L, 5);
        var result2 = calPercentMonth(100_000_000L, 15);
        var result3 = calPercentMonth(50_000_000L, 20);
        assertThat(result, is(1232L));
        assertThat(result2, is(1232876L));
        assertThat(result3, is(821917L));
    }
}