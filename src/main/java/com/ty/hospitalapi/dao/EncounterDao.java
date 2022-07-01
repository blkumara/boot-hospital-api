package com.ty.hospitalapi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.repository.EncounterRepository;

@Repository
public class EncounterDao {

	@Autowired
	EncounterRepository encounterRepository;

	@Autowired
	BranchDao branchDao;

	@Autowired
	PersonDao dao;

	public Encounter saveEncounter(int branch_id, int person_id, Encounter encounter) {
		Branch branch = branchDao.getBranchById(branch_id);
		Person person = dao.getPersonById(person_id);
		if (branch != null && person != null) {
			encounter.setBranch(branch);
			encounter.setPerson(person);
			return encounterRepository.save(encounter);
		} else {
			return null;
		}
	}

	public Encounter getEncounterById(int encounter_id) {
		Optional<Encounter> optional = encounterRepository.findById(encounter_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Encounter updateEncounter(Encounter encounter, int encounter_id) {
		Optional<Encounter> optional = encounterRepository.findById(encounter_id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return encounterRepository.save(encounter);
		}
	}

	public boolean deleteEncounter(int encounter_id) {
		Optional<Encounter> optional = encounterRepository.findById(encounter_id);
		if (optional.isEmpty()) {
			return false;
		} else {
			encounterRepository.delete(optional.get());
			return true;
		}
	}
}
