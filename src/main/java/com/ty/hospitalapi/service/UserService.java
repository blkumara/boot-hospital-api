package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.UserDao;
import com.ty.hospitalapi.dto.ResponseStructure;
import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.exception.InvalidUserException;
import com.ty.hospitalapi.exception.NoIdFoundException;

@Service
public class UserService {

	@Autowired
	UserDao dao;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Success");
		responseStructure.setData(dao.saveUser(user));
		return responseStructure;
	}

	public ResponseStructure<List<User>> getAllUser() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		List<User> list = dao.getAllUser();
		if (list != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(list);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not found");
			responseStructure.setData(null);
		}
		return responseStructure;

	}

	public ResponseStructure<User> getUserById(int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = dao.getUserById(id);
		if (user != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(user);
		} else {
			throw new NoIdFoundException("User id "+id+" not found");
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteUser(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		if (dao.deleteUser(id)) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData("User deleted");
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("user not found");
			responseStructure.setData("User not deleted");
		}
		return responseStructure;
	}

	public ResponseStructure<User> updateUser(User user, int id) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User findUser = dao.getUserById(id);
		if (findUser != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(dao.updateUser(user, id));
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not updated");
			responseStructure.setData(null);
		}
		return responseStructure;
	}

	public ResponseStructure<User> validateUser(String email, String password) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		User user = dao.validateUser(email, password);
		if (user != null) {
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(user);
		} else {
			throw new InvalidUserException();
		}
		return responseStructure;

	}

}
