package br.com.erudio.unittests.mockito.service;

import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.service.PersonService;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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
    private PersonService personService;
    
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
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getLinks());
        Assertions.assertTrue(result.toString().contains("[</person/v1/findBy/1>;rel=\"self\"]"));
        Assertions.assertEquals("Addres Test1", result.getContent().getAddress());
        Assertions.assertEquals("First Name Test1", result.getContent().getFirstName());
        Assertions.assertEquals("Last Name Test1", result.getContent().getLastName());
        Assertions.assertEquals("Female", result.getContent().getGender());
    }
    
    @Test
    void testCreate() {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);
        
        PersonVOV1 personVOV1 = input.mockVO(1);
        
        when(personRepository.save(entity)).thenReturn(persisted);
        var result = personService.create(personVOV1);
        
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getLinks());
        System.out.println(result.toString());
        Assertions.assertTrue(result.toString().contains("[</person/v1/findBy/1>;rel=\"self\"]"));
        Assertions.assertEquals("Addres Test1", result.getAddress());
        Assertions.assertEquals("First Name Test1", result.getFirstName());
        Assertions.assertEquals("Last Name Test1", result.getLastName());
        Assertions.assertEquals("Female", result.getGender());
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
        
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getLinks());
        System.out.println(result.toString());
        Assertions.assertTrue(result.toString().contains("[</person/v1/findBy/1>;rel=\"self\"]"));
        Assertions.assertEquals("Addres Test1", result.getAddress());
        Assertions.assertEquals("First Name Test1", result.getFirstName());
        Assertions.assertEquals("Last Name Test1", result.getLastName());
        Assertions.assertEquals("Female", result.getGender());
    }
    
    @Test
    void testDelete() {
        Person entity  = input.mockEntity(1);
        entity.setId(1L);
        
        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        personService.deletePerson(1L);
    }
}
