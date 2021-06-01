package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Shoe;

public interface ShoeService {

	Shoe save(Shoe shoe);
	
	List<Shoe> findAll();
	
	Shoe findOne(Long id);
	
	void removeOne(Long Id);
}