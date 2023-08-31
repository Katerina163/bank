package com.github.Katerina163.bank.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

import static com.github.Katerina163.bank.model.QClient.client;
import static com.github.Katerina163.bank.model.QCredit.credit;
import static com.github.Katerina163.bank.model.QLoanOffer.loanOffer;

@Component
public class LoanOfferCustomRepositoryImpl implements LoanOfferCustomRepository {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tuple> findLoanOffersByEmail(String email) {
        return new JPAQuery<Tuple>(entityManager)
                .select(
                        loanOffer.id,
                        loanOffer.loanAmount,
                        credit.interestRate
                )
                .from(loanOffer)
                .join(credit).on(loanOffer.credit.id.eq(credit.id))
                .join(client).on(loanOffer.client.id.eq(client.id))
                .where(client.email.eq(email))
                .fetch();
    }

    @Override
    public Tuple findOfferById(UUID id) {
        return new JPAQuery<Tuple>(entityManager)
                .select(
                        loanOffer.loanAmount,
                        loanOffer.month,
                        credit.interestRate
                )
                .from(loanOffer)
                .join(credit).on(loanOffer.credit.id.eq(credit.id))
                .where(loanOffer.id.eq(id))
                .fetchFirst();
    }
}