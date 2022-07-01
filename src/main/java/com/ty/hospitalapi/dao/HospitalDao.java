package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.repository.HospitalRepository;

@Repository
public class HospitalDao {

	@Autowired
	HospitalRepository hospitalRepository;

	public Hospital saveHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}

	public Hospital getHospitalById(int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public List<Hospital> getAllHospital() {
		return hospitalRepository.findAll();
	}

	public Hospital updateHospital(Hospital hospital, int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return hospitalRepository.save(hospital);
		}
	}

	public boolean deleteHospital(int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if (optional.isEmpty()) {
			return false;
		} else {
			hospitalRepository.delete(optional.get());
			return true;
		}
	}

}
