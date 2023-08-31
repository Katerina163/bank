package com.github.Katerina163.bank.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class LoanDto {
    @NotNull
    private UUID id;
    @NotNull
    private Long price;
}
