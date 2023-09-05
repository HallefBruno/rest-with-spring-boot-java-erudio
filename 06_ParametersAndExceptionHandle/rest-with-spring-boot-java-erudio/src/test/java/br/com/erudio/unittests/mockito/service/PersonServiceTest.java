package br.com.erudio.unittests.mockito.service;

import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.service.PersonService;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  MockPerson input;

  @InjectMocks
  PersonService personService;

  @Mock
  PersonRepository personRepository;

  @BeforeEach
  void setUpMocks() {
    input = new MockPerson();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Person person = input.mockEntity(1);
    person.setId(1L);
    when(personRepository.findById(1L)).thenReturn(Optional.of(person));
    var result = personService.findById(1L);
    assertNotNull(result);
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("[</api/person/v1/findBy/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getContent().getAddress());
    assertEquals("First Name Test1", result.getContent().getFirstName());
    assertEquals("Last Name Test1", result.getContent().getLastName());
    assertEquals("Female", result.getContent().getGender());
  }

  @Test
  void testCreate() {
    Person entity = input.mockEntity(1);
    Person persisted = entity;
    persisted.setId(1L);

    PersonVOV1 personVOV1 = input.mockVO(1);
    when(personRepository.save(entity)).thenReturn(persisted);
    var result = personService.create(personVOV1);

    assertNotNull(result);
    assertNotNull(result.getLinks());
    assertTrue(result.toString().contains("[</api/person/v1/findBy/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
  }

  @Test
  void testUpdate() {
    Person entity = input.mockEntity(1);
    entity.setId(1L);

    Person persisted = entity;
    persisted.setId(1L);

    PersonVOV1 personVOV1 = input.mockVO(1);

    when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    when(personRepository.save(entity)).thenReturn(persisted);

    var result = personService.update(personVOV1);

    assertNotNull(result);
    assertNotNull(result.getLinks());
    System.out.println(result.toString());
    assertTrue(result.toString().contains("[</api/person/v1/findBy/1>;rel=\"self\"]"));
    assertEquals("Addres Test1", result.getAddress());
    assertEquals("First Name Test1", result.getFirstName());
    assertEquals("Last Name Test1", result.getLastName());
    assertEquals("Female", result.getGender());
  }

  @Test
  void testDelete() {
    Person entity  = input.mockEntity(1);
    entity.setId(1L);

    when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
    personService.deletePerson(1L);
  }
  
  @Test
  void testCreateWithNullPerson() {
    Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
      personService.create(null);
    });
    assertTrue(exception.getMessage().contains("It is not allowed to persist a null object!"));
  }
  
  @Test
  void testFindAll() {
    List<Person> list  = input.mockEntityList();
    when(personRepository.findAll()).thenReturn(list);
    var peoples = personService.findAll();
    assertNotNull(peoples);
    assertEquals(14, peoples.size());
    var person = peoples.get(0);
    assertEquals("Addres Test0", person.getAddress());
    assertEquals("First Name Test0", person.getFirstName());
    assertTrue(person.toString().contains("/api/person/v1/findBy/0>;rel=\"self\""));
    
  }
  
}
