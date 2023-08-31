package com.github.Katerina163.bank.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class BankDto {
    @Length(min = 2, max = 200)
    private String name;

    @Length(min = 2, max = 200)
    private String password;
}