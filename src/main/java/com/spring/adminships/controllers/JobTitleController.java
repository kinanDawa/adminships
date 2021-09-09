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
import com.spring.adminships.models.Location;
import com.spring.adminships.models.State;
import com.spring.adminships.services.CountryService;
import com.spring.adminships.services.LocationService;
import com.spring.adminships.services.StateService;

@Controller
public class JobTitleController {
	

	  @Autowired private LocationService jobTitleService;
	  @Autowired private CountryService countryService;
	  @Autowired private StateService stateService;

	@GetMapping("/jobTitles")
	public String getLocations(Model model) {
	  List <Location> jobTitleList =jobTitleService.getLocations();
	  model.addAttribute("jobTitles",jobTitleList);
	  
	  List <Country> countryList =countryService.getCountries();
	  model.addAttribute("countries",countryList);
	  
	  List <State> stateList =stateService.getStates();
	  model.addAttribute("states",stateList);
	  
		return "Location";
	}
	
	  @PostMapping("/jobTitles/addNew") 
	  public String addNew(Location jobTitle) {
	  jobTitleService.save(jobTitle); 
	  return "redirect:/jobTitles"; }

	// after getting this Id we don't want it to return a JSON
	// or redirect to a page that will display a JSON so only return it and capture
	// it
	
	  @RequestMapping("/jobTitles/findById")
	  @ResponseBody
	  public Optional<Location> findById(int id) { 
		  return jobTitleService.findById(id);
	  }
	  
	  @RequestMapping(value= "/jobTitles/update", method= {RequestMethod.PUT,RequestMethod.GET})
	  public String update(Location jobTitle) {
		  jobTitleService.save(jobTitle); 
		  return "redirect:/jobTitles"; 
		  }
	  
	  @RequestMapping(value= "/jobTitles/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	  public String delete(int id) {
		  jobTitleService.delete(id); 
		  return "redirect:/jobTitles"; 
		  }
}
