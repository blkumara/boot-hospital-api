package com.ty.hospitalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.service.EncounterService;

@RestController
public class EncounterController {

	@Autowired
	EncounterService encounterService;

	@PostMapping("/encounters/{branch_id}/{person_id}")
	public ResponseStructure<Encounter> saveEncounter(@PathVariable int branch_id, @PathVariable int person_id,
			@RequestBody Encounter encounter) {
		return encounterService.saveEncounter(branch_id, person_id, encounter);
	}

	@GetMapping("/encounters/{encounter_id}")
	public ResponseStructure<Encounter> getEncounterById(@PathVariable int encounter_id) {
		return encounterService.getEncounterById(encounter_id);
	}

	@PutMapping("/encounters/{encounter_id}")
	public ResponseStructure<Encounter> updateEncounter(Encounter encounter, int encounter_id) {
		return encounterService.updateEncounter(encounter, encounter_id);
	}

	@DeleteMapping("/encounters")
	public ResponseStructure<String> deleteEncounter(int encounter_id) {
		return encounterService.deleteEncounter(encounter_id);
	}
}
