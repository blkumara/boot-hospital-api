package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.BranchDao;
import com.ty.hospitalapi.dao.HospitalDao;
import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class BranchService {
	
	@Autowired
	BranchDao branchDao;
	
	@Autowired
	HospitalDao dao;
	
	public ResponseStructure<Branch> saveBranch(Branch branch , int hospital_id) {
		ResponseStructure<Branch> responseStructure=new ResponseStructure<Branch>();
		Hospital hospital=dao.getHospitalById(hospital_id);
		if(hospital!=null) {
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("success");
			responseStructure.setData(branchDao.saveBranch(branch, hospital_id));
		}
		else {
			throw new NoIdFoundException("Hospital id "+hospital_id+" not found");
		}
		return responseStructure;
	}

	public ResponseStructure<Branch> getBranchById(int id) {
		ResponseStructure<Branch> responseStructure=new ResponseStructure<Branch>();
		Branch branch=branchDao.getBranchById(id);
		if(branch!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(branchDao.getBranchById(id));
		}else {
			throw new NoIdFoundException("Branch id "+id+" not found");
		}
		return responseStructure;
	}

	public ResponseStructure<List<Branch>> getBranchesByHospitalId(int hospital_id) {
		ResponseStructure<List<Branch>> responseStructure=new ResponseStructure<List<Branch>>();
		List<Branch> list=branchDao.getBranchesByHospitalId(hospital_id);
		if(list!=null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(list);
		}else {
			throw new NoIdFoundException("Hospital id "+hospital_id+" not found");
		}
		return responseStructure;
	}


	public ResponseStructure<Branch> updateBranch(Branch branch, int id) {
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		Branch findBranch=branchDao.getBranchById(id);
		if (findBranch != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(branchDao.updateBranch(branch, id));
		} else {
			throw new NoIdFoundException("Branch id "+id+" not found");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteBranch(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (branchDao.deleteBranch(id)) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData("Branch deleted");
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not found");
			responseStructure.setData("Branch not deleted");
		}
		return responseStructure;
	}
}
