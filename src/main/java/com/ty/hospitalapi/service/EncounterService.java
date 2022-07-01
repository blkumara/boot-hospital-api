package com.ty.hospitalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.BranchDao;
import com.ty.hospitalapi.dao.EncounterDao;
import com.ty.hospitalapi.dao.PersonDao;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class EncounterService {

	@Autowired
	EncounterDao encounterDao;

	@Autowired
	BranchDao branchDao;

	@Autowired
	PersonDao personDao;

	public ResponseStructure<Encounter> saveEncounter(int branch_id, int person_id, Encounter encounter) {
		ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
		Encounter findEncounter = encounterDao.saveEncounter(branch_id, person_id, encounter);
		if (findEncounter != null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("success");
			responseStructure.setData(findEncounter);
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Encounter> getEncounterById(int encounter_id) {
		ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
		Encounter encounter=encounterDao.getEncounterById(encounter_id);
		if(encounter!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(encounter);
		}else {
			throw new NoIdFoundException("Enounter id "+encounter_id+" not found");
		}
		return responseStructure;
	}

	public ResponseStructure<Encounter> updateEncounter(Encounter encounter, int encounter_id) {
		ResponseStructure<Encounter> responseStructure = new ResponseStructure<Encounter>();
		Encounter findEncounter=encounterDao.getEncounterById(encounter_id);
		if(findEncounter!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(encounter) ;
		}else {
			throw new NoIdFoundException("Enounter id "+encounter_id+" not found");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteEncounter(int encounter_id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		Encounter findEncounter=encounterDao.getEncounterById(encounter_id);
		if(findEncounter!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData("Encounter deleted");
		}else {
			throw new NoIdFoundException("Enounter id "+encounter_id+" not found");
		}
		return responseStructure;
	}
}
