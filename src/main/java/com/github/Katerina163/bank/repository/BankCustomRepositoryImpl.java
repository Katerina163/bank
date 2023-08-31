package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.github.Katerina163.bank.model.QBank.bank;
import static com.github.Katerina163.bank.model.QClient.client;
import static com.github.Katerina163.bank.model.QCredit.credit;
import static com.github.Katerina163.bank.model.QLoanOffer.loanOffer;

@Component
public class BankCustomRepositoryImpl implements BankCustomRepository {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> findClientsByName(String name) {
        return new JPAQuery<Tuple>(entityManager)
                .distinct()
                .select(client)
                .from(client)
                .join(loanOffer).on(client.id.eq(loanOffer.client.id))
                .join(credit).on(credit.id.eq(loanOffer.credit.id))
                .join(bank).on(bank.id.eq(credit.bank.id))
                .where(bank.name.eq(name))
                .fetch();
    }
}
