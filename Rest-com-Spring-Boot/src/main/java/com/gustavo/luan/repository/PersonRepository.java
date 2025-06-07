package com.gustavo.luan.repository;

import com.gustavo.luan.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
