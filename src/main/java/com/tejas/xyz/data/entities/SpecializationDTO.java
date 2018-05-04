package com.tejas.xyz.data.entities;

public class SpecializationDTO {

	// private Long AssociateId;
	private String name;

	public SpecializationDTO() {
		super();
	}

	public SpecializationDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
