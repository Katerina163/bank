package com.github.Katerina163.bank.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class LoanOffersDto {
    @NotNull
    private UUID id;
    private int percent;
    @NotNull
    private long summ;
    private long monthly;
}
