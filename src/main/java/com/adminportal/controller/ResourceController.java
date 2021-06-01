package com.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.service.ShoeService;

@RestController
public class ResourceController {

	@Autowired
	private ShoeService shoeService;
	
	@RequestMapping(value="/shoe/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> shoeIdList,Model model
			) {
		for(String id : shoeIdList)
		{
			String shoeId=id.substring(8);
			shoeService.removeOne(Long.parseLong(shoeId));
		}
		
		return "delete success";
		
	}
}
