package com.ty.hospitalapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.PersonDao;
import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class PersonService {

	@Autowired
	PersonDao dao;

	public ResponseStructure<Person> savePerson(Person person) {
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(dao.savePerson(person));
		return responseStructure;
	}

	public ResponseStructure<Person> getPersonById(int person_id) {
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		Person person = dao.getPersonById(person_id);
		if (person != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(person);
		} else {
			throw new NoIdFoundException("Person id "+person_id+" not found");	
		}
		return responseStructure;
	}

	public ResponseStructure<List<Person>> getAllPerson() {
		ResponseStructure<List<Person>> responseStructure = new ResponseStructure<List<Person>>();
		List<Person> list = dao.getAllPerson();
		if (list != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(list);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not found");
			responseStructure.setData(null);
		}
		return responseStructure;
	}

	public ResponseStructure<Person> updatePerson(Person person, int person_id) {
		ResponseStructure<Person> responseStructure = new ResponseStructure<Person>();
		Person findPerson = dao.getPersonById(person_id);
		if (findPerson != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(dao.updatePerson(person, person_id));
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not found");
			responseStructure.setData(null);
		}
		return responseStructure;
	}

	public ResponseStructure<String> deletePerson(int person_id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (dao.deletePerson(person_id)) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Person found");
			responseStructure.setData("Person deleted");
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Person Not found");
			responseStructure.setData("Person not deleted");
		}
		return responseStructure;
	}
}
