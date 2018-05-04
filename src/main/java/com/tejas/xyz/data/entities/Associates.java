package com.tejas.xyz.data.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Associates", schema = "dbo")
public class Associates implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "associateid")
	private Long associateId;
	@Column(name = "name")
	private String name;
	@Column(name = "phone")
	private String phone;
	@Column(name = "address")
	private String address;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "associates")
	private Set<Specialization> specialization;
	
	public Associates() {
		 super();
	}

	public Associates(Long associateId, String name, String phone, String address, Set<Specialization> specialization) {
		super();
		this.associateId = associateId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.specialization = specialization;
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

	public Set<Specialization> getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Set<Specialization> specialization) {
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "Associates [associateId=" + associateId + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", specialization=" + specialization + "]";
	}
	
}
