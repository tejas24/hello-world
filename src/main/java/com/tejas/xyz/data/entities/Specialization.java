package com.tejas.xyz.data.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Specialization", schema = "dbo")
public class Specialization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name = "name")
	private String name;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "associateid")
	@JsonIgnore
	private Associates associates;

	public Specialization() {
		super();
	}

	public Specialization(Long id, String name, Associates associates) {
		super();
		this.id = id;
		this.name = name;
		this.associates = associates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Associates getAssociates() {
		return associates;
	}

	public void setAssociates(Associates associates) {
		this.associates = associates;
	}

	@Override
	public String toString() {
		return "Specialization [id=" + id + ", name=" + name + ", associates=" + associates + "]";
	}

}
