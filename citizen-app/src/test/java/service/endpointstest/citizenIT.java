package service.endpointstest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import model.Citizen;
import service.repository.CitizenRepository;

@SpringBootTest(classes = service.CitizenApplication.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testing All Endpoints")
public class citizenIT {

    @Autowired
    private CitizenRepository repo;

    @Test
    @Order(1)
    void testBuilderCreatesCitizen() {

        Citizen citizen =
                new Citizen.Builder(
                        "AB123456",
                        "John",
                        "Smith",
                        "M",
                        "01-01-1990")
                .afm("123456789")
                .address("Athens")
                .build();

        assertEquals("John", citizen.getName());
    }

    @Test
    @Order(2)
    void testEquals() {

        Citizen c1 =
                new Citizen.Builder(
                        "AB123456",
                        "John",
                        "Smith",
                        "M",
                        "01-01-1990")
                .build();

        Citizen c2 =
                new Citizen.Builder(
                        "AB123456",
                        "Maria",
                        "Jones",
                        "F",
                        "01-01-1995")
                .build();

        assertTrue(c1.equals(c2));
    }

    @Test
    @Order(3)
    void testSetName() {

        Citizen citizen =
                new Citizen.Builder(
                        "AB123456",
                        "John",
                        "Smith",
                        "M",
                        "01-01-1990")
                .build();

        citizen.setName("Giannis");

        assertEquals("Giannis", citizen.getName());
    }

    @Test
    @Order(4)
    void testSaveCitizen() {

        Citizen citizen =
                new Citizen.Builder(
                        "AB111111",
                        "John",
                        "Smith",
                        "M",
                        "01-01-1990")
                .afm("123456789")
                .address("Athens")
                .build();

        repo.save(citizen);

        Optional<Citizen> result =
                repo.findById("AB111111");

        assertTrue(result.isPresent());
    }

    @Test
    @Order(5)
    void testDeleteCitizen() {

        Citizen citizen =
                new Citizen.Builder(
                        "AB222222",
                        "John",
                        "Smith",
                        "M",
                        "01-01-1990")
                .afm("123456789")
                .address("Athens")
                .build();

        repo.save(citizen);

        repo.deleteById("AB222222");

        Optional<Citizen> result =
                repo.findById("AB222222");

        assertFalse(result.isPresent());
    }

    @Test
    @Order(6)
    void testFindCitizen() {

        Citizen citizen =
                new Citizen.Builder(
                        "AB333333",
                        "John",
                        "Smith",
                        "M",
                        "01-01-1990")
                .afm("123456789")
                .address("Athens")
                .build();

        repo.save(citizen);

        Optional<Citizen> result =
                repo.findById("AB333333");

        assertEquals(
                "John",
                result.get().getName());
    }
 
}