package service.repository;

import org.springframework.data.jpa.domain.Specification;

import model.Citizen;


public class CitizenSpecifications {
	
	public static Specification<Citizen> hasAT(String AT){
		
		return (root, query, builder) -> builder.equal(root.get("AT"),AT);
	}
	public static Specification<Citizen> hasName(String name){
		
		return (root, query, builder) -> builder.equal(root.get("name"),name);
	}
	public static Specification<Citizen> hasSurname(String surname){
	
		return (root, query, builder) -> builder.equal(root.get("surname"),surname);
	}
	public static Specification<Citizen> hasGender(String gender){
		
		return (root, query, builder) -> builder.equal(root.get("gender"),gender);
	}
	public static Specification<Citizen> hasDate(String date){
		
		return (root, query, builder) -> builder.equal(root.get("date"),date);
	}
	public static Specification<Citizen> hasAfm(String afm){
		
		return (root, query, builder) -> builder.equal(root.get("afm"),afm);
	}
	public static Specification<Citizen> hasAddress(String address){
		
		return (root, query, builder) -> builder.equal(root.get("address"),address);
	}

}
