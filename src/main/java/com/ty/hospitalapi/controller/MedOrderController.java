package com.ty.hospitalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hospitalapi.dto.Items;
import com.ty.hospitalapi.dto.MedOrder;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.service.MedOrderService;

@RestController
public class MedOrderController {

	@Autowired
	MedOrderService medOrderService;
	
	@PostMapping("/medorders/{encounter_id}")
	public ResponseStructure<MedOrder> saveMedOrder(@RequestBody MedOrder medOrder,@PathVariable int encounter_id) {
		return medOrderService.saveMedOrder(medOrder, encounter_id);
	}

	@GetMapping("/medorders/{medOrder_id}")
	public ResponseStructure<MedOrder> getMedOrderById(@PathVariable int medOrder_id) {
		return medOrderService.getMedOrderById(medOrder_id);
	}

	@GetMapping("/medordersencounter/{encounter_id}")
	public ResponseStructure<List<MedOrder>> getMedOrderByEncounterId(@PathVariable int encounter_id) {
		return medOrderService.getMedOrderByEncounterId(encounter_id);
	}

	@PutMapping("/medorders/{medOrder_id}")
	public ResponseStructure<MedOrder> updateMedOrder(@RequestBody MedOrder medOrder,@PathVariable int medOrder_id) {
		return medOrderService.updateMedOrder(medOrder, medOrder_id);
	}

	@DeleteMapping("/medorders/{medOrder_id}")
	public ResponseStructure<String> deleteMedOrder(@PathVariable int medOrder_id) {
		return medOrderService.deleteMedOrder(medOrder_id);
	}

	@GetMapping("/medordersitems/{medOrder_id}")
	public ResponseStructure<List<Items>> getItemsByMedOrderId(int medOrder_id) {
		return medOrderService.getItemsByMedOrderId(medOrder_id); 
		
	}
	
}
