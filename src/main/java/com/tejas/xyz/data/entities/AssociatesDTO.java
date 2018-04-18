package com.tejas.xyz.data.entities;

public class AssociatesDTO {

	private String name;
	private String phone;
	private String address;
	private String specialization;

	public AssociatesDTO(String name, String phone, String address, String specialization) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.specialization = specialization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

}
