package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.EncounterDao;
import com.ty.hospitalapi.dao.MedOrderDao;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Items;
import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class MedOrderService {

	@Autowired
	MedOrderDao medOrderDao;

	@Autowired
	EncounterDao encounterDao;

	public ResponseStructure<MedOrder> saveMedOrder(MedOrder medOrder, int encounter_id) {
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(medOrderDao.saveMedOrder(medOrder, encounter_id));
		return responseStructure;
	}

	public ResponseStructure<MedOrder> getMedOrderById(int medOrder_id) {
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		MedOrder medOrder = medOrderDao.getMedOrderById(medOrder_id);
		if (medOrder != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(medOrder);
		} else {
			throw new NoIdFoundException("MedOrder id " + medOrder_id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<List<MedOrder>> getMedOrderByEncounterId(int encounter_id) {
		ResponseStructure<List<MedOrder>> responseStructure = new ResponseStructure<List<MedOrder>>();
		List<MedOrder> list = medOrderDao.getMedOrderByEncounterId(encounter_id);
		if (list != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(list);
		} else {
			throw new NoIdFoundException("Encounter id " + encounter_id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<MedOrder> updateMedOrder(MedOrder medOrder, int medOrder_id) {
		ResponseStructure<MedOrder> responseStructure = new ResponseStructure<MedOrder>();
		Encounter findEncounter = encounterDao.getEncounterById(medOrder_id);
		if (findEncounter != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(medOrder);
		} else {
			throw new NoIdFoundException("Medorder id " + medOrder_id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteMedOrder(int medOrder_id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		Encounter findEncounter = encounterDao.getEncounterById(medOrder_id);
		if (findEncounter != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData("MedORder deleted");
		} else {
			throw new NoIdFoundException("MedOrder id " + medOrder_id + " not found");
		}
		return responseStructure;
	}

	public ResponseStructure<List<Items>> getItemsByMedOrderId(int medOrder_id) {
		ResponseStructure<List<Items>> responseStructure = new ResponseStructure<List<Items>>();
		List<Items> items = medOrderDao.getItemsByMedOrderId(medOrder_id);
		if (items != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(items);
		} else {
			throw new NoIdFoundException("MedOrder id " + medOrder_id + " not found");
		}
		return responseStructure;
	}
}
