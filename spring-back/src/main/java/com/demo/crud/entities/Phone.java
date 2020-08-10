package com.demo.crud.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "phone")
public class Phone {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", length = 64,nullable = false,unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "spec_ids")
	private List<Spec> specs;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Post post;
	
	

	public Phone() {} //makes jpa happy
	
	public Phone(String name, Brand brand, List<Spec> specs, Post post) {
		super();
		this.name = name;
		this.brand = brand;
		this.specs = specs;
		this.post = post;
	}
	@JsonBackReference
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
	@JsonBackReference
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	@JsonManagedReference
	public List<Spec> getSpecs() {
		return specs;
	}

	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}
}
