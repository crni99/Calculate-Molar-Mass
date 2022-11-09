package com.crni99.calculatemolarmass.repository;

import org.springframework.data.repository.CrudRepository;

import com.crni99.calculatemolarmass.model.Element;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Element, Long> {
	
	Element findElementByName(String name);

}
