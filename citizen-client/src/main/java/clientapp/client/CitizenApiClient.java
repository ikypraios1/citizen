package clientapp.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import configuration.ApiConfiguration;
import model.Citizen;

@Component
public class CitizenApiClient {
	private final ApiConfiguration details;
	
	private RestClient client;
	

    public CitizenApiClient(ApiConfiguration details) {
        this.details = details;
        initClient();
    }
    
    private void initClient() {
		String url = "http://" + details.getHost() + ":" + details.getPort() + "/" + details.getApi();
		client = RestClient.create(url);
	}
    
    public void getCitizens(MediaType type) {

        Citizen[] citizens = client.get()
                .accept(type)
                .retrieve()
                .body(Citizen[].class);

        if (citizens == null || citizens.length == 0) {
            System.out.println("No citizens found.");
            return;
        }

        for (Citizen citizen : citizens) {
            System.out.println(citizen.getAT() + " "
                    + citizen.getName() + " "
                    + citizen.getSurname());
        }
    }
    
    public void getCitizen(String AT, MediaType type) {

        Citizen citizen = client.get()
                .uri("/" + AT)
                .accept(type)
                .retrieve()
                .body(Citizen.class);

        if (citizen == null) {
            System.out.println("Citizen not found");
            return;
        }

        System.out.println("AT: " + citizen.getAT());
        System.out.println("Name: " + citizen.getName());
        System.out.println("Surname: " + citizen.getSurname());
        System.out.println("Gender: " + citizen.getGender());
        System.out.println("Date: " + citizen.getDate());
        System.out.println("AFM: " + citizen.getAFM());
        System.out.println("Address: " + citizen.getAddress());
    }
    
    public void createCitizen(Citizen citizen, MediaType type) {

        Citizen result = client.post()
                .contentType(type)
                .accept(type)
                .body(citizen)
                .retrieve()
                .body(Citizen.class);

        System.out.println("Citizen created successfully.");

        if (result != null) {
            System.out.println(result.getAT());
        }
    }
    
    public void deleteCitizen(String at) {

        client.delete()
              .uri("/" + at)
              .retrieve()
              .toBodilessEntity();

        System.out.println("Citizen deleted successfully.");
    }
    public void updateCitizen(String at, Citizen citizen, MediaType type) {

        client.put()
                .uri("/" + at)
                .contentType(type)
                .accept(type)
                .body(citizen)
                .retrieve()
                .body(Citizen.class);

        System.out.println("Citizen updated successfully.");
    }

}
