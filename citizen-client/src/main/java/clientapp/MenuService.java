package clientapp;

import java.util.Scanner;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import clientapp.client.CitizenApiClient;
import model.Citizen;

@Component
public class MenuService {

    private final CitizenApiClient client;

    public MenuService(CitizenApiClient client) {
        this.client = client;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("===== Citizen Registry =====");
            System.out.println("1. Create Citizen");
            System.out.println("2. Get Citizen");
            System.out.println("3. Search Citizens");
            System.out.println("4. Update Citizen");
            System.out.println("5. Delete Citizen");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

            case 1:

                System.out.print("AT: ");
                String at = scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Surname: ");
                String surname = scanner.nextLine();

                System.out.print("Gender: ");
                String gender = scanner.nextLine();

                System.out.print("Date (DD-MM-YYYY): ");
                String date = scanner.nextLine();

                System.out.print("AFM: ");
                String afm = scanner.nextLine();

                System.out.print("Address: ");
                String address = scanner.nextLine();

                Citizen citizen =
                        new Citizen.Builder(
                                at,
                                name,
                                surname,
                                gender,
                                date)
                        .afm(afm)
                        .address(address)
                        .build();

                client.createCitizen(
                        citizen,
                        MediaType.APPLICATION_JSON);

                break;


            case 2:

                System.out.print("Give AT: ");
                String at1 = scanner.nextLine();

                client.getCitizen(at1, MediaType.APPLICATION_JSON);

                break;

            case 3:
                client.getCitizens(MediaType.APPLICATION_JSON);
                break;

            case 4:

                System.out.print("AT: ");
                String updateAt = scanner.nextLine();

                System.out.print("AFM: ");
                String updateAfm = scanner.nextLine();

                System.out.print("Address: ");
                String updateAddress = scanner.nextLine();

                Citizen citizen1 =
                        new Citizen.Builder(
                                updateAt,
                                "dummy",
                                "dummy",
                                "M",
                                "01-01-2000")
                        .afm(updateAfm)
                        .address(updateAddress)
                        .build();

                client.updateCitizen(
                        updateAt,
                        citizen1,
                        MediaType.APPLICATION_JSON);

                break;

            case 5:

                System.out.print("Give AT: ");
                String atToDelete = scanner.nextLine();

                client.deleteCitizen(atToDelete);

                break;

            default:
                System.out.println("Exiting...");
                return;
            }
        }
    }
}