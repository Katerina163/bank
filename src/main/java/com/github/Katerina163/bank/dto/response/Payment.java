package com.github.Katerina163.bank.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Payment {
    private LocalDate date;
    private long sum;
    private long sumLoan;
    private long sumPercent;
    private long debt;
}
