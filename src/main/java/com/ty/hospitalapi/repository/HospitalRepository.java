package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.hospitalapi.dto.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

}
