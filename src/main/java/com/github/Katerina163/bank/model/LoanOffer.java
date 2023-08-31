package com.github.Katerina163.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loan_offer")
@Getter
@Setter
@NoArgsConstructor
public class LoanOffer {
    @Id
    private UUID id = UUID.randomUUID();

    @OneToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(optional = false)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Column(name = "loan_amount")
    private long loanAmount;

    private LocalDate month;
}
