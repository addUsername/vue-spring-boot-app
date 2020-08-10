package com.demo.crud.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Brand {
	

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//length = 64,
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	
	
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
	private List<Phone> phones = new ArrayList<Phone>();
		
	public Brand() {}
	
	public Brand(String name, String description, List<Phone> phones) {
		super();
		this.name = name;
		this.description = description;
		this.phones = phones;
	}
	public Brand(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonManagedReference
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	

}
