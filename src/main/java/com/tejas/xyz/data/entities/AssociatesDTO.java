package com.tejas.xyz.data.entities;

public class AssociatesDTO {
	/*
	 * private final Optional<Associates> associates; private final
	 * Optional<Specialization> specialization;
	 * 
	 * public AssociatesDTO(Optional<Associates> associates,
	 * Optional<Specialization> specialization) { super(); this.associates =
	 * associates; this.specialization = specialization; }
	 * 
	 * public Optional<Associates> getAssociates() { return associates; }
	 * 
	 * public Optional<Specialization> getSpecialization() { return specialization;
	 * }
	 */

	private Long associateId;
	private String name;
	private String phone;
	private String address;

	public AssociatesDTO() {
		super();
	}

	public AssociatesDTO(Long associateId, String name, String phone, String address) {
		super();
		this.associateId = associateId;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
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

}
