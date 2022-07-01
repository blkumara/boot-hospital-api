package com.ty.hospitalapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.hospitalapi.dto.MedOrder;

public interface MedOrderRepository extends JpaRepository<MedOrder, Integer> {

	@Query("Select m from MedOrder m where m.encounter.id=?1")
	List<MedOrder> getMedOrderByEncounterId(int encounter_id);

}
