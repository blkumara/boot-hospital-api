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

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.service.BranchService;

@RestController
public class BranchController {

	@Autowired
	BranchService branchService;

	@PostMapping("/branches/{hospital_id}")
	public ResponseStructure<Branch> saveBranch(@RequestBody Branch branch, @PathVariable int hospital_id) {
		return branchService.saveBranch(branch, hospital_id);
	}

	@GetMapping("/branches/{id}")
	public ResponseStructure<Branch> getBranchById(@PathVariable int id) {
		return branchService.getBranchById(id);
	}

	@GetMapping("/brancheshospital/{hospital_id}")
	public ResponseStructure<List<Branch>> getBranchesByHospitalId(@PathVariable int hospital_id) {
		return branchService.getBranchesByHospitalId(hospital_id);
	}

	@PutMapping("/branches/{id}")
	public ResponseStructure<Branch> updateBranch(@RequestBody Branch branch, @PathVariable int id) {
		return branchService.updateBranch(branch, id);
	}

	@DeleteMapping("/branches/{id}")
	public ResponseStructure<String> deleteBranch(@PathVariable int id) {
		return branchService.deleteBranch(id);
	}
}
