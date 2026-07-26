package service.httpcallstest;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;




@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Citizen Endpoint Testing")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class citizenhttpcalls {
	



	    @Test
	    @Order(1)
	    public void getCitizens() {
	
	        given()
	        .when()
	            .get("http://localhost:8081/api/citizens")
	        .then()
	            .statusCode(200);
	    }
	
	    @Test
	    @Order(2)
	    public void createCitizen() {
	
	        String body = """
	        {
	            "at":"ZZ123456",
	            "name":"John",
	            "surname":"Smith",
	            "gender":"M",
	            "date":"01-01-1990",
	            "afm":"123456789",
	            "address":"Athens"
	        }
	        """;
	
	        given()
	            .contentType("application/json")
	            .body(body)
	        .when()
	            .post("http://localhost:8081/api/citizens")
	        .then()
	            .statusCode(200);
	    }
	
	    @Test
	    @Order(3)
	    public void getCitizen() {
	
	        given()
	        .when()
	            .get("http://localhost:8081/api/citizens/ZZ123456")
	        .then()
	            .statusCode(200);
	    }
	
	    @Test
	    @Order(4)
	    public void deleteCitizen() {
	
	        given()
	        .when()
	            .delete("http://localhost:8081/api/citizens/ZZ123456")
	        .then()
	            .statusCode(200);
	    }
	

}