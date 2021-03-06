package com.spring.adminships.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.adminships.models.Country;
import com.spring.adminships.models.VehicleModel;
import com.spring.adminships.models.State;
import com.spring.adminships.services.CountryService;
import com.spring.adminships.services.VehicleModelService;
import com.spring.adminships.services.StateService;

@Controller
public class VehicleModelController {
	

	  @Autowired private VehicleModelService vehicleModelService;


	@GetMapping("/vehicleModels")
	public String getVehicleModels(Model model) {
	  List <VehicleModel> vehicleModelList =vehicleModelService.getVehicleModels();
	  model.addAttribute("vehicleModels",vehicleModelList);	  
		return "VehicleModels";
	}
	
	  @PostMapping("/vehicleModels/addNew") 
	  public String addNew(VehicleModel vehicleModel) {
	  vehicleModelService.save(vehicleModel); 
	  return "redirect:/vehicleModels"; }

	// after getting this Id we don't want it to return a JSON
	// or redirect to a page that will display a JSON so only return it and capture
	// it
	
	  @RequestMapping("/vehicleModels/findById")
	  @ResponseBody
	  public Optional<VehicleModel> findById(int id) { 
		  return vehicleModelService.findById(id);
	  }
	  
	  @RequestMapping(value= "/vehicleModels/update", method= {RequestMethod.PUT,RequestMethod.GET})
	  public String update(VehicleModel vehicleModel) {
		  vehicleModelService.save(vehicleModel); 
		  return "redirect:/vehicleModels"; 
		  }
	  
	  @RequestMapping(value= "/vehicleModels/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	  public String delete(int id) {
		  vehicleModelService.delete(id); 
		  return "redirect:/vehicleModels"; 
		  }

}
