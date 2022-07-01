package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.hospitalapi.dto.Encounter;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {

}
