package com.afrozaar.bug.openjpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenJPA_2788_Test {

    protected final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberRepository repository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void OpenJPA_2788() {

        Member m = new Member(1, "dave");
        m.setAge(5);
        m.setPlace("capetown");
        em.persist(m);

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Member> q = cb.createQuery(Member.class);
        Root<Member> c = q.from(Member.class);
        ParameterExpression<String> name = cb.parameter(String.class);
        ParameterExpression<String> place = cb.parameter(String.class);
        CriteriaQuery<Member> where = q.select(c).where(cb.equal(c.get("name"), name), cb.equal(c.get("place"), place));

        TypedQuery<Member> query = em.createQuery(where);
        query.setParameter(name, "dave");
        query.setParameter(place, "capetown");
        List<Member> results = query.getResultList();

        assertThat(results).isNotEmpty();
    }
}
