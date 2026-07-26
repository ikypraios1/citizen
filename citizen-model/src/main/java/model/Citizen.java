package model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Citizen {
	@NotBlank(message = "AT cannot be blank!")
	@Pattern(
			regexp = "^.{8}$", 
			message = "AT is invalid"
	 )
	@Id
	private String AT = null;
	@NotBlank(message = "AT cannot be blank!")
    private String name = null;
    @NotBlank(message = "Name cannot be blank!")
    private String surname = null;
    @NotBlank(message = "Surname cannot be blank!")
	@Pattern(
			regexp = "^[MFO]$",
			message = "The only gender values that are accepted are M for Male , F for Female and O for Other"
	)
    private String gender = null;
	@Pattern(
			regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-[0-9]{4}$",
			message = "Date format is ivalid. Please use DD-MM-YYYY"
	)
    private String date = null;
	@Pattern(
	    regexp = "^[0-9]{9}$",
	    message = "AFM is invalid"
	)
    private String afm = null;
    private String address = null;
    
    public Citizen() {}
    
    private Citizen(Builder builder) {
    	this.AT = builder.AT;
    	this.name = builder.name;
    	this.surname = builder.surname;
    	this.gender = builder.gender;
        this.date = builder.date;
        this.afm = builder.afm;
        this.address = builder.address;
        
    }
	
    public static class Builder{
    	private String AT = null;
        private String name = null;
        private String surname = null;
        private String gender = null;
        private String date = null;
        private String afm = null;
        private String address = null;
        
        private static void checkSingleValue(String value, String message) throws IllegalArgumentException{
        	if (value == null || value.trim().equals("")) throw new IllegalArgumentException(message + " cannot be null or empty");
        }
        
        public Builder(String AT, String name, String surname, String gender, String date  ) throws IllegalArgumentException{
        	checkSingleValue(AT, "AT");
        	checkSingleValue(name, "Name");
        	checkSingleValue(surname, "Surname");
        	checkSingleValue(gender, "Gender");
        	checkSingleValue(date, "Date");
        	
        	this.AT = AT;
        	this.name = name;
        	this.surname = surname;
        	this.gender = gender;
        	this.date = date;
        }
        
        public Builder afm(String value) {
        	this.afm = value;
        	return this;
        }
        
        public Builder address(String value) {
        	this.address = value;
        	return this;
        }
        
        public Citizen build() {
        	return new Citizen(this);
        }

        
    }
    
	public String getAT() {
		return AT;
	}

	public void setAT(String AT) throws IllegalArgumentException{
		Builder.checkSingleValue(AT, "AT");
		this.AT = AT;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentException{
		Builder.checkSingleValue(name, "Name");
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) throws IllegalArgumentException{
		Builder.checkSingleValue(surname, "Surname");
		this.surname = surname;
	}
    
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) throws IllegalArgumentException{
		Builder.checkSingleValue(gender, "Gender");
		this.gender = gender;
	}
    
	public String getDate() {
		return date;
	}

	public void setDate(String date) throws IllegalArgumentException{
		Builder.checkSingleValue(date, "Date");
		this.date = date;
	}
	
	public String getAFM() {
		return afm;
	}

	public void setAFM(String afm) {
	    if (afm == null || afm.trim().equals("")) {
	        this.afm = null;
	    } else {
	        this.afm = afm.trim();
	    }
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
	    if (address == null || address.trim().equals("")) {
	        this.address = null;
	    } else {
	        this.address = address.trim();
	    }
	}
	
	public boolean equals(Object o) {
		if (o instanceof Citizen) {
			Citizen b = (Citizen)o;
			if (b.getAT().equals(AT)) return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}