package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Items;
import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.repository.MedOrderRepository;

@Repository
public class MedOrderDao {

	@Autowired
	MedOrderRepository medOrderRepository;

	@Autowired
	EncounterDao dao;

	public MedOrder saveMedOrder(MedOrder medOrder, int encounter_id) {
		Encounter encounter = dao.getEncounterById(encounter_id);
		if (encounter != null) {
			medOrder.setEncounter(encounter);
			List<Items> list = medOrder.getItems();
			for (Items items : list) {
				items.setMedOrder(medOrder);
			}
			return medOrderRepository.save(medOrder);
		} else {
			return null;
		}
	}

	public MedOrder getMedOrderById(int medOrder_id) {
		Optional<MedOrder> optional = medOrderRepository.findById(medOrder_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public List<MedOrder> getMedOrderByEncounterId(int encounter_id) {
		Encounter encounter = dao.getEncounterById(encounter_id);
		if (encounter != null) {
			return medOrderRepository.getMedOrderByEncounterId(encounter_id);
		} else {
			return null;
		}
	}

	public MedOrder updateMedOrder(MedOrder medOrder, int medOrder_id) {
		Optional<MedOrder> optional = medOrderRepository.findById(medOrder_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return medOrderRepository.save(medOrder);
		}
	}

	public boolean deleteMedOrder(int medOrder_id) {
		Optional<MedOrder> optional = medOrderRepository.findById(medOrder_id);
		if (optional.isEmpty()) {
			return false;
		} else {
			medOrderRepository.delete(optional.get());
			return true;
		}
	}

	public List<Items> getItemsByMedOrderId(int medOrder_id) {
		Optional<MedOrder> optional = medOrderRepository.findById(medOrder_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			List<Items> list = optional.get().getItems();
			return list;
		}
	}
}
