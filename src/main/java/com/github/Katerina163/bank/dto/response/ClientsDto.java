package com.github.Katerina163.bank.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientsDto {
    private UUID id;
    private String email;
    private String fullName;
    private long phone;
    private long passport;
}
