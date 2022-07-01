package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.HospitalDao;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class HospitalService {

	@Autowired
	HospitalDao dao;

	public ResponseStructure<Hospital> saveHospital(Hospital hospital) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(dao.saveHospital(hospital));
		return responseStructure;
	}

	public ResponseStructure<Hospital> getHospitalById(int id) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();
		Hospital hospital = dao.getHospitalById(id);
		if (hospital != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(hospital);
		} else {
			throw new NoIdFoundException("hospital id " + id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<List<Hospital>> getAllHospital() {
		ResponseStructure<List<Hospital>> responseStructure = new ResponseStructure<List<Hospital>>();
		List<Hospital> list = dao.getAllHospital();
		if (list != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(list);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(list);
		}
		return responseStructure;
	}

	public ResponseStructure<Hospital> updateHospital(Hospital hospital, int id) {
		ResponseStructure<Hospital> responseStructure = new ResponseStructure<Hospital>();
		Hospital findHospital = dao.getHospitalById(id);
		if (findHospital != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(dao.updateHospital(hospital, id));
		} else {
			throw new NoIdFoundException("hospital id " + id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteHospital(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (dao.deleteHospital(id)) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData("Hospital deleted");
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not found");
			responseStructure.setData("Hospital not deleted");
		}
		return responseStructure;
	}
}
