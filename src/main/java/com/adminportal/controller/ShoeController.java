package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Shoe;
import com.adminportal.service.ShoeService;

@Controller
@RequestMapping("/shoe")
public class ShoeController {
	
	@Autowired
	ShoeService shoeService;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addShoe(Model model) {
		Shoe shoe = new Shoe();
		model.addAttribute("shoe", shoe);
		return "addShoe";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addShoePost(
			@ModelAttribute("shoe") Shoe shoe, HttpServletRequest request) {
		
		shoeService.save(shoe); 
		
		MultipartFile shoeImage = shoe.getShoeImage();
		
		try {
			byte[] bytes = shoeImage.getBytes();
			String name = shoe.getId() + ".png";   
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/images/shoe/"+name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:shoeList";
	}
	
	@RequestMapping("/shoeinfo")
	public String shoeInfo(@RequestParam("id") Long id,Model model) {
		Shoe shoe =shoeService.findOne(id);
		model.addAttribute("shoe", shoe);
		return "shoeinfo";
	}
	
	@RequestMapping("/updateShoe")
	public String updateShoe(@RequestParam("id") Long id,Model model) {
		Shoe shoe =shoeService.findOne(id);
		model.addAttribute("shoe", shoe);
		
		return "updateShoe";
	}
	
	@RequestMapping(value="/updateShoe", method=RequestMethod.POST)
	public String updateShoePost(@ModelAttribute("shoe") Shoe shoe,HttpServletRequest request) {
		shoeService.save(shoe); 
		MultipartFile shoeImage = shoe.getShoeImage();
		if(!shoeImage.isEmpty()) {
			try {
				byte[] bytes = shoeImage.getBytes();
				String name = shoe.getId() + ".png";   
										
				Files.delete(Paths.get("src/main/resources/static/images/shoe/"+name));
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/images/shoe/"+name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return "redirect:/shoe/shoeinfo?id="+shoe.getId();
	}
	

	@RequestMapping("/shoeList")
	public String shoeList(Model model) {
		List<Shoe> shoeList = shoeService.findAll();
		model.addAttribute("shoeList", shoeList);
		return "shoeList";
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@ModelAttribute("id") String id,Model model) {
		
		shoeService.removeOne(Long.parseLong(id.substring(8)));
		List<Shoe> shoeList = shoeService.findAll();
		model.addAttribute("shoeList", shoeList);
		
		return "redirect:/shoe/shoeList";
	}
}
