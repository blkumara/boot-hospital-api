package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.repository.BranchRepository;
import com.ty.hospitalapi.repository.HospitalRepository;

@Repository
public class BranchDao {

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	HospitalDao dao;

	public Branch saveBranch(Branch branch, int hospital_id) {
		Hospital hospital = dao.getHospitalById(hospital_id);
		if (hospital != null) {
			branch.setHospital(hospital);
			return branchRepository.save(branch);
		} else {
			return null;
		}
	}

	public Branch getBranchById(int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public List<Branch> getBranchesByHospitalId(int hospital_id) {
			return branchRepository.getBranchesByHospitalId(hospital_id);
	}

	public Branch updateBranch(Branch branch, int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return branchRepository.save(branch);
		}
	}

	public boolean deleteBranch(int id) {
		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isEmpty()) {
			return false;
		} else {
			branchRepository.delete(optional.get());
			return true;
		}
	}
}
