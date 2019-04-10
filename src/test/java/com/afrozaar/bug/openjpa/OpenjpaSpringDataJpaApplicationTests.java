package com.afrozaar.bug.openjpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenjpaSpringDataJpaApplicationTests {

    @Autowired
    private MemberRepository repository;

    @Test
    public void MemberByName() {
        Member save = repository.save(new Member(1L, "John"));
        assertThat(repository.findAll()).isNotEmpty();

        List<Member> findByName = repository.findByName("John");

        assertThat(findByName).containsOnly(save);
    }

}
