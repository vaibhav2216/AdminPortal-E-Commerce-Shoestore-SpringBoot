package com.adminportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Shoe;
import com.adminportal.repository.ShoeRepository;
import com.adminportal.service.ShoeService;



@Service
public class ShoeServiceImpl implements	ShoeService {

	@Autowired
	private ShoeRepository shoeRepository;
	
	public Shoe save(Shoe shoe) {
		return shoeRepository.save(shoe);
	}
	
	public List<Shoe> findAll(){
		return (List<Shoe>) shoeRepository.findAll();
	}
	
	public Shoe findOne(Long Id){
	    Optional <Shoe> optUser = shoeRepository.findById(Id); 
	    if (optUser.isPresent()) {
	        return optUser.get();
	    } else {
	        return null;
	    }
	}
	public void removeOne(Long Id) {
		shoeRepository.deleteById(Id);
	}
}