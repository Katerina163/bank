package com.github.Katerina163.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "full_name")
    private String fullName;

    private long phone;

    private String email;

    private long passport;

    private String password;
}
