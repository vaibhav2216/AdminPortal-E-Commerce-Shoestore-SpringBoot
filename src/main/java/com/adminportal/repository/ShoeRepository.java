package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.domain.Shoe;

public interface ShoeRepository extends CrudRepository<Shoe, Long> {

}
