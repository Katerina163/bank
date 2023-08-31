package com.github.Katerina163.bank.dto.request;

import com.github.Katerina163.bank.validation.LoginGroup;
import com.github.Katerina163.bank.validation.RegisterGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientDto {
    @Email(groups = {LoginGroup.class, RegisterGroup.class})
    private String email;

    @NotNull(groups = {LoginGroup.class, RegisterGroup.class})
    @Length(min = 2, max = 200, groups = {LoginGroup.class, RegisterGroup.class})
    private String password;

    @Length(min = 2, max = 200, groups = RegisterGroup.class)
    private String fullName;

    @Range(min = 79000000000L, max = 89999999999L, groups = RegisterGroup.class)
    private long phone;

    @Range(min = 1000000000L, max = 9999999999L, groups = RegisterGroup.class)
    private long passport;
}
