package br.com.erudio.service;

import br.com.erudio.controller.PersonController;
import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.util.Util;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
  
  private static final Logger logger = Logger.getLogger(PersonService.class.getName());
  
  @Autowired
  private PersonRepository personRepository;
  
  public EntityModel<PersonVOV1> findById(Long id) {
    Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    logger.info("findById one Person!");
    PersonVOV1 personVO = new PersonVOV1();
    Util.copyProperties(person, personVO);
    EntityModel em = EntityModel.of(personVO,linkTo(methodOn(PersonController.class).findByPerson(id)).withSelfRel());
    return em;
  }
  
  public List<PersonVOV1> findAll() {
    logger.info("findAll one Person!");
    List<PersonVOV1> personVOs = DozerMapper.parseListObjects(personRepository.findAll(), PersonVOV1.class);
    personVOs.forEach(person -> {
      person.add(linkTo(methodOn(PersonController.class).findByPerson(person.getId())).withSelfRel());
    });        
    return personVOs;
  }
  
  @Transactional
  public PersonVOV1 create(PersonVOV1 personVo) {
    if(Objects.isNull(personVo)) {
      throw new RequiredObjectIsNullException();
    }
    logger.info("create Person!");
    Person converterForPerson = DozerMapper.parseObject(personVo, Person.class);
    PersonVOV1 personVOV1 = DozerMapper.parseObject(personRepository.save(converterForPerson), PersonVOV1.class);
    personVOV1.add(linkTo(methodOn(PersonController.class).findByPerson(personVOV1.getId())).withSelfRel());
    return personVOV1;
  }
  
  @Transactional
  public PersonVOV2 createV2(PersonVOV2 personVo) {
    if(Objects.isNull(personVo)) {
      throw new RequiredObjectIsNullException();
    }
    logger.info("createV2 Person!");
    Person converterForPerson = DozerMapper.parseObject(personVo, Person.class);
    return DozerMapper.parseObject(personRepository.save(converterForPerson), PersonVOV2.class);
  }
  
  @Transactional
  public PersonVOV1 update(PersonVOV1 personVO) {
    if(Objects.isNull(personVO)) {
      throw new RequiredObjectIsNullException();
    }
    logger.info("update Person!");
    Person converterForPerson = DozerMapper.parseObject(personVO, Person.class);
    Person person = personRepository.findById(converterForPerson.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    Util.copyProperties(converterForPerson, person, "id");
    PersonVOV1 personVOV1 = DozerMapper.parseObject(personRepository.save(person), PersonVOV1.class);
    personVOV1.add(linkTo(methodOn(PersonController.class).findByPerson(personVOV1.getId())).withSelfRel());
    return personVOV1;
  }
  
  @Transactional
  public void deletePerson(Long id) {
    logger.info("deleting Person!");
    personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    personRepository.deleteById(id);
  }
  
}
