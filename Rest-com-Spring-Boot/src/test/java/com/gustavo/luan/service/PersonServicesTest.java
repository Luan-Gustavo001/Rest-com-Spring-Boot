package com.gustavo.luan.service;

import com.gustavo.luan.data.dto.PersonDTO;
import com.gustavo.luan.exception.RequiredObjectIsNullException;
import com.gustavo.luan.model.Person;
import com.gustavo.luan.repository.PersonRepository;
import com.gustavo.luan.unitetests.mapper.mocks.MockPerson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());

        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("self")
                && link.getHref().contains("/api/person/v1/1")
                && link.getType().equals("GET"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("findAll")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("create")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("POST"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("update")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("PUT"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("delete")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("DELETE"))

        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void create() {

        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());

        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("self")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("findAll")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("create")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("POST"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("update")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("PUT"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("delete")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("DELETE"))

        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.create(null);
                });

        String expectedMessage = "Objeto não pode ser nulo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {

        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());

        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("self")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("findAll")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("create")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("POST"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("update")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("PUT"))

        );

        assertNotNull(result.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("delete")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("DELETE"))

        );

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    service.update(null);
                });

        String expectedMessage = "Objeto não pode ser nulo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());

        assertNotNull(personOne.getLinks());

        assertNotNull(personOne.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("self")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(personOne.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("findAll")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(personOne.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("create")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("POST"))

        );

        assertNotNull(personOne.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("update")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("PUT"))

        );

        assertNotNull(personOne.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("delete")
                        && link.getHref().contains("/api/person/v1/1")
                        && link.getType().equals("DELETE"))

        );

        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());

        assertNotNull(personFour.getLinks());

        assertNotNull(personFour.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("self")
                        && link.getHref().contains("/api/person/v1/4")
                        && link.getType().equals("GET"))

        );

        assertNotNull(personFour.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("findAll")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(personFour.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("create")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("POST"))

        );

        assertNotNull(personFour.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("update")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("PUT"))

        );

        assertNotNull(personFour.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("delete")
                        && link.getHref().contains("/api/person/v1/4")
                        && link.getType().equals("DELETE"))

        );

        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("Male", personFour.getGender());

        var personNine = people.get(9);

        assertNotNull(personNine);
        assertNotNull(personNine.getId());

        assertNotNull(personNine.getLinks());

        assertNotNull(personNine.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("self")
                        && link.getHref().contains("/api/person/v1/9")
                        && link.getType().equals("GET"))

        );

        assertNotNull(personNine.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("findAll")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("GET"))

        );

        assertNotNull(personNine.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("create")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("POST"))

        );

        assertNotNull(personNine.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("update")
                        && link.getHref().contains("/api/person/v1")
                        && link.getType().equals("PUT"))

        );

        assertNotNull(personNine.getLinks().stream().anyMatch(
                link -> link.getRel().value().equals("delete")
                        && link.getHref().contains("/api/person/v1/9")
                        && link.getType().equals("DELETE"))

        );

        assertEquals("First Name Test9", personNine.getFirstName());
        assertEquals("Last Name Test9", personNine.getLastName());
        assertEquals("Address Test9", personNine.getAddress());
        assertEquals("Female", personNine.getGender());

    }
}