package com.tejas.xyz.data.entities;

public class SpecializationDTO {

	private Long AssociateId;
	private String name;

	public SpecializationDTO() {
		super();
	}

	public SpecializationDTO(Long associateId, String name) {
		super();
		AssociateId = associateId;
		this.name = name;
	}

	public Long getAssociateId() {
		return AssociateId;
	}

	public void setAssociateId(Long associateId) {
		AssociateId = associateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
