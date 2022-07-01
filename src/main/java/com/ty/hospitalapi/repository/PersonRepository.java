package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.hospitalapi.dto.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
