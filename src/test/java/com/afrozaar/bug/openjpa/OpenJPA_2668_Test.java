package com.afrozaar.bug.openjpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenJPA_2668_Test {

    @Autowired
    private MemberRepository repository;

    @Test
    public void OpenJpa2668() {
        repository.save(new Member(2L, "Michael"));
        repository.save(new Member(3L, "James"));

        assertThat(repository.findByNameIn(Lists.newArrayList("Michael", "James"))).hasSize(2);

        assertThat(repository.findByNameIn(Lists.newArrayList("Michael"))).hasSize(1); // fails as the parameter from before is "cached"
    }

}
