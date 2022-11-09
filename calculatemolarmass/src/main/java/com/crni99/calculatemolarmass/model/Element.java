package com.crni99.calculatemolarmass.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "elements")
public class Element {

	@Id
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "atomic_mass")
	private int atomicMass;

	public Element() {
	}

	public Element(Long id, String name, int atomicMass) {
		this.id = id;
		this.name = name;
		this.atomicMass = atomicMass;
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

	public int getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(int atomicMass) {
		this.atomicMass = atomicMass;
	}

	@Override
	public String toString() {
		return "Element [id=" + id + ", name=" + name + ", atomicMass=" + atomicMass + "]";
	}

}
