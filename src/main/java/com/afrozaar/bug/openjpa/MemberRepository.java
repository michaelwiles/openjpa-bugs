package com.afrozaar.bug.openjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String string);

    Collection<Member> findByNameIn(Collection<String> list);

}
