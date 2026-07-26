package service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import model.Citizen;
import service.repository.CitizenRepository;
import service.repository.CitizenSpecifications;



@RestController
@RequestMapping("/api/citizens")
public class CitizenController {
	
	@Autowired
	private CitizenRepository repo;
	
	CitizenController(CitizenRepository repo){
		
		this.repo = repo;
		
	}
	@GetMapping(produces = {"application/json", "application/xml"})
	public List<Citizen> getModel(@RequestParam(required = false) String AT, 
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname,  
			@RequestParam(required = false) String gender, 
			@RequestParam(required = false) String date,
	        @RequestParam(required = false) String afm,
	        @RequestParam(required = false) String address
	        ){
		
		Specification<Citizen> spec = Specification.where(null);
		

		if (AT != null) {
	        spec = spec.and(CitizenSpecifications.hasAT(AT));
	    }
	
	    if (name != null) {
	        spec = spec.and(CitizenSpecifications.hasName(name));
	    }
	
	    if (surname != null) {
	        spec = spec.and(CitizenSpecifications.hasSurname(surname));
	    }
	
	    if (gender != null) {
	        spec = spec.and(CitizenSpecifications.hasGender(gender));
	    }
	
	    if (date != null) {
	        spec = spec.and(CitizenSpecifications.hasDate(date));
	    }

	    if (afm != null) {
	        spec = spec.and(CitizenSpecifications.hasAfm(afm));
	    }

	    if (address != null) {
	        spec = spec.and(CitizenSpecifications.hasAddress(address));
	    }

	    return repo.findAll(spec);


	}
	@PostMapping
	public Citizen createCitizen(@Valid @RequestBody Citizen citizen) {

	    if (repo.existsById(citizen.getAT())) {
	        throw new ResponseStatusException(
	                HttpStatus.CONFLICT,
	                "Citizen with AT already exists");
	    }

	    return repo.save(citizen);
	}
	@DeleteMapping("/{AT}")
	public void deleteCitizen(@PathVariable String AT) {

	    if (AT.isBlank()) {
	        throw new ResponseStatusException(
	                HttpStatus.BAD_REQUEST,
	                "AT is empty");
	    }

	    if (!repo.existsById(AT)) {
	        throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND,
	                "Citizen with this AT not found");
	    }

	    repo.deleteById(AT);
	}
	@PutMapping("/{AT}")
	public Citizen updateCitizen(@PathVariable String AT,@Valid @RequestBody Citizen citizen) {

	    Citizen existingCitizen = repo.findById(AT)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Citizen not found"));

	    existingCitizen.setAFM(citizen.getAFM());
	    existingCitizen.setAddress(citizen.getAddress());

	    return repo.save(existingCitizen);
	}
	@GetMapping("/{AT}")
	public Citizen getCitizen(@PathVariable String AT) {

	    if (AT.isBlank()) {
	        throw new ResponseStatusException(
	                HttpStatus.BAD_REQUEST,
	                "AT is empty");
	    }

	    return repo.findById(AT)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Citizen not found"));
	}
}