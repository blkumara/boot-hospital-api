package com.ty.hospitalapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.hospitalapi.dto.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

	@Query("Select b from Branch b where b.hospital.id=?1")
	List<Branch> getBranchesByHospitalId(int hospital_id);
}
