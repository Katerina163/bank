package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class BankCustomRepositoryImpl implements BankCustomRepository {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> findClientsByName(String name) {
        String sql = "select distinct c from Client c "
                + "left join LoanOffer lo on lo.client.id = c.id "
                + "left join Credit cr on cr.id = lo.credit.id "
                + "left join Bank b on b.id = cr.bank.id "
                + "where b.name = :name";
        return entityManager.createQuery(sql, Client.class)
                .setParameter("name", name)
                .getResultList();
    }
}
