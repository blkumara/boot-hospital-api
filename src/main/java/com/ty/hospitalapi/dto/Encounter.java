package com.ty.hospitalapi.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String admit__date_time;
	private String discharge__date_time;
	private String reason;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Branch branch;
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Person person;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmit__date_time() {
		return admit__date_time;
	}

	public void setAdmit__date_time(String admit__date_time) {
		this.admit__date_time = admit__date_time;
	}

	public String getDischarge__date_time() {
		return discharge__date_time;
	}

	public void setDischarge__date_time(String discharge__date_time) {
		this.discharge__date_time = discharge__date_time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


}