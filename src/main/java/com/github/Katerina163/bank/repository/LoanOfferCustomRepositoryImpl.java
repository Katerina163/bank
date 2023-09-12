package com.github.Katerina163.bank.repository;

import com.github.Katerina163.bank.model.LoanOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.UUID;

@Component
public class LoanOfferCustomRepositoryImpl implements LoanOfferCustomRepository {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tuple> findLoanOffersByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        Root<LoanOffer> loanOffer = query.from(LoanOffer.class);
        var credit = loanOffer.join("credit", JoinType.INNER);
        var client = loanOffer.join("client", JoinType.INNER);
        query.select(cb.construct(
                Tuple.class,
                loanOffer.get("id"),
                loanOffer.get("loanAmount"),
                credit.get("interestRate")
        )).where(cb.equal(client.get("email"), email));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Tuple findOfferById(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        Root<LoanOffer> loanOffer = query.from(LoanOffer.class);
        var credit = loanOffer.join("credit", JoinType.INNER);
        query.select(cb.construct(
                Tuple.class,
                loanOffer.get("loanAmount"),
                loanOffer.get("month"),
                credit.get("interestRate")
        )).where(cb.equal(loanOffer.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
//        return new JPAQuery<Tuple>(entityManager)
//                .select(
//                        loanOffer.loanAmount,
//                        loanOffer.month,
//                        credit.interestRate
//                )
//                .from(loanOffer)
//                .join(credit).on(loanOffer.credit.id.eq(credit.id))
//                .where(loanOffer.id.eq(id))
//                .fetchFirst();
    }
}