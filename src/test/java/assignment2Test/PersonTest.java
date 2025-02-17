package assignment2Test;

import assignment2.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Testing if person created is valid and all fields are set correctly
    @Test
    public void testValidPersonCreation() {
        Person person = Person.builder()
                .withId("1")
                .withFirstName("Parmod")
                .withLastName("Shrestha")
                .withAge(29)
                .withGender("Male")
                .build();

        assertEquals("1", person.getId());
        assertEquals("Parmod", person.getFirstName());
        assertEquals("Shrestha", person.getLastName());
        assertEquals(Integer.valueOf(29), person.getAge());
        assertEquals("Male", person.getGender());
    }

    // Testing if validation works for null ID
    @Test
    public void testNullId() {
        assertThrows(IllegalArgumentException.class, () ->
                Person.builder()
                        .withFirstName("Parmod")
                        .withLastName("Shrestha")
                        .withAge(29)
                        .build()
        );
    }

    // Testing if validation works for blank first name
    @Test
    public void testBlankFirstName() {
        assertThrows(IllegalArgumentException.class, () ->
                Person.builder()
                        .withId("1")
                        .withFirstName(" ")
                        .withLastName("Shrestha")
                        .withAge(29)
                        .build()
        );
    }

    // Testing if validation works for null last name
    @Test
    public void testNullLastName() {
        assertThrows(IllegalArgumentException.class, () ->
                Person.builder()
                        .withId("1")
                        .withFirstName("Parmod")
                        .withAge(29)
                        .build()
        );
    }

    // Testing if validation works for blank last name
    @Test
    public void testNegativeAge() {
        assertThrows(IllegalArgumentException.class, () ->
                Person.builder()
                        .withId("1")
                        .withFirstName("Parmod")
                        .withLastName("Shrestha")
                        .withAge(-29)
                        .build()
        );
    }

    // Testing if serialization and deserialization works correctly with builder pattern
    @Test
    public void testJsonSerialization() throws Exception {
        Person person = Person.builder()
                .withId("1")
                .withFirstName("Parmod")
                .withLastName("Shrestha")
                .withAge(29)
                .withGender("Male")
                .build();

        String json = objectMapper.writeValueAsString(person);
        Person deserializedPerson = objectMapper.readValue(json, Person.class);

        assertEquals(person, deserializedPerson);
    }

    // Testing if deserialization works correctly with a JSON string
    @Test
    public void testJsonDeserialization() throws Exception {
        String json = "{"
                + "\"id\": \"1\","
                + "\"firstName\": \"Parmod\","
                + "\"lastName\": \"Shrestha\","
                + "\"age\": 29,"
                + "\"gender\": \"Male\""
                + "}";

        Person person = objectMapper.readValue(json, Person.class);
        assertEquals("1", person.getId());
        assertEquals("Parmod", person.getFirstName());
        assertEquals("Shrestha", person.getLastName());
        assertEquals(Integer.valueOf(29), person.getAge());
        assertEquals("Male", person.getGender());
    }
}
