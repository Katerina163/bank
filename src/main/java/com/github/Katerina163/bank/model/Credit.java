package com.github.Katerina163.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Credit {
    @Id
    private UUID id = UUID.randomUUID();

    private long limit;

    @Column(name = "interest_rate")
    private int interestRate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_id")
    private Bank bank;
}
